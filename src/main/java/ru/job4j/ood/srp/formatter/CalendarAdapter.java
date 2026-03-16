package ru.job4j.ood.srp.formatter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.util.Calendar;

public class CalendarAdapter extends XmlAdapter<String, Calendar> {
    private final DateTimeParser<Calendar> parser = new ReportDateTimeParser();

    @Override
    public Calendar unmarshal(String v) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public String marshal(Calendar v) throws Exception {
        return parser.parse(v);
    }
}