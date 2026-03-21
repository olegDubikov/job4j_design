package ru.job4j.ood.lsp.products.service;

import ru.job4j.ood.lsp.products.Food;
import ru.job4j.ood.lsp.products.PercentExpiryDate;
import ru.job4j.ood.lsp.products.storage.Store;

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
}