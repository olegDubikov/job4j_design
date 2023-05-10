package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String str = read.readLine();
            while (str != null) {
                validate(str);
                if (!str.startsWith("#") && str.contains("=")) {
                    String[] arr = str.split("=", 2);
                    values.put(arr[0], arr[1]);
                }
                str = read.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validate(String s) {
        String[] arr = s.split("=", 2);
        if (!s.contains("=") && !s.startsWith("#") && !s.isEmpty()) {
            throw new IllegalArgumentException("This is just line");
        }
        if (!s.startsWith("#") && s.contains("=") && s.length() > 1) {
            if (arr[0].length() == 0) {
                throw new IllegalArgumentException("No key");
            } else if (arr[1].length() == 0) {
                throw new IllegalArgumentException("No value");
            }
        }
        if (s.length() == 1) {
            throw new IllegalArgumentException("Line contain only \"=\"");
        }
    }

    public String value(String key) {
        String rsl;
        if (values.containsKey(key)) {
            rsl = values.get(key);
        } else {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return rsl;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}