package ru.job4j.ood.srp;

public class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void voice() {
        Animal dog = new Animal("Dog", 5);
        System.out.println("Wow");
    }
}
// нарушение SRP - создание объекта и реализация поведенияв одном классе.