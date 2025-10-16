package com.gabrielwbenattidev.carwashx.core.util;

import org.springframework.stereotype.Component;

@Component
public class StringUtils {

    public static String onlyAlphanumeric(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("[^a-zA-Z0-9]", "");
    }

}
