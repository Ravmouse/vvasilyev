package ru.job4j.h6tree.t3bst;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.List;

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
     * Список для размещения всех элементов в целях итерации.
     */
    private List<Node<E>> list = new LinkedList<>();
    /**
     * Здесь хранится кол-во добавляемых в дерево элементов.
     */
    private int size;
    /**
     * Переменная для итератора, чтобы получать значения из списка.
     */
    private int index;

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
                size++;                                     //Один элемент добавлен.
                current = root;                             //Теперь current не null, а ссылается на root.
                rsl = true;
            } else {
                if (current.getValue().compareTo(e) >= 0) { //Если current >= e, то нод размещается слева.
                    if (current.getLeft() != null) {        //Если слева уже имеется элемент,
                        current = current.getLeft();        //то current становится этим элементом.
                        rsl = add(e);                       //Рекурсивно вызвать этот же метод.
                    } else {
                        current.setLeft(new Node<>(e));     //Если слева нет элемента, то создается дочерний эл-т.
                        size++;
                        rsl = true;
                        current = root;                     //Вернуть current значения root, чтобы при след.добавлении
                    }                                       //сравнение эл-тов происходило от корня.

                } else {                                    //Если current < e, то нод размещается справа.
                    if (current.getRight() != null) {
                        current = current.getRight();
                        rsl = add(e);
                    } else {
                        current.setRight(new Node<>(e));
                        size++;
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

//    /**
//     * При помощи рекурсии осуществляется обход всех элементов дерева.
//     * @param node - элемент дерева.
//     */
//    private void visitAll(Node<E> node) {
//        if (node != null) {               //Условие выхода из рекурсии.
//            visitAll(node.getLeft());     //Посетить дочерний элемент слева.
//            list.add(node.getValue());    //Получить значение самого элемента и добавить его в очередь.
//            visitAll(node.getRight());    //Посетить дочерний элемент справа.
//        }
//    }

    /**
     * @return итератор по всем элементам дерева.
     */
    @Override
    public Iterator<E> iterator() {
        list.clear(); //Очищение списка всякий раз, когда создается итератор.
        index = -1;   //В next() значение инкрементируется. При след.вызове итератора index д.б. в начале списка.
        listFill();   //Заполнение списка.
        return new Iterator<E>() {

            public boolean hasNext() {
                return index < list.size() - 1;
            }

            public E next() {
                if (hasNext()) {
                    index++;
                    return list.get(index).getValue();
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    /**
     * Заполнение списка всеми элементами дерева.
     */
    private void listFill() {
        int i = 0;
        list.add(root);
        while (i < size) {
            Node<E> tmp = list.get(i);
            if (tmp.getLeft() != null) {
                list.add(tmp.getLeft());
            }
            if (tmp.getRight() != null) {
                list.add(tmp.getRight());
            }
            i++;
        }
    }
}