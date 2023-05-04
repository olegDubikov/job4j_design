package ru.job4j.io;

import java.io.*;

public class Buffered {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("data/newData.txt"));
             BufferedWriter out = new BufferedWriter(new FileWriter("data/output.txt", true))) {
            out.write(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
