package ru.job4j.ood.ocp;

public class Example3 {
    public class CheckInt {
        public void checkInt(int i) {
            if (i == 1) {
                System.out.println(1);
            }
            if (i == 2) {
                System.out.println(2);
            }
        }
    }
}
/*
метод checkInt не соответствует принципу OCP, если надо будет проверять число 3,
 то придется добавлять еще один if. Итак каждый раз при добавлении другого числа.
 */