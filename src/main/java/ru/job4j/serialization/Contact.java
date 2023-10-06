package ru.job4j.serialization;

import org.json.JSONObject;

import java.io.*;
import java.util.Objects;

public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    private int zipCode;

    private String phone;

    public Contact() {
    }

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return zipCode == contact.zipCode && Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, phone);
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + '}';
    }

    public static void main(String[] args) {
        final Contact contact = new Contact(12345, "+7 (111) 111-11-11");
        JSONObject jsonFromObject = new JSONObject(contact);
        JSONObject jsonFromLine = new JSONObject("{\"zipCode\":11111,\"phone\":\"+7 (111) 111-11-11\"}");
        System.out.println(jsonFromObject);
        System.out.println(jsonFromLine);
    }
}
