package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ArgsNameTest {
    @Test
    void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[] {"-request=?msg=Exit="});
        assertThat(jvm.get("request")).isEqualTo("?msg=Exit=");
    }

    @Test
    void whenKeyNotExist() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512"});
        assertThatThrownBy(() -> jvm.get("Xms")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This key 'Xms' does not find");
    }

    @Test
    void whenKeyGetNotExist2() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512"});
        assertThatThrownBy(() -> jvm.get("Xss")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("This key 'Xss' does not find");
    }

    @Test
    void whenWrongSomeArgument() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Arguments not passed to program");
    }

    @Test
    void whenStringDoesNotContainKeyThenExceptionThrown() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-=?msg=Exit="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("This line '-=?msg=Exit=' does not contain a key");
    }

    @Test
    void whenStringDoesNotContainKeyThenExceptionThrown2() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-=?msg=Hello="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This line '-=?msg=Hello=' does not contain a key");
    }

    @Test
    void whenStringDoesNotContainKeyThenExceptionThrown3() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-=512", "-msg=Hello"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This line '-=512' does not contain a key");
    }

    @Test
    void whenStringDoesNotContainValueThenExceptionThrown() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-request="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This line '-request=' does not contain a value");
    }

    @Test
    void whenStringDoesNotContainValueThenExceptionThrown2() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-encoding="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This line '-encoding=' does not contain a value");
    }

    @Test
    void whenStringDoesNotContainValueThenExceptionThrown3() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=", "-encoding=java"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This line '-Xmx=' does not contain a value");
    }

    @Test
    void whenThereNoEqualSignThenExceptionThrown1() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-request?msgHello"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(
                        "Error: This line '-request?msgHello' does not contain '=' "
                );
    }

    @Test
    void whenThereNoEqualSignThenExceptionThrown2() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "welcome to java"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(
                        "Error: This line 'welcome to java' does not contain '=' "
                );
    }

    @Test
    void whenNoHyphenPrefixThenExceptionThrown1() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "request=?msg=Exit="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(
                        "Error: This line 'request=?msg=Exit=' does not contain '-' "
                );
    }

    @Test
    void whenNoHyphenPrefixThenExceptionThrown2() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "mr.X=postman"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(
                        "Error: This line 'mr.X=postman' does not contain '-' "
                );
    }
}