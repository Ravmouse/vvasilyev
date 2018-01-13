package ru.job4j.h6tree.t2isbinary;
import ru.job4j.h6tree.t1elementarytree.Node;
import ru.job4j.h6tree.t1elementarytree.SimpleTree;
import ru.job4j.h6tree.t1elementarytree.Tree;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @param <E> - обобщенный тип класса.
 */
public class TreeBinCheck<E extends Comparable<E>> extends Tree<E> implements SimpleTree<E> {
    /**
     * @return true, если дерево - бинарное и false, если - нет.
     */
    public boolean isBinary() {
        boolean rsl = true;
        Queue<Node<E>> q = new LinkedList<>();
        q.offer(getRoot());
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