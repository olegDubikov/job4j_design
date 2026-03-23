package ru.job4j.ood.lsp.products.service;

import ru.job4j.ood.lsp.products.Food;
import ru.job4j.ood.lsp.products.PercentExpiryDate;
import ru.job4j.ood.lsp.products.storage.Store;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void distribute(Food food) {
        PercentExpiryDate percent = new PercentExpiryDate();
        food.setExpiryPercent(percent.percentExpired(food.getCreateDate(), food.getExpireDate()));
        for (Store store : stores) {
            if (store.accept(food)) {
                store.add(food);
                break;
            }
        }
    }

    public void resort() {
        List<Food> foods = new ArrayList<>();
        for (Store store : stores) {
            foods.addAll(store.getFoods());
            store.getFoods().clear();
        }
        for (Food food : foods) {
            distribute(food);
        }
    }
}