package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import static org.assertj.core.api.Assertions.assertThat;

class ReportForHRsTest {
    @Test
    void whenGenerateReport() {
        MemoryStore store = new MemoryStore();
        Employee worker1 = new Employee("Ivan", 200);
        Employee worker2 = new Employee("Petr", 300);
        Employee worker3 = new Employee("Oleg", 100);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportForHRs(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append("Petr").append(" ")
                .append(300.0)
                .append(System.lineSeparator())
                .append("Ivan").append(" ")
                .append(200.0)
                .append(System.lineSeparator())
                .append("Oleg").append(" ")
                .append(100.0)
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}