package com.example.demoexternalservice.utils;

import lombok.var;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IteratorUtils {

    public static <T> List<T> collect(Iterator<T> iterator) {
        List<T> result = new LinkedList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }

    ;

    public static <T> List<T> collect(Iterable<T> iterable) {
        var iterator = iterable.iterator();
        List<T> result = new LinkedList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }

    ;
}
