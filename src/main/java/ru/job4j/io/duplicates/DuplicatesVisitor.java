package ru.job4j.io.duplicates;

import java.util.Map;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<String>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().getName(), file.toFile().length());
        map.putIfAbsent(fileProperty, new ArrayList<>());
        map.get(fileProperty).add(file.toFile().getAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public void view() {
        for (FileProperty key : map.keySet()) {
            if (map.get(key).size() > 1) {
                System.out.println(key.getName() + " - " + key.getSize());
                map.get(key).forEach(System.out::println);
            }
        }
    }
}