package com.example.demoexternalservice.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MethodUtils {
    static public Boolean isPublic(Method method) {
        return Modifier.isPublic(method.getModifiers());
    }

    static public Boolean isPrivate(Method method) {
        return Modifier.isPrivate(method.getModifiers());
    }

    static public Boolean isStatic(Method method) {
        return Modifier.isStatic(method.getModifiers());
    }

    static public Boolean isNonStatic(Method method) {
        return !isStatic(method);
    }

    public static boolean isFromObject(Method method) {
        return method.getDeclaringClass().equals(Object.class);
    }

    public static boolean isFrom(Method method, Class<?> clazz) {
        return method.getDeclaringClass().equals(clazz);
    }
}
