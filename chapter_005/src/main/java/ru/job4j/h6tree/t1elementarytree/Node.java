package ru.job4j.h6tree.t1elementarytree;
import java.util.ArrayList;
import java.util.List;

/**
 * @param <T> - обобщенный тип.
 */
public class Node<T> {
    /**
     * Список дочерних элементов.
     */
    private List<Node<T>> children;
    /**
     * Значение.
     */
    private T value;

    /**
     * @param value - значение для инициализации в конструкторе.
     */
    public Node(T value) {
        children = new ArrayList<>();
        this.value = value;
    }

    /**
     * @param child - элемент для добавления в List Node'ов.
     */
    public void add(Node<T> child) {
        this.children.add(child);
    }

    /**
     * @return ссылку на List Node'ов.
     */
    public List<Node<T>> childrenList() {
        return this.children;
    }

    /**
     * @param that - элемент для сравнения с текущим значением Node.
     * @return true, если значения идентичны и false, если - нет.
     */
    public boolean eqValue(T that) {
        return this.value.equals(that);
    }

    /**
     * @return значение текущего Node.
     */
    public T getValue() {
        return value;
    }
}