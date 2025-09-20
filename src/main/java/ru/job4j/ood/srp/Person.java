package ru.job4j.ood.srp;

import java.util.List;

public class Person {

    List<Person> persons;

    public boolean add(Person p) {
        return persons.add(p);
    }
    public boolean delete(Person p) {
        boolean result = false;
        if (persons.contains(p)) {
            persons.remove(p);
            result = true;
        }
        return result;
    }
}
// нарушение SRP - два метода с добавлением и удалением  и проверка списка в одном классе.