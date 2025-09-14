package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzLogicTest {
    FizzBuzzLogic logic = new FizzBuzzLogic();

    @Test
    void whenNumberIsThree() {
        String result = logic.isFizzBuzz(3);
        assertEquals("Fizz", result);
    }

    @Test
    void whenNumberIsFive() {
        String result = logic.isFizzBuzz(5);
        assertEquals("Buzz", result);
    }

    @Test
    void whenNumberIsFifteen() {
        String result = logic.isFizzBuzz(15);
        assertEquals("FizzBuzz", result);
    }

    @Test
    void whenNumberIsWrong() {
        String result = logic.isFizzBuzz(16);
        assertEquals("16", result);
    }
}