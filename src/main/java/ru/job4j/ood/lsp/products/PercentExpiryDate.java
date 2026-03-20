package ru.job4j.ood.lsp.products;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PercentExpiryDate {

    public boolean isExpired(LocalDate expiryDate) {
        return LocalDate.now().isAfter(expiryDate);
    }

    public double percentExpired(LocalDate createDate, LocalDate expiryDate) {
        long totalDays = daysBetween(createDate, expiryDate);
        long expiredDays = daysBetween(createDate, LocalDate.now());
        return ((double) expiredDays / totalDays) * 100;
    }

    private long daysBetween(LocalDate from, LocalDate to) {
        return ChronoUnit.DAYS.between(from, to);
    }
}