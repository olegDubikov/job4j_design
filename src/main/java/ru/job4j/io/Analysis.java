package ru.job4j.io;

import java.io.*;

public class Analysis {

    public void unavailable(String sours, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(sours));
             PrintWriter out = new PrintWriter(target)) {
            StringBuilder builder = new StringBuilder();
            String str = in.readLine();
            int x = 235959;
            int y = 235959;
            int count = 0;

            while (str != null) {
                String[] s = str.split(" ");
                String c = s[1];
                String a = c.replaceAll(":", "");
                if (str.contains("400 ") || str.contains("500 ")) {
                    if (count == 1) {
                        str = in.readLine();
                        continue;
                    }
                    x = Integer.parseInt(a);
                    if (x > y) {
                        builder.append(c).append(";");
                        count++;
                    }
                }
                if (str.contains("200 ") || str.contains("300 ")) {
                    if (count > 1) {
                        str = in.readLine();
                        continue;
                    }
                    y = Integer.parseInt(a);
                    if (y > x) {
                        builder.append(c).append(System.lineSeparator());
                        count--;
                    }
                }
                str = in.readLine();
            }
            out.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}