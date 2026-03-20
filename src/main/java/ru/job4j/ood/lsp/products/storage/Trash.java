package ru.job4j.ood.lsp.products.storage;

import ru.job4j.ood.lsp.products.Food;
import ru.job4j.ood.lsp.products.PercentExpiryDate;

public class Trash extends AbstractStore {
    @Override
    public boolean accept(Food food) {
        PercentExpiryDate percent = new PercentExpiryDate();
        return percent.isExpired(food.getExpireDate());
    }
}