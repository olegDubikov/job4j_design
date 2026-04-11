package ru.job4j.algo.sort;

import java.util.Comparator;
import java.util.List;

public class QuickList {
    public static <T> void quickSort(List<T> sequence, Comparator<T> comparator) {
        quickSort(sequence, 0, sequence.size() - 1, comparator);
    }

    private static <T> void quickSort(List<T> sequence, int start, int end, Comparator<T> comparator) {
        if (start >= end) {
            return;
        }
        int partitionIndex = breakPartition(sequence, start, end, comparator);
        quickSort(sequence, start, partitionIndex - 1, comparator);
        quickSort(sequence, partitionIndex + 1, end, comparator);
    }

    private static <T> int breakPartition(List<T> sequence, int start, int end, Comparator<T> comparator) {
        T supportElement = sequence.get(start);
        int beginToEnd = start + 1;
        int endToBegin = end;

        while (true) {
            while (beginToEnd <= end && comparator.compare(sequence.get(beginToEnd), supportElement) < 0) {
                beginToEnd++;
            }
            while (endToBegin > start && comparator.compare(sequence.get(endToBegin), supportElement) > 0) {
                endToBegin--;
            }
            if (beginToEnd >= endToBegin) {
                break;
            }
            swap(sequence, beginToEnd++, endToBegin--);
        }
        swap(sequence, start, endToBegin);
        return endToBegin;
    }

    private static <T> void swap(List<T> array, int i, int j) {
        T temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}