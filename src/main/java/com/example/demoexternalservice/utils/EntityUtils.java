package com.example.demoexternalservice.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static ch.qos.logback.core.joran.util.beans.BeanUtil.isGetter;

public class EntityUtils {


    public
    static
    @Data
    @AllArgsConstructor
    class ForEachPayload {
        private String filedName;
        private Object fieldValue;
    }

    public static void getMethodsForEach(Object target, BiConsumer<String,Object> consumer) {
        Class<?> clazz = target.getClass();
        while (clazz != null && !clazz.equals(Object.class)) {
            for (Method method : clazz.getDeclaredMethods()) {
                if (isGetterMethod(method)) {
                    String propertyName = Introspector.decapitalize(method.getName().substring(3)); // getName → name
                    try {
                        Object value = method.invoke(target);
                        consumer.accept(propertyName, value);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException("getMethodsForEach：调用" + method.getName() + "方法出错", e.getTargetException());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("getMethodsForEach： 调用" + method.getName() + "方法出错", e);
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }

    }

    public static void fieldsForEach(Object target, Consumer<ForEachPayload> consumer) throws InvocationTargetException, IllegalAccessException {
        Class<?> clazz = target.getClass();
        while (clazz != null && !clazz.equals(Object.class)) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                consumer.accept(new ForEachPayload(field.getName(), field.get(target)));
            }
            clazz = clazz.getSuperclass();
        }
    }

    public static void setFieldValue(Object target, String fieldName, Object value) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            try {
                LogUtils.log(
                        EntityUtils.class.getDeclaredMethod("setFieldValue", Object.class, String.class, Object.class),
                        e,
                        target, fieldName, value);
            } catch (NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    public static boolean isGetterMethod(Method method) {
        return method.getName().startsWith("get")
                && method.getParameterCount() == 0
                && !void.class.equals(method.getReturnType());
    }
}


