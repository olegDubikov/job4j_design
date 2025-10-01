package ru.job4j.ood.srp.format;


import java.text.DecimalFormat;

public class SalaryFormat implements FormatterSalary {
    private static final DecimalFormat FORMATTER = new DecimalFormat("#,##0.00");

    public String format(double value) {
        return FORMATTER.format(value);
    }
}