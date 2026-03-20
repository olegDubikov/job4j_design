package ru.job4j.ood.lsp.products;

public class DiscountCalculate {
    public void applyDiscount(Food food, double discount) {
        food.setPrice(food.getPrice() * (1- discount / 100));
        food.setDiscount(discount);
    }
}