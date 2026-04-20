package ru.job4j.algo.hash;

import java.util.HashMap;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0;
        int maxLength = 0;
        int maxStart = 0;

        for (int end = 0; end < str.length(); end++) {
            char currentChar = str.charAt(end);
            if (map.containsKey(currentChar)) {
                start = Math.max(start, map.get(currentChar) + 1);
            }
            map.put(currentChar, end);
            if (end - start + 1 > maxLength) {
                maxLength = end - start + 1;
                maxStart = start;
            }
        }
        return str.substring(maxStart, maxStart + maxLength);
    }
}
// change 3
/*
Временная сложность: O(n), где n — длина входной строки.
Память: O(k), где k кол-во уникальных символов, в худшем случае
O(n) когда вся строка состоит из уникальных символов.
 */