package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportForProgrammers implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> parser;

    public ReportForProgrammers(Store store, DateTimeParser<Calendar> parser) {
        this.store = store;
        this.parser = parser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(String.format("%-7s; %s; %s; %-7s;%n",
                    employee.getName(),
                    parser.parse(employee.getHired()),
                    parser.parse(employee.getFired()),
                    employee.getSalary()));
        }
        return text.toString();
    }
}