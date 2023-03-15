package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenUsernameIsIvan() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Ivan"));
        Role result = roleStore.findById("1");
        assertThat(result.getName()).isEqualTo("Ivan");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Ivan"));
        Role result = roleStore.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsIvan() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Ivan"));
        roleStore.add(new Role("1", "Igor"));
        Role result = roleStore.findById("1");
        assertThat(result.getName()).isEqualTo("Ivan");
    }

    @Test
    void whenReplaceThenUsernameIsIgor() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Ivan"));
        roleStore.replace("1", new Role("1", "Igor"));
        Role result = roleStore.findById("1");
        assertThat(result.getName()).isEqualTo("Igor");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Ivan"));
        roleStore.replace("10", new Role("10", "Igor"));
        Role result = roleStore.findById("1");
        assertThat(result.getName()).isEqualTo("Ivan");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Ivan"));
        roleStore.delete("1");
        Role result = roleStore.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsIvan() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Ivan"));
        roleStore.delete("10");
        Role result = roleStore.findById("1");
        assertThat(result.getName()).isEqualTo("Ivan");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Ivan"));
        boolean result = roleStore.replace("1", new Role("1", "Igor"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Ivan"));
        boolean result = roleStore.replace("10", new Role("10", "Igor"));
        assertThat(result).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Ivan"));
        boolean result = roleStore.delete("1");
        assertThat(result).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Ivan"));
        boolean result = roleStore.delete("2");
        assertThat(result).isFalse();
    }
}