package ru.job4j.ood.lsp.products.service;

import ru.job4j.ood.lsp.products.Food;
import ru.job4j.ood.lsp.products.storage.Store;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void distribute(Food food) {
        for (Store store : stores) {
            if (store.accept(food)) {
                store.add(food);
                break;
            }
        }
    }
}