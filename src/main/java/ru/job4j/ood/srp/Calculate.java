package ru.job4j.ood.srp;

public class Calculate {
    public int sum(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }
}

//В данном примере нарушение SRP состоит в том что класс отвечает за две разные операции.