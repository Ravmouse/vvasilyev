package ru.job4j.h3list.cycle;
/**
 * @param <T> is the name of type parameter.
 */
public class ListWithCycle<T> {
    /**
     * The first element in the list.
     */
    Node<T> first;
    /**
     * @return true or false.
     */
    public boolean hasCycle() {
        boolean result = false;
        Node<T> current = first.next;
        Node<T> beforeCurrent = first;
        while ((current != first) && (current != null)) {
            current = current.next;
            if (beforeCurrent != current) {
                beforeCurrent = beforeCurrent.next;
            } else {
                break;
            }
        }
        if ((current == first) || (current == beforeCurrent)) {
            result = true;
        }
        return result;
    }
}