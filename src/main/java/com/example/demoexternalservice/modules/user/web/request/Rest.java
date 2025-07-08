package com.example.demoexternalservice.modules.user.web.request;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @deprecated
 */
public interface Rest {
     Map<String,String> rest = new HashMap<>();

     default Map<String, String> getRest() {
         System.out.println(this);
        return rest;
    }

     default void setRest(Map<String, String> res) {
         rest.putAll(res);
    }
}
