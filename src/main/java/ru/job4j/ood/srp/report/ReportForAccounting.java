package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.format.FormatterSalary;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportForAccounting implements Report {


    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final FormatterSalary format;
    private final CurrencyConverter converter;


    public ReportForAccounting(Store store, DateTimeParser<Calendar> dateTimeParser, FormatterSalary format, CurrencyConverter converter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.format = format;
        this.converter = converter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            double convertedSalary = converter.convert(
                    Currency.RUB,
                    employee.getSalary(),
                    Currency.USD);
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(format.format(convertedSalary))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}