package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {

    @Test
    void checkEmpty() {
        assertThatThrownBy(new NameLoad()::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkLength() {
        assertThatThrownBy(new NameLoad()::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("empty");
    }

    @Test
    void checkSymbol() {
        assertThatThrownBy(() -> new NameLoad().parse("key:value"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(
                        "this name: key:value does not contain the symbol \"=\""
                );
    }

    @Test
    void checkKey() {
        assertThatThrownBy(() -> new NameLoad().parse("=value"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: =value does not contain a key");
    }

    @Test
    void checkValue() {
        assertThatThrownBy(() -> new NameLoad().parse("key="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: key= does not contain a value");
    }
}