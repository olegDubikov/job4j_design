package ru.job4j.algo.sort;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MergeTest {
    @Test
    void whenSortedThenOk(){
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(-13, 2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    public void testEmptyArray() {
        assertArrayEquals(new int[]{}, Merge.mergesort(new int[]{}));
    }

    @Test
    public void testOneElement() {
        assertArrayEquals(new int[]{1}, Merge.mergesort(new int[]{1}));
    }

    @Test
    public void testTwoElementsSorted() {
        assertArrayEquals(new int[]{1, 2}, Merge.mergesort(new int[]{1, 2}));
    }

    @Test
    public void testTwoElementsUnsorted() {
        assertArrayEquals(new int[]{1, 2}, Merge.mergesort(new int[]{2, 1}));
    }

    @Test
    public void testWithDuplicates() {
        assertArrayEquals(new int[]{1, 2, 2, 3}, Merge.mergesort(new int[]{2, 3, 1, 2}));
    }
}