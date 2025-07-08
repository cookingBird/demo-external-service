package com.example.demoexternalservice.modules.user.web.request;

import java.util.HashMap;

public class Test {
   static class T1 implements Rest{

   }
    static class T2 implements Rest{

    }
    public static void main(String[] args) {
        Rest rest1 = new T1();
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("foo","1");
        rest1.setRest(objectObjectHashMap);
        Rest rest2 = new T2();
        HashMap<String, String> objectObjectHashMap2 = new HashMap<>();
        objectObjectHashMap2.put("foo","2");
        rest2.setRest(objectObjectHashMap2);
        System.out.println("rest1.getRest() === t2.getRest() : " + (rest1.getRest() == rest2.getRest()));
    }
}
