package ru.job4j.algo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SmallestRangeFinder {
    public static int[] findSmallestRange(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int left = 0;
        int minLength = Integer.MAX_VALUE;
        int[] result = null;
        for (int right = 0; right < nums.length; right++) {
            countMap.put(nums[right], countMap.getOrDefault(nums[right], 0) + 1);
            while (countMap.size() >= k) {
                int currentLength = right - left + 1;
                if (currentLength < minLength) {
                    minLength = currentLength;
                    result = new int[]{left, right};
                }
                countMap.put(nums[left], countMap.get(nums[left]) - 1);
                if (countMap.get(nums[left]) == 0) {
                    countMap.remove(nums[left]);
                }
                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}
/*
Временная сложность: O(n), где n — длина массива.
Каждый элемент добавляется и удаляется из окна не более одного раза.
Пространственная сложность: O(k), так как map хранит k уникальных элементов.
 */