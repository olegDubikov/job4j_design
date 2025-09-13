package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.job4j.kiss.fool.Fool.isFizzBuzz;

class FoolTest {
    @Test
    void whenNumberIsThree() {
        String result = isFizzBuzz(3);
        assertEquals("Fizz", result);
    }
    @Test
    void whenNumberIsFive() {
        String result = isFizzBuzz(5);
        assertEquals("Buzz", result);
    }
    @Test
    void whenNumberIsFifteen() {
        String result = isFizzBuzz(15);
        assertEquals("FizzBuzz", result);
    }
    @Test
    void whenNumberIsWrong() {
        String result = isFizzBuzz(16);
        assertEquals("16", result);
    }
}