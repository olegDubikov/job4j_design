package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {
    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var input = new Scanner(System.in);
        FizzBuzzLogic logic = new FizzBuzzLogic();
        while (startAt < 100) {
            System.out.println(logic.isFizzBuzz(startAt));
            startAt++;
            var answer = input.nextLine();
            if (!logic.isFizzBuzz(startAt).equals(answer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }
}