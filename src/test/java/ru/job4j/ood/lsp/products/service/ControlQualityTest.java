package ru.job4j.ood.lsp.products.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.products.Food;
import ru.job4j.ood.lsp.products.storage.Shop;
import ru.job4j.ood.lsp.products.storage.Trash;
import ru.job4j.ood.lsp.products.storage.Warehouse;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControlQualityTest {

    private Warehouse warehouse;
    private Shop shop;
    private Trash trash;

    private final Food foodFresh = new Food("milk", LocalDate.now()
            .plusDays(30), LocalDate.now(), 100, 0);
    private final Food foodExpiry = new Food("bread", LocalDate.now()
            .plusDays(8), LocalDate.now()
            .minusDays(4), 70, 0);
    private final Food foodDiscount = new Food("meat", LocalDate.now()
            .plusDays(85), LocalDate.now()
            .minusDays(300), 200, 20);
    private final Food foodExpired = new Food("fish", LocalDate.now()
            .minusDays(3), LocalDate.now()
            .minusDays(10), 180, 0);

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse();
        shop = new Shop();
        trash = new Trash();
        ControlQuality control = new ControlQuality(List.of(warehouse, shop, trash));

        control.distribute(foodFresh);
        control.distribute(foodExpiry);
        control.distribute(foodDiscount);
        control.distribute(foodExpired);
    }

    @Test
    void whenFoodInWarehouse() {
        assertTrue(warehouse.getFoods().contains(foodFresh));
        assertFalse(warehouse.getFoods().contains(foodExpiry));
        assertFalse(warehouse.getFoods().contains(foodDiscount));
        assertFalse(warehouse.getFoods().contains(foodExpired));
    }

    @Test
    void whenFoodInShop() {
        assertFalse(shop.getFoods().contains(foodFresh));
        assertTrue(shop.getFoods().contains(foodExpiry));
        assertTrue(shop.getFoods().contains(foodDiscount));
        assertFalse(shop.getFoods().contains(foodExpired));
    }

    @Test
    void whenFoodInTrash() {
        assertFalse(trash.getFoods().contains(foodFresh));
        assertFalse(trash.getFoods().contains(foodExpiry));
        assertFalse(trash.getFoods().contains(foodDiscount));
        assertTrue(trash.getFoods().contains(foodExpired));
    }
}