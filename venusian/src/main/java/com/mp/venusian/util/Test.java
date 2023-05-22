package com.mp.venusian.util;

import java.util.regex.Pattern;

public class Test {
    public static boolean testEmail(String email) {
        String regexPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

    public static boolean testPhone(String phone) {
        String regexPattern = "^(?:(?:\\+|00)?(55)\\s?)?(?:\\(?([1-9][0-9])\\)?\\s?)(?:((?:9\\s?\\d|[2-9])\\d{3})\\-?(\\d{4}))$";
        return Pattern.compile(regexPattern)
                .matcher(phone)
                .matches();
    }
}
