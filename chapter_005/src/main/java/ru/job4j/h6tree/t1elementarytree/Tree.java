package ru.job4j.h6tree.t1elementarytree;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.LinkedList;

/**
 * @param <E> - обобщенный тип.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Корень "дерева".
     */
    private Node<E> root;

    /**
     * Очередь для итератора.
     */
    private Queue<Node<E>> queueForIter;

    /**
     * @param parent - родительский элемент для добавления.
     * @param child - дочерний элемент родителя для добавления.
     * @return true, если удалось добавить и false, если - нет.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if ((parent != null) && (child != null)) {
            if (root == null) {                                  //Если корень == null, то очередь пуста ->
                root = new Node<E>(parent);                      //parent становится root, а child - первым доч.эл-ом.
                root.add(new Node<E>(child));
                result = true;
            } else {
                Queue<Node<E>> q = new LinkedList<>();
                q.offer(root);
                while (!q.isEmpty()) {
                    final Node<E> node = q.poll();
                    if (node.getValue().compareTo(parent) == 0) {
                        if (!findBy(child)) {
                            root.add(new Node<>(child));
                            result = true;
                            break;
                        }
                        break;
                    }
                    for (Node<E> tmp : node.childrenList()) {
                        q.offer(tmp);
                    }
                }
            }
        }
        return result;
    }

    /**
     * @param value - значение, которое может содержаться в одном из Node'ов и которое надо найти.
     * @return true, если значение найдено и false, если - нет.
     */
    public boolean findBy(E value) {
        boolean result = false;
        Queue<Node<E>> queue = new LinkedList<>();    //
        queue.offer(root);                            //В очередь вводится корень дерева
        while (!queue.isEmpty()) {                    //Пока очередь не станет пустой
            final Node<E> node = queue.poll();        //Вытащить из очереди следующий элемент
            if (node.eqValue(value)) {                //Если элемент равен значению, то...
                result = true;
                break;
            }                                         //Если елемент не равен значению, то...
            for (Node<E> tmp : node.childrenList()) { //для каждого Node в листе Node
                queue.offer(tmp);                     //Ввести в очередь каждый Node
            }
        }
        return result;
    }

    /**
     * @return итератор по всем элементам.
     */
    @Override
    public Iterator<E> iterator() {
        fillQueueForIterator();         //Заполнение очереди для итератора.
        return new Iterator<E>() {

            public boolean hasNext() {
                boolean rsl = false;
                if (!queueForIter.isEmpty()) {
                    rsl = true;
                }
                return rsl;
            }

            public E next() {
                if (this.hasNext()) {
                    return queueForIter.poll().getValue();
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    /**
     * При создании объекта типа Iterator вызывается этот метод для заполнения очереди queueForIter
     * всеми элементами, входящими в этот класс Tree.
     */
    private void fillQueueForIterator() {
        queueForIter = new LinkedList<>();
        Queue<Node<E>> tmpQueue = new LinkedList<>();
        tmpQueue.offer(root);
        while (!tmpQueue.isEmpty()) {
            final Node<E> node = tmpQueue.poll();
            queueForIter.offer(node);
            for (Node<E> tmp: node.childrenList()) {
                tmpQueue.offer(tmp);
            }
        }
    }
}