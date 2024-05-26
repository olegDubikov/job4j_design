package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Emulator {

    private static final Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("Выбирете директорию: ");
        String dirPath = sc.nextLine();
        DirFileCache dirFileCache = new DirFileCache(dirPath);

        boolean exit = false;
        while (!exit) {
            System.out.println("Введите 1. Чтобы получить кеш файла");
            System.out.println("Введите 2. Для выхода из программы");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> {
                    System.out.println("Введите имя файла с расширением:");
                    String fileName = sc.nextLine();
                    String content = dirFileCache.get(fileName);
                    if (Files.isDirectory(Path.of(dirPath))) {
                        System.out.println(content);
                    } else {
                        throw new IllegalArgumentException(String.format("%s нет такой директории", dirPath));
                    }
                }
                case 2 -> {
                    exit = true;
                }
                default -> System.out.println("Выбирайте 1 или 2");
            }
        }
    }
}