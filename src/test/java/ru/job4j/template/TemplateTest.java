package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class TemplateTest {
    Template temp = new Template();
    String template = "I am a ${name}, Who are ${subject}?";
    Map<String, String> map = new HashMap<>();

    @Test
    public void whenMapHasKeys() {
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        String result = temp.produce(template, map);
        assertEquals("I am a Petr Arsentev, Who are you?", result);
    }

    @Test
    public void whenMapNoHasKey() {
        map.put("name", "Petr Arsentev");
        assertThrows(IllegalArgumentException.class, () -> temp.produce(template, map));
    }

    @Test
    public void whenMapHasExtraKey() {
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        map.put("extra", "extra");
        assertThrows(IllegalArgumentException.class, () -> temp.produce(template, map));
    }
}