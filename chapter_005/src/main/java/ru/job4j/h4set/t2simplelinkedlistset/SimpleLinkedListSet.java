package ru.job4j.h4set.t2simplelinkedlistset;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @param <E> is the name of type parameter.
 */
public class SimpleLinkedListSet<E> implements Iterator<E> {
    /**
     * The first element.
     */
    private Link<E> first;
    /**
     * The last element.
     */
    private Link<E> last;
    /**
     * The element for iterating through this set.
     */
    private Link<E> current;

    /**
     * Добавляет значение в конец контейнера-списка.
     * @param value is the passed parameter.
     * @return true, если удалось добавить значение в список, и false, если такое значение уже имеется в списке.
     */
    public boolean add(E value) {
        boolean result = false;
        if (value != null) {
            Link<E> newLink = new Link<>(value);
            if (isEmpty()) {
                first = newLink;
                current = first;
                last = newLink;
                result = true;
            } else {
                if (!contains(value)) {
                    last.next = newLink;
                    last = newLink;
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * @param obj is the parameter that whether is contained in this set or not.
     * @return true or false.
     */
    private boolean contains(E obj) {
        boolean result = false;
        Link<E> tmp = first;
        while (tmp != null) {
            if (obj.equals(tmp.data)) {
                result = true;
                break;
            }
            tmp = tmp.next;
        }
        return result;
    }

    /**
     * @param value is the position number which element should be taken.
     * @return the element according to the value.
     */
    public E get(int value) {
        Link<E> temp = first;
        int counter = 1;
        while ((temp != null) && (counter < value)) {
            counter++;
            temp = temp.next;
        }
        if ((temp == null) || (value < counter)) {
            throw new IndexOutOfBoundsException();
        }
        return temp.data;
    }

    /**
     * @return true or false.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        if (current != null) {
            result = true;
        }
        return result;
    }

    /**
     * @return the next element in this set.
     */
    @Override
    public E next() {
        E result;
        if (hasNext()) {
            result = current.data;
            current = current.next;
        } else {
            throw new NoSuchElementException();
        }
        return result;
    }

    /**
     * @return true or false.
     */
    public boolean isEmpty() {
        return first == null;
    }
//---------------------------------------------------------
    /**
     * @param <E> is the name of type parameter.
     */
   private class Link<E> {
        /**
         * Data.
         */
       private E data;
        /**
         * The reference to the next element.
         */
       private Link<E> next;
        /**
         * @param data for initialization.
         */
       private Link(E data) {
           this.data = data;
       }
   }
}