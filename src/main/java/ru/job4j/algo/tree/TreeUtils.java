package ru.job4j.algo.tree;

import ru.job4j.collection.SimpleQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TreeUtils<T> {

    public int countNode(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("root не может быть null");
        }
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        int count = 0;

        while (true) {
            try {
                Node<T> current = queue.poll();
                count++;
                for (Node<T> child : current.getChildren()) {
                    queue.push(child);
                }
            } catch (NoSuchElementException e) {
                break;
            }
        }
        return count;
    }

    public Iterable<T> findAll(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("root не может быть null");
        }
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        List<T> result = new ArrayList<>();
        queue.push(root);

        while (true) {
            try {
                Node<T> current = queue.poll();
                result.add(current.getValue());
                for (Node<T> child : current.getChildren()) {
                    queue.push(child);
                }
            } catch (NoSuchElementException e) {
                break;
            }
        }
        return result;
    }
}
