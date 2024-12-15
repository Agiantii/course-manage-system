package com.agiantii.backend.utils;

public class StringUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0 || str=="" || str =="null";
    }
}
