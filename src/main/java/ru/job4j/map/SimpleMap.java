package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        expand();
        int index = getBasket(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            result = true;
            count++;
            modCount++;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> table.length);
    }

    private int indexFor(int hash) {
        return hash & table.length - 1;
    }

    private int getBasket(K key) {
        return key == null ? 0 : indexFor(hash(key.hashCode()));
    }

    private void expand() {
        if (count >= capacity * LOAD_FACTOR) {
            MapEntry<K, V>[] temp = table;
            capacity *= 2;
            table = new MapEntry[capacity];
            for (MapEntry<K, V> el : temp) {
                if (el != null) {
                    int index = getBasket(el.key);
                    table[index] = new MapEntry<>(el.key, el.value);
                }
            }
        }
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = getBasket(key);
        if (key != null && table[index] != null && table[index].key != null
                && key.hashCode() == table[index].key.hashCode()
                && Objects.equals(key, table[index].key)
                || table[index] != null && table[index].key == key) {
            result = table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = getBasket(key);
        if (table[index] != null && table[index].key == key) {
            table[index] = null;
            count--;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {

            private final int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}