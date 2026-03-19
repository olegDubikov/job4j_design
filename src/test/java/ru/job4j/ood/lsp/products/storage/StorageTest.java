package ru.job4j.ood.lsp.products.storage;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.products.Food;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
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