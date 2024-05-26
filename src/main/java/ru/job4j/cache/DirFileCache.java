package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.readString;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String result = null;
        try {
            Path path = Path.of(cachingDir, key);
            result = readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}