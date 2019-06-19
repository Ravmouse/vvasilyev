package ru.job4j.h4isp;

import ru.job4j.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 05.06.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class MenuItem {
    /**
     * Имя пункта меню.
     */
    private static final String NAME = "Задача ";
    /**
     * Список дочерних элементов пункта меню.
     */
    private final List<MenuItem> children = new ArrayList<>();
    /**
     * Номер пункта меню.
     */
    private final String number;

    /**
     * @param number номер для присвоения.
     */
    public MenuItem(final String number) {
        this.number = number;
    }

    /**
     * @return список дочерних элементов.
     * У каждого элемента список дочерних элементов сортируется прежде, чем вернуть из метода.
     */
    public List<MenuItem> getChildren() {
        children.sort(Utils.compareMenuItems());
        return this.children;
    }

    /**
     * @return номер пункта меню.
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * @param child дочерний элемент для добавления в список.
     */
    public void addChild(final MenuItem child) {
        this.children.add(child);
    }

    /**
     * @return количество знаков "-" в соответствии с номером пункта меню.
     */
    private String countPoints() {
        final StringBuilder sb = new StringBuilder("");
        for (char c : this.number.toCharArray()) {
            if (c == '.') {
                sb.append("-");
            }
        }
        return sb.toString();
    }

    /**
     * @return строк.представление.
     */
    @Override
    public String toString() {
        return String.format("%s%s%s", countPoints(), NAME, this.number);
    }
}