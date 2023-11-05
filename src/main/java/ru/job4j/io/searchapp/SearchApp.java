package ru.job4j.io.searchapp;

import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class SearchApp {

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        String directory = argsName.get("d");
        String fileName = argsName.get("n");
        String searchType = argsName.get("t");
        String outputFile = argsName.get("o");

        if (directory == null || fileName == null || searchType == null || outputFile == null) {
            System.out.println("-d <каталог> - укажите каталог для поиска");
            System.out.println("-n <имя файла> - укажите имя файла для поиска");
            System.out.println("-t <тип поиска> - укажите тип поиска (mask или name)");
            System.out.println("-o <выходной файл> - укажите файл для записи результатов поиска");
            return;
        }

        final String REGEX_PATTERN = "^[A-Za-z\\d.]{1,255}$";
        if (!fileName.matches(REGEX_PATTERN)) {
            throw new IllegalArgumentException("Недопустимое имя файла: " + fileName);
        }

        Path directoryPath = Paths.get(directory);
        if (!Files.exists(directoryPath) || !Files.isReadable(directoryPath)) {
            throw new IllegalArgumentException("Указанный каталог не существует или недоступен для чтения: "
                    + directory);
        }

        Predicate<Path> condition = switch (searchType) {
            case "mask", "regex" -> file -> file.getFileName().toString().matches(fileName);
            case "name" -> file -> file.getFileName().toString().equals(fileName);
            default -> throw new IllegalArgumentException("Непраильный тип файла: " + searchType);
        };

        SearchFiles searchFiles = new SearchFiles(condition);
        Files.walkFileTree(directoryPath, searchFiles);

        List<Path> result = searchFiles.getList();

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile))) {
            for (Path path : result) {
                writer.write(path.toString());
                writer.newLine();
            }
        }
    }
}
