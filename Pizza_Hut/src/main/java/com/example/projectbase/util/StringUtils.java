package com.example.projectbase.util;

public class StringUtils {
    public static boolean isNullOrEmpty(String value) {
        if(value != null && !value.isEmpty()) {
            return false;
        }
        return true;
    }
}
