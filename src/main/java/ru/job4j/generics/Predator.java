package ru.job4j.generics;

public class Predator extends Animal {
    private String name1;

    public Predator(String name, String name1) {
        super(name);
        this.name1 = name1;
    }

    @Override
    public String getName() {
        return name1;
    }

    @Override
    public String toString() {
        return "Predator{"
                + "name='" + name1 + '\''
                + '}';
    }
}
