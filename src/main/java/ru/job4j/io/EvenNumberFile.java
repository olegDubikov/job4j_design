package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] arr = text.toString().split(System.lineSeparator());
            for (var a : arr) {
                int x = Integer.parseInt(a);
                if (x % 2 == 0) {
                    System.out.println(x);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}