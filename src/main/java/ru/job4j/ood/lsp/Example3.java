package ru.job4j.ood.lsp;

public class Example3 {
    public class Car {
        public void fuel() {
            System.out.println("Бензин");
        }
    }

    public class Bycicle extends Car {
        @Override
        public void fuel() {
            throw new UnsupportedOperationException("Не нуждается в топливе");
        }
    }
}
/*
класс наследник не может заменить класс родителя,
по принципу подстановки Лисков,
т.к. нарушает логику родительского класса.
 */