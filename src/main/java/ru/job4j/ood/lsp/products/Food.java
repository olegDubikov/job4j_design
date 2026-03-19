package ru.job4j.ood.lsp.products;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Food {
    private final String name;
    private final LocalDate expiryDate;
    private final LocalDate createDate;
    private double price;
    private double discount;

    public Food(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public double percentExpired() {
        long totalDays = daysBetween(createDate, expiryDate);
        long expiredDays = daysBetween(createDate, LocalDate.now());
        return ((double) expiredDays / totalDays) * 100;
    }

    public void applyDiscount(double percentage) {
        this.discount = percentage;
        this.price *= (1 - discount / 100);
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }


    private long daysBetween(LocalDate from, LocalDate to) {
        return ChronoUnit.DAYS.between(from, to);
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpireDate() {
        return expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Double.compare(food.price, price) == 0 && Double.compare(food.discount, discount) == 0 && Objects.equals(name, food.name) && Objects.equals(expiryDate, food.expiryDate) && Objects.equals(createDate, food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expiryDate, createDate, price, discount);
    }
}