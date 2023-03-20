package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int count;

    public T poll() {
        if (count == 0) {
            throw new NoSuchElementException();
        }
        out.push(in.pop());
        count--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        count++;
    }
}