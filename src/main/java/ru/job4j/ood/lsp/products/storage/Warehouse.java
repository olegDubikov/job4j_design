package ru.job4j.ood.lsp.products.storage;

import ru.job4j.ood.lsp.products.Food;

public class Warehouse extends AbstractStore {
    @Override
    public boolean accept(Food food) {
        return food.getExpiryPercent() < AbstractStore.PERCENT_EXPIRY_MIN;
    }
}