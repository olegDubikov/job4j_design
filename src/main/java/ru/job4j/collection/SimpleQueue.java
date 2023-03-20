package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inCount;
    private int outCount;

    public T poll() {
        if (outCount == 0) {
            while (inCount != 0) {
                out.push(in.pop());
                inCount--;
                outCount++;
            }
            outCount--;
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        inCount++;
    }
}