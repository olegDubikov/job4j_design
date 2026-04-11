package ru.job4j.algo.sort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class QuickListTest {
    @Test
    void whenSortThenOk() {
        User user1 = new User(1, "11");
        User user2 = new User(1, "22");
        User user3 = new User(3, "33");
        User user4 = new User(-4, "44");
        User user5 = new User(4, "55");
        User user6 = new User(6, "66");
        Comparator<User> comparator = Comparator.comparingInt(User::getId);
        List<User> listUser = new ArrayList<>();
        listUser.add(user3);
        listUser.add(user5);
        listUser.add(user1);
        listUser.add(user4);
        listUser.add(user6);
        listUser.add(user2);
        QuickList.quickSort(listUser, comparator);
        List<Integer> result = listUser.stream().map(User::getId).toList();
        assertThat(result).containsExactly(-4, 1, 1, 3, 4, 6);
    }

    @Test
    void whenReverseOrderThenOk() {
        Comparator<Integer> comparator = (a, b) -> b - a;
        List<Integer> list = new ArrayList<>();
        list.add(-2);
        list.add(-8);
        list.add(1);
        list.add(0);
        list.add(10);
        list.add(1);
        list.add(8);
        QuickList.quickSort(list, comparator);
        System.out.println(list);
        assertThat(list).containsExactly(10, 8, 1, 1, 0, -2, -8);
    }

    @Test
    void whenSortEmptyListThenOk() {
        List<Integer> list = new ArrayList<>();
        QuickList.quickSort(list, Integer::compare);
        assertThat(list).isEmpty();
    }

    @Test
    void whenSortOneElementThenOk() {
        List<Integer> list = List.of(1);
        QuickList.quickSort(new ArrayList<>(list), Integer::compare);
        assertThat(list).containsExactly(1);
    }

    @Test
    void whenSortAlreadySortedThenOk() {
        List<Integer> list = List.of(1, 2, 3, 4);
        QuickList.quickSort(new ArrayList<>(list), Integer::compare);
        assertThat(list).containsExactly(1, 2, 3, 4);
    }

    @Test
    void whenSortWithDuplicatesThenOk() {
        List<Integer> list = new ArrayList<>(List.of(3, 1, 2, 2, 3));
        QuickList.quickSort(list, Integer::compare);
        assertThat(list).containsExactly(1, 2, 2, 3, 3);
    }

    private static class User {
        private final Integer id;

        public User(int id, String name) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }
}
