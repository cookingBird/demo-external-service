package com.example.demoexternalservice.utils;

import java.lang.reflect.Method;
import java.util.Optional;

public class ClassUtils {
    public static Optional<Method> getMethodSafe(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        try {
            return Optional.of(clazz.getDeclaredMethod(methodName, parameterTypes));
        } catch (NoSuchMethodException e) {
            System.out.println("com.example.demoexternalservice.utils.getMethodSafe");
        }
        return Optional.empty();
    }


}
