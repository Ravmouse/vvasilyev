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
     * Проверяет наличие зацикленности в контейнере, реализующем связанный список.
     * @return true, если узлы зациклены в середине или на начало списка, и false, если список не замкнут.
     */
    public boolean hasCycle() {
        boolean result = false;
        Node<T> current = first.next;
        Node<T> before = first;
        while ((current != before) && (current != first) && (current.next != null)) {
            current = current.next;
            if (current != before) {
                before = before.next;
            } else {
                break;
            }
            if ((current != first) && (current.next != null)) {
                current = current.next;
            }
        }
        if ((current == first) || (current == before)) {
            result = true;
        }
        return result;
    }
}