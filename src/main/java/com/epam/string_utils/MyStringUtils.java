package com.epam.string_utils;

public class MyStringUtils {

    static String concatenate(Object... arg) {
        StringBuilder builder = new StringBuilder();
        for (Object i : arg)
            builder.append(i);
        return builder.toString();
    }

    static String replaceVowels(String text){
        String pattern = "[aeiouyAEIOUY]";
        return text.replaceAll(pattern, "_");
    }

}
