package ru.job4j.ood.lsp;

public class Example1 {
    public class Cat {
        public void sound() {
            System.out.println("meow");
        }
    }
    public class Dog extends Cat {
        private String name;
        @Override
        public void sound() {
            System.out.println(name);
        }
    }
}
/*
класс наследник меняет поведение метода родительского класса
 */