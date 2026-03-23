package ru.job4j.ood.lsp.products.storage;

import ru.job4j.ood.lsp.products.Food;

import java.util.List;

public interface Store {
    boolean add(Food food);

    boolean accept(Food food);
    List<Food> getFoods();
}