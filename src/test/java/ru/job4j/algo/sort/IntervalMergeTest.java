package ru.job4j.algo.sort;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IntervalMergeTest {
    @Test
    void whenIntervalsHaveOverlapsThenMergeOverlappingIntervals() {
        IntervalMerge intervalMerge = new IntervalMerge();
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] expected = new int[][]{{1, 6}, {8, 10}, {15, 18}};
        int[][] result = intervalMerge.merge(intervals);
        assertThat(result).isDeepEqualTo(expected);
    }

    @Test
    void whenIntervalsTouchAtEdgesThenMergeIntervals() {
        IntervalMerge intervalMerge = new IntervalMerge();
        int[][] intervals = new int[][]{{1, 4}, {4, 5}};
        int[][] expected = new int[][]{{1, 5}};
        int[][] result = intervalMerge.merge(intervals);
        assertThat(result).isDeepEqualTo(expected);
    }

    @Test
    void whenIntervalsAreNonOverlappingThenReturnSameIntervals() {
        IntervalMerge intervalMerge = new IntervalMerge();
        int[][] intervals = new int[][]{{1, 2}, {3, 4}, {5, 6}};
        int[][] expected = new int[][]{{1, 2}, {3, 4}, {5, 6}};
        int[][] result = intervalMerge.merge(intervals);
        assertThat(result).isDeepEqualTo(expected);
    }

    @Test
    void whenIntervalsFullyOverlapThenMergeIntoOneInterval() {
        IntervalMerge intervalMerge = new IntervalMerge();
        int[][] intervals = new int[][]{{1, 5}, {2, 6}, {3, 7}};
        int[][] expected = new int[][]{{1, 7}};
        int[][] result = intervalMerge.merge(intervals);
        assertThat(result).isDeepEqualTo(expected);
    }

    @Test
    void whenIntervalsAreMixedThenMergeOverlappingAndKeepNonOverlapping() {
        IntervalMerge intervalMerge = new IntervalMerge();
        int[][] intervals = new int[][]{{1, 4}, {0, 2}, {3, 5}};
        int[][] expected = new int[][]{{0, 5}};
        int[][] result = intervalMerge.merge(intervals);
        assertThat(result).isDeepEqualTo(expected);
    }
}

/*
Временная сложность: O(nlogn).
Объём используемой памяти: O(n).
 */