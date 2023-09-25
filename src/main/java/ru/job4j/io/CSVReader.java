package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        try (var scanner = new Scanner(new FileInputStream(argsName.get("path")))
                .useDelimiter(argsName.get("delimiter"))) {
            String delimiter = argsName.get("delimiter");
            String[] filters = argsName.get("filter").split(",");
            String[] splitFilters = scanner.nextLine().split(delimiter);
            String[] finishFilter = new String[splitFilters.length];
            for (var i = 0; i < filters.length; i++) {
                for (String splitFilter : splitFilters) {
                    if (filters[i].equals(splitFilter)) {
                        finishFilter[i] = splitFilter;
                        break;
                    }
                }
            }
            List<String> filterCSV = new ArrayList<>();
            filterCSV.add(argsName.get("filter").replace(",", delimiter));
            while (scanner.hasNextLine()) {
                String[] column = scanner.nextLine().split(delimiter);
                StringJoiner everyString = new StringJoiner(delimiter);
                for (var i = 0; i < column.length; i++) {
                    if (finishFilter[i] != null) {
                        int index = Arrays.asList(splitFilters).indexOf(finishFilter[i]);
                        String print = column[index];
                        everyString.add(print);
                    }
                }
                filterCSV.add(everyString.toString());
            }
            if ("stdout".equals(argsName.get("out"))) {
                printResult(filterCSV);
            } else {
                printInFile(filterCSV, argsName);
            }
        }
    }

    private static void printResult(List<String> forPrint) {
        for (var line : forPrint) {
            System.out.println(line);
        }
    }

    private static void printInFile(List<String> forPrint, ArgsName argsName) throws IOException {
        FileWriter writer = new FileWriter(argsName.get("out"));
        for (var line : forPrint) {
            writer.write(line + System.lineSeparator());
        }
        writer.close();
    }

    private static ArgsName validateArgs(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (!(Files.exists(Path.of(argsName.get("path"))) || argsName.get("path").endsWith(".csv"))) {
            throw new IllegalArgumentException("Error: Файл не существует или имеет неверное расширение");
        }
        if (!argsName.get("delimiter").equals(";") && !argsName.get("delimiter").equals(",")) {
            throw new IllegalArgumentException("Error: Разделителем может быть запятая или точка с запятой");
        }
        if (!("stdout".equals(argsName.get("out")) || argsName.get("out").endsWith(".csv"))) {
            throw new IllegalArgumentException("Error: Некорректно введены аргументы вывода результата");
        }
        if (argsName.get("filter").endsWith("=")) {
            throw new IllegalArgumentException("Error: Не указан фильтр");
        }
        return argsName;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Error: В программу должно быть передано 4 аргумента");
        }
        handle(validateArgs(args));
    }
}