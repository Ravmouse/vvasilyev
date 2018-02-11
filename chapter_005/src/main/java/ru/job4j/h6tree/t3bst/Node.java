package ru.job4j.h6tree.t3bst;

/**
 * @param <T> - обобщенный тип класса.
 */
public class Node<T> {
    /**
     * Ссылка на дочерний элемент бинарного дерева слева.
     */
    private Node<T> left;
    /**
     * Ссылка на дочерний элемент бинарного дерева справа.
     */
    private Node<T> right;
    /**
     * Значение нода.
     */
    private T value;

    /**
     * @param value - значение для инициализации в конструкторе.
     */
    public Node(T value) {
        this.value = value;
    }

    /**
     * @param left - нод для установки дочерним элементом слева.
     */
    public void setLeft(Node<T> left) {
        this.left = left;
    }

    /**
     * @return дочерний элемент слева.
     */
    public Node<T> getLeft() {
        return this.left;
    }

    /**
     * @param right - нод для установки дочерним элементом справа.
     */
    public void setRight(Node<T> right) {
        this.right = right;
    }

    /**
     * @return дочерний элемент справа.
     */
    public Node<T> getRight() {
        return this.right;
    }

    /**
     * @return значение нода.
     */
    public T getValue() {
        return this.value;
    }
}