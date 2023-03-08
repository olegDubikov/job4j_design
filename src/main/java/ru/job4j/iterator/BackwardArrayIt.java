package ru.job4j.iterator;

import java.util.Iterator;

public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point;

    public BackwardArrayIt(int[] data) {
        this.data = data;
        this.point = data.length - 1;
    }

    @Override
    public boolean hasNext() {
        return point > 0;
    }

    @Override
    public Integer next() {
        if (point < 0) {
            throw new NoSuchFieldError("Data is empty");
        }
        return data[point--];
    }
}