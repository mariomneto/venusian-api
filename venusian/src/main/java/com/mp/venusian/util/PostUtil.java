package com.mp.venusian.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PostUtil {
    public String generateRandomId() {
        Random rand = new Random();
        String str = rand.ints(48, 123)
            .filter(num -> (num<58 || num>64) && (num<91 || num>96))
            .limit(6)
            .mapToObj(c -> (char)c).collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)
            .toString();
        return str;
    }

    public String generateRandomColor() {
        Random rand = new Random();
        int color = rand.nextInt(0xffffff + 1);
        String hex = String.format("#%06x", color);
        return hex;
    }
}
