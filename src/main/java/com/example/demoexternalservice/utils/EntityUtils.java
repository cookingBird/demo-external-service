package com.example.demoexternalservice.utils;

import lombok.Data;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Optional;
import java.util.function.Consumer;

import static ch.qos.logback.core.joran.util.beans.BeanUtil.isGetter;

public class EntityUtils {


    public
    static
    class ForEachPayload {
        private String filedName;
        private Optional<Object> fieldValue;

        public ForEachPayload(String filedName, Object fieldValue) {
            this.filedName = filedName;
            this.fieldValue = Optional.ofNullable(fieldValue);
        }

        public String getFiledName() {
            return filedName;
        }

        public void setFiledName(String filedName) {
            this.filedName = filedName;
        }

        public Optional<Object> getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(Object fieldValue) {
            this.fieldValue = Optional.ofNullable(fieldValue);
        }
    }

    public static void getMethodsForEach(Object target, Consumer<ForEachPayload> consumer) {
        Class<?> clazz = target.getClass();
        while (clazz != null && !clazz.equals(Object.class)) {
            for (Method method : clazz.getMethods()) {
                if (isGetter(method)) {
                    String propertyName = Introspector.decapitalize(method.getName().substring(3)); // getName → name
                    try {
                        Object value = method.invoke(target);
                        consumer.accept(new ForEachPayload(propertyName, value));
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

    public static void setFieldValue(Object target, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    public static boolean isGetterMethod(Method method) {
        return method.getName().startsWith("get")
                && method.getParameterCount() == 0
                && !void.class.equals(method.getReturnType())
                && !method.getName().equals("getClass");
    }
}


