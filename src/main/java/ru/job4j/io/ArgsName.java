package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format(
                    "Error: This key '%s' does not find", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (var s : args) {
            validate(s);
            String[] str = s.split("=", 2);
            values.put(str[0].substring(1), str[1]);
        }
    }

    private void validate(String s) {
        if (!s.contains("=")) {
            throw new IllegalArgumentException(String.format(
                    "Error: This line '%s' does not contain '=' ", s));
        }

        if (!s.startsWith("-")) {
            throw new IllegalArgumentException(String.format(
                    "Error: This line '%s' does not contain '-' ", s));
        }
        if (s.startsWith("-") && s.charAt(1) == '=') {
            throw new IllegalArgumentException(
                    String.format("Error: This line '%s' does not contain a key", s));
        }
        if (s.indexOf('=') == s.length() - 1) {
            throw new IllegalArgumentException(
                    String.format("Error: This line '%s' does not contain a value", s));
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
