package com.epam.string_utils;

public class MyStringUtils {

    static String concatenate(Object... arg) {
        StringBuilder builder = new StringBuilder();
        for (Object i : arg)
            builder.append(i);
        return builder.toString();
    }
    

}
