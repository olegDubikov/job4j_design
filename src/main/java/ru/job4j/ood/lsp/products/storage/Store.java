package ru.job4j.ood.lsp.products.storage;

import ru.job4j.ood.lsp.products.Food;

public interface Store {
    boolean add(Food food);

    boolean accept(Food food);
}