package ru.job4j.ood.lsp.products.storage;

import ru.job4j.ood.lsp.products.DiscountCalculate;
import ru.job4j.ood.lsp.products.Food;
import ru.job4j.ood.lsp.products.PercentExpiryDate;

public class Shop extends AbstractStore {
    @Override
    public boolean accept(Food food) {
        PercentExpiryDate percent = new PercentExpiryDate();
        boolean b = false;
        if (!percent.isExpired(food.getExpireDate())) {
            if (percent.percentExpired(food.getCreateDate(), food.getExpireDate()) >= AbstractStore.PERCENT_EXPIRY_MIN && percent.percentExpired(food.getCreateDate(), food.getExpireDate()) <= AbstractStore.PERCENT_EXPIRY_MAX) {
                b = true;
            }
            if (percent.percentExpired(food.getCreateDate(), food.getExpireDate()) > AbstractStore.PERCENT_EXPIRY_MAX) {
                DiscountCalculate discountCalculate = new DiscountCalculate();
                discountCalculate.applyDiscount(food, AbstractStore.DISCOUNT_PERCENT);
                b = true;
            }
        }
        return b;
    }
}