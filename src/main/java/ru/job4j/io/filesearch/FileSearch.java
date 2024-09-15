package ru.job4j.io.filesearch;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSearch {

    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Необходимо ввести параметры для поиска файлов!");
            System.out.println("Пример использования: -d=c: -n=*.?xt -t=mask -o=log.txt");
            return;
        }

        String directory = "";
        String name = "";
        String type = "";
        String output = "";

        for (String arg : args) {
            String[] param = arg.split("=");
            switch (param[0]) {
                case "-d" -> {
                    directory = param[1];
                }
                case "-n" -> {
                    name = param[1];
                }
                case "-t" -> {
                    type = param[1];
                }
                case "-o" -> {
                    output = param[1];
                }
                default -> throw new IllegalStateException("Unexpected value: " + param[0]);
            }
        }

        if (!directory.isEmpty() && !name.isEmpty() && !type.isEmpty() && !output.isEmpty()) {
            File dir = new File(directory);
            if (!dir.exists()) {
                System.out.println("Указанная директория не существует!");
                return;
            }
            if (type.equals("mask") || type.equals("name") || type.equals("regex")) {
                searchFiles(dir, name, type, output);
            } else {
                System.out.println("Некорректно указан тип поиска!");
                System.out.println("Допустимые значения: mask, name, regex");
            }
        } else {
            System.out.println("Проверьте правильность введенных параметров!");
            System.out.println("Пример использования: -d=c: -n=*.?xt -t=mask -o=log.txt");
        }
    }

    public static void searchFiles(File directory, String name, String type, String output) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                searchFiles(file, name, type, output);
            } else {
                String fileName = file.getName();
                switch (type) {
                    case "mask" -> {
                        if (fileName.matches(name)) {
                            writeToFile(writer, file);
                        }
                    }
                    case "name" -> {
                        if (fileName.equals(name)) {
                            writeToFile(writer, file);
                        }
                    }
                    case "regex" -> {
                        Pattern pattern = Pattern.compile(name);
                        Matcher matcher = pattern.matcher(fileName);
                        if (matcher.matches()) {
                            writeToFile(writer, file);
                        }
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + type);
                }
            }
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(FileWriter writer, File file) {
        try {
            writer.write(file.getAbsolutePath() + " - " + file.length() + " байт\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}