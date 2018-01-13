package ru.job4j.h6tree.t1elementarytree;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.LinkedList;

/**
 * @param <E> - обобщенный тип класса.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Корень "дерева".
     */
    private Node<E> root;

    /**
     * Очередь как для метода find(), так и для итератора.
     */
    private Queue<Node<E>> queue = new LinkedList<>();

    /**
     * @param parent - родительский элемент для добавления.
     * @param child - дочерний элемент родителя для добавления.
     * @return true, если удалось добавить и false, если - нет.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        if ((parent != null) && (child != null)) {
            if (root == null) {                                      //Если корень == null, то очередь пуста ->
                root = new Node<>(parent);                           //parent становится root,
                root.add(new Node<>(child));                         //а child - первым доч.эл-ом.
                rsl = true;
            } else {
                Node<E> parentNode = find(parent);                   //Добавление происходит только тогда, когда parent
                if ((parentNode != null) && (find(child) == null)) { //имеется как один из child,поэтому он д.б. найден
                    parentNode.add(new Node<>(child));
                    rsl = true;
                }
            }
        }
        return rsl;
    }

    /**
     * @param value - значение, которое может содержаться в одном из Node'ов и которое надо найти.
     * @return Node со значением value, если такое значение имеется, или null.
     */
    public Node<E> find(E value) {
        Node<E> rsl = null;
        queue.offer(root);                            //В очередь вводится корень дерева
        while (!queue.isEmpty()) {                    //Пока очередь не станет пустой
            final Node<E> node = queue.poll();        //Вытащить из очереди следующий элемент
            if (node.eqValue(value)) {                //Если элемент равен значению, то...
                rsl = node;                           //То вернуть Node со значением.
                break;
            }                                         //Если элемент не равен значению, то...
            for (Node<E> tmp : node.childrenList()) { //для каждого Node в листе Node
                queue.offer(tmp);                     //Ввести в очередь каждый Node
            }
        }
        return rsl;
    }

    /**
     * @return итератор по всем элементам.
     */
    @Override
    public Iterator<E> iterator() {
        visitAll(root);
        return new Iterator<E>() {

            public boolean hasNext() {
                return !queue.isEmpty();
            }

            public E next() {
                if (this.hasNext()) {
                    return queue.poll().getValue();
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    /**
     * Перебор всех элементов дерева при помощи рекурсии.
     * @param val - Node, с которого начинается перебор дочерних элементов.
     */
    private void visitAll(Node<E> val) {
        int count = 0;
        if (val.childrenList() != null) {                //Условие, при котором рекурсия прекращается.
            while (count < val.childrenList().size()) {  //Пока лок.переменная не равна кол-ву нодов в Листе...
                visitAll(val.childrenList().get(count)); //Снова заход в метод.
                count++;
            }
        }
        queue.offer(val);
    }

    /**
     * @return ссылку на корень дерева.
     * Метод нужен для класса TreeBinCheck, который расширяет данный класс.
     */
    public Node<E> getRoot() {
        return root;
    }
}