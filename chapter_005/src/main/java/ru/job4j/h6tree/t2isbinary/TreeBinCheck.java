package ru.job4j.h6tree.t2isbinary;
import ru.job4j.h6tree.t1elementarytree.Node;
import ru.job4j.h6tree.t1elementarytree.SimpleTree;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * @param <E> - обобщенный тип класса.
 */
public class TreeBinCheck<E extends Comparable<E>> implements SimpleTree<E> {
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
            if (root == null) {
                root = new Node<E>(parent);
                root.add(new Node<E>(child));
                result = true;
            } else {
                Queue<Node<E>> q = new LinkedList<>();
                q.offer(root);
                while (!q.isEmpty()) {
                    final Node<E> node = q.poll();
                    if (node.getValue().compareTo(parent) == 0) {
                        if (!findBy(child)) {
                            node.add(new Node<>(child));
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
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            final Node<E> node = queue.poll();
            if (node.eqValue(value)) {
                result = true;
                break;
            }
            for (Node<E> tmp : node.childrenList()) {
                queue.offer(tmp);
            }
        }
        return result;
    }

    /**
     * @return итератор по всем элементам.
     */
    @Override
    public Iterator<E> iterator() {
        fillQueueForIterator();
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

    /**
     * @return true, если дерево - бинарное и false, если - нет.
     */
    public boolean isBinary() {
        boolean rsl = true;
        Queue<Node<E>> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            final Node<E> node = q.poll();
            if (node.childrenList().size() > 2) {
                rsl = false;
                break;
            }
            for (Node<E> tmp : node.childrenList()) {
                q.offer(tmp);
            }
        }
        return rsl;
    }
}