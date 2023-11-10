package ru.job4j.io.searchapp;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SearchApp {

    public static void main(String[] args) throws IOException {
        SearchApp searchApp = new SearchApp();
        searchApp.checkLength(args);
        ArgsName argsName = ArgsName.of(args);
        String directory = argsName.get("d");
        String name = argsName.get("n");
        String type = argsName.get("t");
        String out = argsName.get("o");
        Path start = Paths.get(directory);
        searchApp.validate(directory, name, type, out);
        List<Path> list = new ArrayList<>();

        switch (type) {
            case ("name") -> list = Search.search(start, s -> s.toFile().getName().contains(name));
            case ("regex") -> {
                Pattern pattern = Pattern.compile(name);
                list = Search.search(start, s -> pattern.matcher(s.toString()).find());
            }
            case ("mask") -> {
                String str = name.replace("*", "[a-zA-Z0-9]");
                Pattern pattern = Pattern.compile(str);
                list = Search.search(start, s -> pattern.matcher(s.toString()).find());
            }
            default -> System.out.println("Enter the correct search type");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(
                "C:\\projects\\job4j_design\\data\\" + out))) {
            for (Path path : list) {
                File file = path.toFile();
                bw.write(file.getAbsolutePath() + " Size: " + file.length() + " bytes");
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkLength(String[] strings) {
        if (strings.length !=4 ) {
            throw new IllegalArgumentException(
                    "the arguments must be " +
                            "-d =<directory> -n=<file name> " +
                            "-t=<mask> -o=<file search result with extension .txt>");
        }
    }

    public void validate(String s1, String s2, String s3, String s4) {
        File file = new File(s1);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Directory does not exist: %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("The path is not a directory: %s", file.getAbsoluteFile()));
        }
        if (s2 == null || s2.isEmpty()) {
            throw new IllegalArgumentException("The file name to search was not specified.");
        }
        if (s3 == null || !(s3.equals("mask") || s3.equals("name") || s3.equals("regex"))) {
            throw new IllegalArgumentException("The correct search type is not specified (mask, name или regex).");
        }
        if (s4 == null) {
            throw new IllegalArgumentException(
                    "Specify a file to record search results with the extension .txt");
        }
    }
}