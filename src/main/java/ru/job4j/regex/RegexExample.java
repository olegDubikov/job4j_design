package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("11");

        String text1 = "111111";
        Matcher matcher1 = pattern.matcher(text1);
        while (matcher1.find()) {
            System.out.println(matcher1.group());
        }
    }
}