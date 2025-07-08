package com.example.demoexternalservice.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

import static com.example.demoexternalservice.utils.ListUtils.forEachWithIndex;

public class LogUtils {
    public static void log(Method method, Exception ex, Object... params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(method.getDeclaringClass().getName());
        stringBuilder.append(":\n");
        stringBuilder.append("Exception: ");
        stringBuilder.append(ex.getClass().getName());
        stringBuilder.append("\n");
        forEachWithIndex(Arrays.asList(params), (index, param) -> {
            stringBuilder.append("Param[");
            stringBuilder.append(index);
            stringBuilder.append("]: ");
            stringBuilder.append(param.toString());
            stringBuilder.append("\n");
        });
        System.out.println(stringBuilder.toString());
    }
}
