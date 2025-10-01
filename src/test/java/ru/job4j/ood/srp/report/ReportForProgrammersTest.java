package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ReportForProgrammersTest {
    @Test
    void whenGenerateReport() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 200);
        Employee worker3 = new Employee("Sergey", now, now, 300);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportForProgrammers(store, parser);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append("   ;").append(" ")
                .append(parser.parse(worker1.getHired())).append(";").append(" ")
                .append(parser.parse(worker1.getFired())).append(";").append(" ")
                .append(worker1.getSalary()).append("  ;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append("   ;").append(" ")
                .append(parser.parse(worker1.getHired())).append(";").append(" ")
                .append(parser.parse(worker1.getFired())).append(";").append(" ")
                .append(worker2.getSalary()).append("  ;")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(" ;").append(" ")
                .append(parser.parse(worker1.getHired())).append(";").append(" ")
                .append(parser.parse(worker1.getFired())).append(";").append(" ")
                .append(worker3.getSalary()).append("  ;")
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}