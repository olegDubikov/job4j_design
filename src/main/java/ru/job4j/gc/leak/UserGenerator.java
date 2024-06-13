package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {
    public final String pathNames = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    public final String pathSurnames = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    public final String pathPatrons = "src/main/java/ru/job4j/gc/leak/files/patr.txt";

    public final String separator = " ";
    public final int newUsers = 1000;

    public List<String> names;
    public List<String> surnames;
    public List<String> patrons;
    private final List<User> users = new ArrayList<>();
    private final Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    @Override
    public void generate() {
        users.clear();
        try {
            for (int i = 0; i < newUsers; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(surnames.get(random.nextInt(surnames.size())));
                sb.append(separator);
                sb.append(names.get(random.nextInt(names.size())));
                sb.append(separator);
                sb.append(patrons.get(random.nextInt(patrons.size())));
                users.add(new User(sb.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readAll() {
        try {
            names = read(pathNames);
            surnames = read(pathSurnames);
            patrons = read(pathPatrons);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }
}