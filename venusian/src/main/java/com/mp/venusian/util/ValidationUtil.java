package com.mp.venusian.util;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidationUtil {
    public static boolean validateEmail(String email) {
        String regexPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

    public static boolean validatePhone(String phone) {
        String regexPattern = "^(?:(?:\\+|00)?(55)\\s?)?(?:\\(?([1-9][0-9])\\)?\\s?)(?:((?:9\\s?\\d|[2-9])\\d{3})\\-?(\\d{4}))$";
        return Pattern.compile(regexPattern)
                .matcher(phone)
                .matches();
    }
}
