package ru.job4j.h4isp;

/**
 * @param <T>
 */
public interface Tree<T extends MenuItem> {
    /**
     * @param element элемент для добавления.
     */
    void add(T element);
    /**
     * @param number строка с номером для поиска нужного элемента.
     * @return найденный элемент или null.
     */
    T findByNumber(String number);
    /**
     * Отображает все элементы.
     */
    void showAll();
}