package ru.job4j.generics;

public class Role extends Base {
    private final String name;

    public Role(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}