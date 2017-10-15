package ru.job4j.h3list.simplestackandqueue;

/**
 * @param <E> is the name of type parameter.
 */
public abstract class SimpleAbstract<E> {
    /**
     * The ref to the LinkList class.
     */
    private LinkList<E> linkList;

    /**
     * The constructor.
     */
    public SimpleAbstract() {
        linkList = new LinkList<>();
    }

    /**
     * @return the type parameter value and deletes it.
     */
    public E poll() {
        return linkList.removeFirst();
    }

    /**
     * @param pos is the number assigned to the value that should be found.
     * @return the found value.
     */
    public E get(int pos) {
        return linkList.get(pos);
    }

    /**
     * @return the ref to the LinkList class.
     */
    public LinkList<E> getLinkList() {
        return linkList;
    }

    /**
     * @param value to be pushed.
     */
    public abstract void push(E value);
}
