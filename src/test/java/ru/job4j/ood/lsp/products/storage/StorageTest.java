package ru.job4j.ood.lsp.products.storage;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.products.Food;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    private final Food foodFresh = createFood(10.0);
    private final Food foodExpiry = createFood(50.0);
    private final Food foodDiscount = createFood(85.0);
    private final Food foodExpired = createFood(120.0);

    private Food createFood(double expiryPercent) {
        Food food = new Food("Test", LocalDate.now(), LocalDate.now(), 200, 0);
        food.setExpiryPercent(expiryPercent);
        return food;
    }

    @Test
    void whenFoodLess25PercentInWarehouse() {
        Warehouse warehouse = new Warehouse();
        assertTrue(warehouse.accept(foodFresh));
        assertFalse(warehouse.accept(foodExpiry));
        assertFalse(warehouse.accept(foodDiscount));
        assertFalse(warehouse.accept(foodExpired));
    }

    @Test
    void whenFoodMore25PercentAndLess75PercentInShop() {
        Shop shop = new Shop();
        assertFalse(shop.accept(foodFresh));
        assertTrue(shop.accept(foodExpiry));
        assertTrue(shop.accept(foodDiscount));
        assertFalse(shop.accept(foodExpired));
    }

    @Test
    void whenFoodMore75PercentInShopWithDiscount() {
        Shop shop = new Shop();
        assertTrue(shop.accept(foodDiscount));
        assertEquals(160, foodDiscount.getPrice(), 0.01);
    }

    @Test
    void whenFoodExpiredInTrash() {
        Trash trash = new Trash();
        assertFalse(trash.accept(foodFresh));
        assertFalse(trash.accept(foodExpiry));
        assertFalse(trash.accept(foodDiscount));
        assertTrue(trash.accept(foodExpired));
    }
}