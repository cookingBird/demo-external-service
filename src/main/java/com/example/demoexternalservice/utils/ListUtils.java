package com.example.demoexternalservice.utils;

import java.util.List;
import java.util.function.BiConsumer;

public class ListUtils {
    public static <T> void forEachWithIndex(List<T> list, BiConsumer<Integer, T> action) {
        for (int i = 0; i < list.size(); i++) {
            action.accept(i, list.get(i));
        }
    }
}
