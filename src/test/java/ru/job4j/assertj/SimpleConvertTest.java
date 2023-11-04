package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {

    @Test
    void checkArray() {
        String[] array = new SimpleConvert().toArray(
                "first", "second", "third", "fourth", "fifth"
        );
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        List<String> list = new SimpleConvert().toList(
                "first", "second", "third", "fourth", "fifth"
        );
        assertThat(list).hasSize(5)
                .containsAnyOf("third", "zero", "tenth")
                .contains("fifth", Index.atIndex(4))
                .isNotEqualTo("sixth")
                .contains("second")
                .anyMatch(e -> e.equals("third"))
                .startsWith("first", "second")
                .endsWith("fourth", "fifth");
    }

    @Test
    void toSet() {
        Set<String> set = new SimpleConvert().toSet(
                "first", "fifty", "third", "fifth", "fifty", "first", null
        );
        assertThat(set).hasSize(5)
                .containsAnyOf("third", "zero", "tenth")
                .isNotEqualTo("sixth")
                .containsAnyOf(null, "second", "fourth");
        assertThat(set).first()
                .isEqualTo(null);
        assertThat(set).last()
                .isEqualTo("first");
    }

    @Test
    void toMap() {
        Map<String, Integer> map = new SimpleConvert().toMap(
                "one", "two", "three", "four", "one"
        );
        assertThat(map).hasSize(4)
                .containsKeys("one", "three")
                .containsValues(3, 0, 2)
                .doesNotContainKey("five")
                .doesNotContainValue(5)
                .containsEntry("two", 1);
    }
}