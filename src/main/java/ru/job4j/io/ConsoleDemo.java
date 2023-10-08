package ru.job4j.io;

import java.io.Console;
import java.util.Arrays;

public class ConsoleDemo {
    public static void main(String[] args) {
        String login;
        char[] password;
        Console console = System.console();
        if (console == null) {
            System.out.println("No access");
            return;
        }
        login = console.readLine("%s", "Введите логин: ");
        console.printf("Ваш логин: %s\n", login);
        password = console.readPassword("%s", "Введите пароль: ");
        System.out.println("Ваш пароль: " + String.valueOf(password));
        Arrays.fill(password, ' ');
    }
}
