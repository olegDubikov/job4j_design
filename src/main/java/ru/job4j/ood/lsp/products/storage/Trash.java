package ru.job4j.ood.lsp.products.storage;

import ru.job4j.ood.lsp.products.Food;

public class Trash extends AbstractStore {
    @Override
    public boolean accept(Food food) {
        return food.isExpired();
    }
}