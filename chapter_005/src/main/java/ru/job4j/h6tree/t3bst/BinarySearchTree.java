package ru.job4j.h6tree.t3bst;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * @param <E> - обобщенный тип класса.
 */
public class BinarySearchTree<E extends Comparable<E>> implements SimpleBinarySearchTree<E> {
    /**
     * Корень дерева.
     */
    private Node<E> root;
    /**
     * Ссылка на нод при добавлении элемента.
     */
    private Node<E> current;

    /**
     * Очередь для размещения всех элементов в целях итерации.
     */
    private Queue<E> list = new LinkedList<>();

    /**
     * @param e - значение дочернего элемента для добавления в дерево.
     * @return true, если удалось добавить элемент и false, если - нет.
     */
    @Override
    public boolean add(E e) {
        boolean rsl = false;
        if (e != null) {
            if (root == null) {                             //Если root == null, значит дерево не содержит элементов.
                root = new Node<>(e);
                current = root;                             //Теперь current не null, а ссылается на root.
                rsl = true;
            } else {
                if (current.getValue().compareTo(e) >= 0) { //Если current >= e, то нод размещается слева.
                    if (current.getLeft() != null) {        //Если слева уже имеется элемент,
                        current = current.getLeft();        //то current становится этим элементом.
                        rsl = add(e);                       //Рекурсивно вызвать этот же метод.
                    } else {
                        current.setLeft(new Node<>(e));     //Если слева нет элемента, то создается дочерний эл-т.
                        rsl = true;
                        current = root;                     //Вернуть current значения root, чтобы при след.добавлении
                    }                                       //сравнение эл-тов происходило от корня.

                } else {                                    //Если current < e, то нод размещается справа.
                    if (current.getRight() != null) {
                        current = current.getRight();
                        rsl = add(e);
                    } else {
                        current.setRight(new Node<>(e));
                        rsl = true;
                        current = root;
                    }
                }
            }
        }
        return rsl;
    }

    /**
     * @param e - значение, которое нужно найти среди нодов дерева.
     * @return нод с искомым значением.
     */
    @Override
    public Node<E> find(E e) {
        Node<E> temp = root;
        while ((temp != null) && (temp.getValue().compareTo(e) != 0)) { //Пока temp != null и != искомому значению,
            if (temp.getValue().compareTo(e) > 0) {                     //если temp > иск.значения, то идти налево,
                temp = temp.getLeft();
            } else {                                                    //если temp < иск.значения, то идти направо.
                temp = temp.getRight();
            }
        }
        return temp;
    }

    /**
     * При помощи рекурсии осуществляется обход всех элементов дерева.
     * @param node - элемент дерева.
     */
    private void visitAll(Node<E> node) {
        if (node != null) {               //Условие выхода из рекурсии.
            visitAll(node.getLeft());     //Посетить дочерний элемент слева.
            list.add(node.getValue());    //Получить значение самого элемента и добавить его в очередь.
            visitAll(node.getRight());    //Посетить дочерний элемент справа.
        }
    }

    /**
     * @return итератор по всем элементам дерева.
     */
    @Override
    public Iterator<E> iterator() {
        list.clear();    //Очищение очереди всякий раз, когда создается итератор.
        visitAll(root);
        return new Iterator<E>() {
            public boolean hasNext() {
                return !list.isEmpty();
            }

            public E next() {
                if (hasNext()) {
                    return list.poll();
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}