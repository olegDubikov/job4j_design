package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("driver_class"));
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private void querySQL(String query, Object... args) {
        try (var statement = connection.createStatement()) {
            statement.execute(String.format(query, args));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream(
                "jdbc.properties"
        )) {
            config.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(config)) {
            tableEditor.createTable("start");
            System.out.println(tableEditor.getTableScheme("start"));
            tableEditor.createTable("test");
            System.out.println(tableEditor.getTableScheme("test"));
            tableEditor.dropTable("start");
            tableEditor.addColumn("test", "last_name", "text");
            System.out.println(tableEditor.getTableScheme("test"));
            tableEditor.dropColumn("test", "column1");
            System.out.println(tableEditor.getTableScheme("test"));
            tableEditor.renameColumn("test", "column3", "first_name");
            System.out.println(tableEditor.getTableScheme("test"));
        }
    }

    public void createTable(String tableName) {
        querySQL("CREATE TABLE IF NOT EXISTS %s (column1 int, column2 text, column3 text)",
                tableName);
    }

    public void dropTable(String tableName) {
        querySQL("DROP TABLE %s", tableName);
    }

    public void addColumn(String tableName, String columnName, String type) {
        querySQL("ALTER TABLE %s ADD COLUMN %s %s",
                tableName, columnName, type);
    }

    public void dropColumn(String tableName, String columnName) {
        querySQL("ALTER TABLE %s DROP COLUMN %s",
                tableName, columnName);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        querySQL("ALTER TABLE %s RENAME COLUMN %s TO %s",
                tableName, columnName, newColumnName);
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}