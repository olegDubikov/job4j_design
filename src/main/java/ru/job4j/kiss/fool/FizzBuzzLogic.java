package ru.job4j.kiss.fool;

public class FizzBuzzLogic {
    public String isFizzBuzz(int number) {
        StringBuilder result = new StringBuilder();
        if (number % 3 == 0) {
            result.append("Fizz");
        }
        if (number % 5 == 0) {
            result.append("Buzz");
        }
        if (result.length() == 0) {
            result.append(number);
        }
        return result.toString();
    }
}