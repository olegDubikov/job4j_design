package ru.job4j.serialization;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Car {

    private boolean sedan;

    private int size;

    private String nameFactory;

    private Contact contact;

    private String[] model;

    public Car() {
    }

    public Car(boolean sedan, int size, String nameFactory, Contact contact, String[] model) {
        this.sedan = sedan;
        this.size = size;
        this.nameFactory = nameFactory;
        this.contact = contact;
        this.model = model;
    }

    public boolean isSedan() {
        return sedan;
    }

    public int getSize() {
        return size;
    }

    public String getNameFactory() {
        return nameFactory;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getModel() {
        return model;
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
        List<String> list = new ArrayList<>();
        list.add("VAZ");
        list.add("2114");
        JSONArray jsonModel = new JSONArray(list);
        System.out.println(jsonModel);
        JSONObject jsonContact = new JSONObject("{\"zipCode\":11111,\"phone\":\"+7 (111) 111-11-11\"}");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sedan", car.isSedan());
        jsonObject.put("size", car.getSize());
        jsonObject.put("nameFactory", "VAZ");
        jsonObject.put("contact", jsonContact);
        jsonObject.put("model", jsonModel);
        System.out.println(jsonObject);
        System.out.println(new JSONObject(car));
    }
}