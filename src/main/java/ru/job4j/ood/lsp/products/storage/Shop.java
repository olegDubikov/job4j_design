package ru.job4j.ood.lsp.products.storage;

import ru.job4j.ood.lsp.products.Food;

public class Shop extends AbstractStore {

    @Override
    public boolean accept(Food food) {
        boolean b = false;
        if (!food.isExpired()) {
            if (food.percentExpired() >= 25 && food.percentExpired() <= 75) {
                b = true;
            }
            if (food.percentExpired() > 75) {
                food.applyDiscount(20);
                b = true;
            }
        }
        return b;
    }
}