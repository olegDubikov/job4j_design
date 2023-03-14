package ru.job4j.generics;

public class Tiger extends Predator {
    private String name2;

    public Tiger(String name, String name1, String name2) {
        super(name, name1);
        this.name2 = name2;
    }

    @Override
    public String getName() {
        return name2;
    }

    @Override
    public String toString() {
        return "Tiger{" + "name='" + name2 + '\'' + '}';
    }
}
