package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            var x = 1;
            while (x <= 10) {
                out.write(("1*" + x + "=" + x).getBytes());
                out.write(System.lineSeparator().getBytes());
                x++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}