package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Car {
    private final boolean sedan;
    private final int size;
    private final String nameFactory;
    private final Contact contact;
    private final String[] model;

    public Car(boolean sedan, int size, String nameFactory, Contact contact, String[] model) {
        this.sedan = sedan;
        this.size = size;
        this.nameFactory = nameFactory;
        this.contact = contact;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{"
                + "sedan=" + sedan
                + ", size=" + size
                + ", nameFactory='" + nameFactory + '\''
                + ", contact=" + contact
                + ", model=" + Arrays.toString(model)
                + '}';
    }

    public static void main(String[] args) {
        final Car car = new Car(true, 5, "BMW",
                new Contact(1, "12345"), new String[]{"Z", "330"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));
        final String carJson =
                "{"
                        + "\"sedan\":false,"
                        + "\"size\":3,"
                        + "\"nameFactory\":\"VAZ\","
                        + "\"contact\":"
                        + "{"
                        + "\"zipCode\":7,"
                        + "\"phone\":\"1233\""
                        + "},"
                        + "\"model\":"
                        + "[\"Samara\", \"2114\"]"
                        + "}";
        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}