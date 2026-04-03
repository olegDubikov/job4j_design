package ru.job4j.ood.isp.menu;

import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.MenuPrinter;

public class Printer implements MenuPrinter {
    private final static String APPENDER = "----";

    @Override
    public void print(Menu menu) {
        StringBuilder str = new StringBuilder();
        for (Menu.MenuItemInfo item : menu) {
            int num = item.getNumber().split("\\.").length - 1;
            str.append(APPENDER.repeat(num));
            str.append(item.getNumber()).append(" ").append(item.getName())
                    .append(System.lineSeparator());
        }
        System.out.println(str);
    }
}