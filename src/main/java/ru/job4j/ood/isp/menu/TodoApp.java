package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TodoApp {
    private static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");
    private final Menu menu = new SimpleMenu();
    private final MenuPrinter printer = new Printer();

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Добавить в корень");
            System.out.println("2. Добавить к родителю");
            System.out.println("3. Вызвать действие");
            System.out.println("4. Вывести меню");
            System.out.println("0. Выход");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addRoot(scanner);
                case "2" -> addChild(scanner);
                case "3" -> invokeAction(scanner);
                case "4" -> printer.print(menu);
                case "0" -> {
                    return;
                }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    private void addRoot(Scanner scanner) {
        System.out.print("Введите имя пункта: ");
        String name = scanner.nextLine();
        menu.add(Menu.ROOT, name, DEFAULT_ACTION);
    }

    private void addChild(Scanner scanner) {
        System.out.print("Введите имя родителя: ");
        String parent = scanner.nextLine();
        System.out.print("Введите имя нового пункта: ");
        String name = scanner.nextLine();
        menu.add(parent, name, DEFAULT_ACTION);
    }

    private void invokeAction(Scanner scanner) {
        System.out.print("Введите имя пункта для вызова действия: ");
        String name = scanner.nextLine();
        menu.select(name).ifPresent(i -> i.getActionDelegate().delegate());
    }

    public static void main(String[] args) {
        new TodoApp().run();
    }
}
