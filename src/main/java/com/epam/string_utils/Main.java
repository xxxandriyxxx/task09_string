package com.epam.string_utils;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("------------------------ concatenate()");
        System.out.println("1, 2,\"aaa\",3, 4, 5F, \"bJnbhU\"");
        System.out.println(MyStringUtils.concatenate(1, 2,"aaa",3, 4, 5F, "bJnbhU"));

        String str = "aAm JkoUbbey BnOnnI mnsE";
        System.out.println("------------------------ replaceVowels()");
        System.out.println(str);
        System.out.println(MyStringUtils.replaceVowels(str));

        String str1 = "zxxtheAsd YOUaaa Thehjs kkmsxHTE youDkfd";
        System.out.println("------------------------ split() regex: \"the|you\"");
        System.out.println(str1);
        System.out.println(Arrays.toString(MyStringUtils.split(str1, "the|you")));

        String str2 = "hello world";
        String str3 = "Hello world";
        String str4 = "hello world.";
        String str5 = "Hello world.";
        System.out.println("------------------------ check() regex: \"[A-Z].*\\.\"");
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(str4);
        System.out.println(str5);
        System.out.println("---Result:");
        System.out.println(MyStringUtils.check(str2));
        System.out.println(MyStringUtils.check(str3));
        System.out.println(MyStringUtils.check(str4));
        System.out.println(MyStringUtils.check(str5));
    }
}
