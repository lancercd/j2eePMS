package com.j2ee.utils;

public class StringUtils {

    public static boolean isEmpty(String str){
        return str == null || str.trim().length() == 0;
    }

}
