package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("firstName")).isEqualTo("Petr");
        assertThat(config.value("lastName")).isEqualTo("Arsentev");
        assertThat(config.value("first")).isEqualTo("Petr=");
        assertThat(config.value("last")).isEqualTo("Arsentev=1");
    }

    @Test
    void whenComment() {
        String path = "./data/with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("#key"))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Don't impl this method yet!");
    }

    @Test
    void whenEmptyLine() {
        String path = "./data/with_empty_line.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value(" "))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Don't impl this method yet!");
    }

    @Test
    void whenOnlyEquals() {
        String path = "./data/only_equals.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line contain only \"=\"");
    }

    @Test
    void whenNoKey() {
        String path = "./data/without_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("No key");
    }

    @Test
    void whenNoValue() {
        String path = "./data/without_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("No value");
    }

    @Test
    void whenSimpleLine() {
        String path = "./data/simple_line.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("This is just line");
    }
}