package ru.job4j.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ForwardLinkedTest {

    private ForwardLinked<Integer> list;

    @BeforeEach
    public void initData() {
        list = new ForwardLinked<>();
        list.add(1);
        list.add(2);
    }

    @Test
    void checkIteratorSimple() {
        assertThat(list).hasSize(2);
        list.add(3);
        list.add(4);
        assertThat(list).hasSize(4);
    }

    @Test
    void checkAddAndAddFirst() {
        assertThat(list).containsExactly(1, 2);
        list.add(3);
        list.addFirst(4);
        assertThat(list).containsExactly(4, 1, 2, 3);
    }

    @Test
    void whenAddAndGet() {
        list.add(3);
        list.add(4);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(2)).isEqualTo(3);
        assertThat(list.get(3)).isEqualTo(4);
    }

    @Test
    void whenGetFromOutOfBoundThenExceptionThrown() {
        assertThatThrownBy(() -> list.get(2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenGetNegateIndexThenExceptionThrown() {
        assertThatThrownBy(() -> list.get(-1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAndDeleteFirstThenOk() {
        assertThat(list).containsExactly(1, 2);
        list.add(3);
        assertThat(list).containsExactly(1, 2, 3);
        list.deleteFirst();
        assertThat(list).containsExactly(2, 3);
        list.deleteFirst();
        assertThat(list).containsExactly(3);
    }

    @Test
    void whenDeleteFirstFromEmptyListThenException() {
        ForwardLinked<Integer> list = new ForwardLinked<>();
        assertThat(list).isEmpty();
        assertThatThrownBy(list::deleteFirst)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenAddIteratorHasNextTrue() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    void whenHasIteratorAndAddThenHasNextExceptionThrown() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isTrue();
        list.add(3);
        assertThatThrownBy(it::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenHasIteratorAndAddThenNextExceptionThrown() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isTrue();
        list.add(3);
        assertThatThrownBy(it::next)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenAddIteratorNextOne() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.next()).isEqualTo(1);
    }

    @Test
    void whenEmptyIteratorHashNextFalse() {
        ForwardLinked<Integer> list = new ForwardLinked<>();
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isFalse();
        assertThat(it.hasNext()).isFalse();
    }

    @Test
    void whenAddIteratorMultiHasNextTrue() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isTrue();
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    void whenAddIteratorNextOneNextTwo() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
    }

    @Test
    void whenGetIteratorTwiceThenEveryFromBegin() {
        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext()).isTrue();
        assertThat(first.next()).isEqualTo(1);
        assertThat(first.hasNext()).isTrue();
        assertThat(first.next()).isEqualTo(2);
        assertThat(first.hasNext()).isFalse();
        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext()).isTrue();
        assertThat(second.next()).isEqualTo(1);
        assertThat(second.hasNext()).isTrue();
        assertThat(second.next()).isEqualTo(2);
        assertThat(second.hasNext()).isFalse();
    }

    @Test
    void whenDeleteFirstAndHasNextException() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isTrue();
        list.deleteFirst();
        assertThatThrownBy(it::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenAddFirstAndNextException() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isTrue();
        list.addFirst(1);
        assertThatThrownBy(it::next)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenListIsEmptyAndAddFirstAndGet() {
        ForwardLinked<Integer> list = new ForwardLinked<>();
        list.addFirst(2);
        list.addFirst(1);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
    }
}