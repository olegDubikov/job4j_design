package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        if (childName == null) {
            throw new IllegalArgumentException();
        }
        if (Objects.equals(parentName, ROOT)) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
            return true;
        }
        Optional<ItemInfo> parent = findItem(parentName);
        if (parent.isEmpty()) return false;
        SimpleMenuItem child = new SimpleMenuItem(childName, actionDelegate);
        parent.get().menuItem.getChildren().add(child);
        return true;
    }

    @Override
    public Optional<Menu.MenuItemInfo> select(String itemName) {
        Optional<MenuItemInfo> result;
        if (itemName == null) {
            throw new IllegalArgumentException("itemName cannot be null");
        }
        Optional<ItemInfo> item = findItem(itemName);
        if (item.isEmpty()) {
            result = Optional.empty();
        } else {
            ItemInfo info = item.get();
            result = Optional.of(
                    new MenuItemInfo(
                            info.menuItem,
                            info.number
                    )
            );
        }
        return result;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new Iterator<>() {
            final DFSIterator iterator = new DFSIterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo itemInfo = iterator.next();
                return new MenuItemInfo(
                        itemInfo.menuItem,
                        itemInfo.number
                );
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        DFSIterator iterator = new DFSIterator();
        while (iterator.hasNext()) {
            ItemInfo itemInfo = iterator.next();
            if (name.equals(itemInfo.menuItem.getName())) {
                return Optional.of(itemInfo);
            }
        }
        return Optional.empty();
    }

    private static class SimpleMenuItem implements MenuItem {
        private final String name;
        private final List<MenuItem> children = new ArrayList<>();
        private final ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {
        private final Deque<MenuItem> stack = new LinkedList<>();
        private final Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious(); ) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private static class ItemInfo {
        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
