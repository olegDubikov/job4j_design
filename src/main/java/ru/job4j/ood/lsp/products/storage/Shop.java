package ru.job4j.ood.lsp.products.storage;

import ru.job4j.ood.lsp.products.DiscountCalculate;
import ru.job4j.ood.lsp.products.Food;

public class Shop extends AbstractStore {
    @Override
    public boolean accept(Food food) {
        boolean b = food.getExpiryPercent() >= AbstractStore.PERCENT_EXPIRY_MIN
                && food.getExpiryPercent() <= AbstractStore.PERCENT_EXPIRY_MAX;
        if (food.getExpiryPercent() > AbstractStore.PERCENT_EXPIRY_MAX
                && food.getExpiryPercent() <= AbstractStore.PERCENT_EXPIRED) {
            new DiscountCalculate().applyDiscount(food, AbstractStore.DISCOUNT_PERCENT);
            b = true;
        }
        return b;
    }
}