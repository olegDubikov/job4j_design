package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private final Properties config;
    private final String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
                    throw new IllegalArgumentException("Incorrect data in line" + line);
                }
                users.add(new User(parts[0], parts[1]));
            }
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("driver_class"));
        try (Connection connection = DriverManager.getConnection(config.getProperty("url"), config.getProperty("username"), config.getProperty("password"))) {
            for (User user : users) {
                try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(name, email) VALUES(?, ?)")) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("jdbc.properties")) {
            config.load(in);
        }
        ImportDB dataBase = new ImportDB(config, "data/dump.txt");
        dataBase.save(dataBase.load());
    }
}