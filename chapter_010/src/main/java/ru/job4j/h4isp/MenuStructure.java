package ru.job4j.h4isp;

import org.apache.log4j.Logger;
import ru.job4j.utils.Utils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Vitaly Vasilyev, date: 05.06.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class MenuStructure<T extends MenuItem> implements Tree<T> {
    /**
     * Корневые элементы - пункты меню.
     */
    private final List<T> roots = new ArrayList<>();
    /**
     * Логгер.
     */
    private static final Logger LOG = Logger.getLogger(Utils.getNameOfTheClass());

    /**
     * @param element пункт меню.
     */
    @Override
    public void add(T element) {
        final T parent;
        if (element == null) {
            throw new RuntimeException("null");
        }
        if ((parent = retrieveParent(element)) != null) {
            parent.addChild(element);
        } else {
            this.roots.add(element);
        }
    }

    /**
     * @param element пункт меню.
     * @return предок по отношению к передаваемому элементу или null, если ничего не было найдено.
     *
     * При разборе массива чаров цикл заканчивается тогда, когда i равно предпоследнему элементу массива
     * (последней цифре в номере, например, 2 в номере 1.2. или 3 в номере 1.1.3.).
     * Когда наступает очередь последней цифры, parent уже найден.
     * Для 1.2. -> parent = 1.
     * Для 1.1.3. -> parent = 1.1.
     * Это нужно для того, чтобы не заходило в else if(), т.к. там result будет null.
     */
    private T retrieveParent(final T element) {
        T result = null;
        final char[] chars = element.getNumber().toCharArray();
        String value = "";
        for (int i = 0; i < chars.length; i += 2) {
            value = String.format("%s%c%s", value, chars[i], ".");
            if (i == 0) {
                result = loop(this.roots, value);
            } else if (i < chars.length - 2) {
                final List<T> list = (List<T>) result.getChildren();
                result = loop(list, value);
            }
        }
        return result;
    }

    /**
     * @param items список.
     * @param value значение для поиска.
     * @return найденное значение или null.
     */
    private T loop(final List<T> items, final String value) {
        T result = null;
        for (final T item : items) {
            if (item.getNumber().equals(value)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * @param number строка с номером для поиска нужного элемента.
     * @return найденный элемент или null.
     * Поиск элемента в ширину.
     */
    @Override
    public T findByNumber(final String number) {
        T result = null;
        final Queue<T> queue = new LinkedList<>();
        queue.addAll(this.roots);
        while (!queue.isEmpty()) {
            final T tmp = queue.poll();
            if (tmp.getNumber().equals(number)) {
                result = tmp;
            } else {
                for (final T item : (List<T>) tmp.getChildren()) {
                    queue.offer(item);
                }
            }
        }
        return result;
    }

    /**
     * @param item пункт меню.
     */
    public void show(final MenuItem item) {
        LOG.info(item);
        item.getChildren().forEach(this::show);
    }

    /**
     * Выводит на экран все элементы в виде дерева.
     * Но сначала все root-элементы должны быть отсортированы.
     */
    @Override
    public void showAll() {
        this.roots.sort(Utils.compareMenuItems());
        this.roots.forEach(this::show);
    }
}