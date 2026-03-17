package ru.job4j.ood.lsp;

public class Example2 {
    public class Integer {
        private int n = 150000;

        public void num(int n) {
            System.out.println(n);
        }
    }

    public class Number extends Integer {
        private int n;

        @Override
        public void num(int n) {
            System.out.println((byte) n);
        }
    }
}
/*
в классе наследнике, метод num ограничивает метод родительского класса
 */