package ru.job4j.ood.lsp.products.storage;

import ru.job4j.ood.lsp.products.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {

    public static final int PERCENT_EXPIRY_MIN = 25;
    public static final int PERCENT_EXPIRY_MAX = 75;
    public static final int PERCENT_EXPIRED = 100;

    public static final double DISCOUNT_PERCENT = 20;

    protected List<Food> foods = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        return foods.add(food);
    }

    @Override
    public abstract boolean accept(Food food);

    @Override
    public List<Food> getFoods() {
        return new ArrayList<>(foods);
    }
}