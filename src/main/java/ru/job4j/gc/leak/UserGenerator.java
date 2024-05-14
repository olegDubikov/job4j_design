package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {
    public final String PATH_NAMES = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    public final String PATH_SURNAMES = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    public final String PATH_PATRONS = "src/main/java/ru/job4j/gc/leak/files/patr.txt";

    public final String SEPARATOR = " ";
    public final int NEW_USERS = 1000;

    public List<String> names;
    public List<String> surnames;
    public List<String> patrons;
    private List<User> users = new ArrayList<>();
    private Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    @Override
    public void generate() {
        users.clear();
        try {
            for (int i = 0; i < NEW_USERS; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(surnames.get(random.nextInt(surnames.size())));
                sb.append(SEPARATOR);
                sb.append(names.get(random.nextInt(names.size())));
                sb.append(SEPARATOR);
                sb.append(patrons.get(random.nextInt(patrons.size())));
                users.add(new User(sb.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readAll() {
        try {
            names = read(PATH_NAMES);
            surnames = read(PATH_SURNAMES);
            patrons = read(PATH_PATRONS);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }
}
