package ru.job4j.ood.ocp;

public class Example2 {
    public class Cat {
        public void sound() {
            System.out.println("Myau");
        }
    }
    public class AnimalSound {
        public void voice(Cat cat) {
            cat.sound();
        }
    }
}
/*
Метод voice жестко завязан на конкретный класс Cat.
Если захотим добавить класс собаку (Dog), придется изменять класс AnimalSound
 */