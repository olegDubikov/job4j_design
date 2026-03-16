package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportToJSON implements Report {
    private final Store store;
    private final Gson gson;

    public ReportToJSON(Store store) {
        this.store = store;
        this.gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(Calendar.class, (JsonSerializer<Calendar>) (src, typeOfSrc, context) ->
                        new JsonPrimitive(String.format("%1$td:%1$tm:%1$tY %1$tH:%1$tM", src)))
                .setPrettyPrinting()
                .create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        return gson.toJson(employees);
    }
}