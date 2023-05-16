package com.mp.venusian.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectMapper {
    public static Map<String, Object> mapObjectForDatabase(Object obj) {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                String name = field.getName();
                Object value = field.get(obj);
                if(name != "id" && value != null) {
                    map.put(name, value);
                }
            } catch (IllegalAccessException e) {
            }
        }
        return map;
    }
}
