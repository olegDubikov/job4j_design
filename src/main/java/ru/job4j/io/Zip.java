package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Need three arguments");
        }
        if (args[0].charAt(1) != 'd') {
            throw new IllegalArgumentException("Wrong key directory");
        }
        if (args[1].charAt(1) != 'e') {
            throw new IllegalArgumentException("Wrong key file");
        }
        if (args[2].charAt(1) != 'o') {
            throw new IllegalArgumentException("Wrong key archive");
        }
        File file = new File(getArgsValue(args, "d"));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }

    }

    private static String getArgsValue(String[] args, String key) {
        ArgsName argsValue = ArgsName.of(args);
        return argsValue.get(key);
    }


    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        validate(args);
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        List<Path> sources = new ArrayList<>();
        Path root = Paths.get(".");
        Search.search(root, p -> !p.toFile().getName().endsWith(getArgsValue(args, "e")));
        zip.packFiles(sources, new File(getArgsValue(args, "o")));
    }
}