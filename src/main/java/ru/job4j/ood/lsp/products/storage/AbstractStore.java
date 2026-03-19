package ru.job4j.ood.lsp.products.storage;

import ru.job4j.ood.lsp.products.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    protected List<Food> foods = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        return foods.add(food);
    }

    @Override
    public abstract boolean accept(Food food);

    public List<Food> getFoods() {
        return foods;
    }
}