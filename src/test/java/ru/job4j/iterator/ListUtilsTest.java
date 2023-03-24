package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> list1;
    private List<Integer> list2;

    @BeforeEach
    void setUpList1() {
        list1 = new ArrayList<>(Arrays.asList(1, 3));
    }

    @BeforeEach
    void setUpList2() {
        list2 = new ArrayList<>(Arrays.asList(2, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(list1, 1, 2);
        assertThat(list1).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(list1, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(list1, 0, 2);
        assertThat(list1).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(list1, 2, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.removeIf(list1, e -> e.equals(3));
        assertThat(list1).hasSize(1).containsSequence(1);

    }

    @Test
    void whenReplaceIf() {
        ListUtils.replaceIf(list1, e -> e.equals(1), 2);
        assertThat(list1).hasSize(2).containsSequence(2, 3);
    }

    @Test
    void whenRemoveAll() {
        ListUtils.removeAll(list1, list2);
        assertThat(list1).hasSize(2).containsSequence(1, 3);
    }
}