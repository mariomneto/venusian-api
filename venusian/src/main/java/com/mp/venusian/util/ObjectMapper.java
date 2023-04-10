package com.mp.venusian.util;

import com.google.cloud.Timestamp;
import com.mp.venusian.models.UserModel;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ObjectMapper {
    public static Map<String, Object> mapObjectForDatabase(UserModel model) {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = model.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                String name = field.getName();
                Object value = field.get(model);
                if(name != "id" && value != null) {
                    if(value instanceof Enum) {
                        value = ((Enum<?>) value).name();
                    }
                    map.put(name, value);
                }
            } catch (IllegalAccessException e) {
            }
        }
        return map;
    }
}
