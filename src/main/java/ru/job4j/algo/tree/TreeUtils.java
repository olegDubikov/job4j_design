package ru.job4j.algo.tree;

import ru.job4j.collection.SimpleQueue;
import ru.job4j.collection.SimpleStack;

import java.util.*;

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

    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("root не может быть null");
        }
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);
        while (true) {
            try {
                Node<T> current = stack.pop();
                if (Objects.equals(current.getValue(), key)) {
                    return Optional.of(current);
                }
                List<Node<T>> children = current.getChildren();
                for (int i = children.size() - 1; i >= 0; i--) {
                    stack.push(children.get(i));
                }
            } catch (NoSuchElementException e) {
                break;
            }
        }
        return Optional.empty();
    }

    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("root не может быть null");
        }
        if (Objects.equals(root.getValue(), key)) {
            return Optional.of(root);
        }
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);
        while (true) {
            try {
                Node<T> current = stack.pop();
                List<Node<T>> children = current.getChildren();
                for (var iterator = children.iterator(); iterator.hasNext(); ) {
                    Node<T> child = iterator.next();
                    if (Objects.equals(child.getValue(), key)) {
                        iterator.remove();
                        return Optional.of(child);
                    }
                    stack.push(child);
                }
            } catch (NoSuchElementException e) {
                break;
            }
        }
        return Optional.empty();
    }

    public boolean add(Node<T> root, T parent, T child) {
        if (root == null) {
            throw new IllegalArgumentException("root не может быть null");
        }
        Optional<Node<T>> parentNode = findByKey(root, parent);
        boolean childAlreadyExists = findByKey(root, child).isPresent();
        if (parentNode.isPresent() && !childAlreadyExists) {
            parentNode.get().getChildren().add(new Node<>(child));
            return true;
        }
        return false;
    }
}