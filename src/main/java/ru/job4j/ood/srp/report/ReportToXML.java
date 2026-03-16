package ru.job4j.ood.srp.report;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.Employees;
import ru.job4j.ood.srp.store.Store;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class ReportToXML implements Report {
    private final Store store;
    private final JAXBContext context;

    public ReportToXML(Store store) {
        this.store = store;
        try {
            this.context = JAXBContext.newInstance(Employees.class);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String xml;
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter writer = new StringWriter()) {
                List<Employee> filteredEmployees = store.findBy(filter);
                marshaller.marshal(new Employees(filteredEmployees), writer);
                xml = writer.getBuffer().toString();
            }
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
        return xml;
    }
}