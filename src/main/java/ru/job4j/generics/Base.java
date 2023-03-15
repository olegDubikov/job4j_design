package ru.job4j.generics;

public abstract class Base {
    private final String id;

    public Base(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}