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
                if (str.startsWith("#") || str.isBlank()) {
                    continue;
                }
                validate(str);
                String[] arr = str.split("=", 2);
                values.put(arr[0], arr[1]);
                str = read.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validate(String s) {
        if (s.length() > 1 && s.contains("=")) {
            if (s.startsWith("=")) {
                throw new IllegalArgumentException("No key");
            }
            if (s.indexOf('=') == s.length() - 1) {
                throw new IllegalArgumentException("No value");
            }
        } else if (!s.contains("=")) {
            throw new IllegalArgumentException("This is just line");
        } else if (s.startsWith("=") && s.length() == 1) {
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