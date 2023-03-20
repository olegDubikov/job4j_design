package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        Node<T> el = head;
        if (size == 0) {
            head = new Node<>(value, null);
        } else {
            for (int i = 1; i < size; i++) {
                el = el.next;
            }
            el.next = new Node<>(value, null);
        }
        size++;
        modCount++;
    }

    public void addFirst(T value) {
        Node<T> oldValue = head;
        head = new Node<T>(value, oldValue);
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> el = head;
        for (int i = 0; i < index; i++) {
            el = el.next;
        }
        return el.item;
    }

    public T deleteFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node<T> el = head.next;
        T temp = head.item;
        head.item = null;
        head.next = null;
        head = el;
        size--;
        modCount++;
        return temp;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T result = node.item;
                node = node.next;
                return result;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}