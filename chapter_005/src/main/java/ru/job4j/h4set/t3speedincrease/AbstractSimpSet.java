package ru.job4j.h4set.t3speedincrease;

/**
 * @param <E> - обобщенный параметр.
 */
public abstract class AbstractSimpSet<E> implements SimpSetMethods<E> {
    /**
     * Массив элементов обобщенного типа.
     */
    private E[] objects;

    /**
     * @param size - кол-во элементов.
     */
    public AbstractSimpSet(int size) {
        if (size > 0) {
            objects = (E[]) new Object[size];
        }
    }

    /**
     * @param e - элемент для добавления в массив.
     * @return true или false.
     */
    public abstract boolean add(E e);

    /**
     * @param e - элемент, который нужно удалить из массива.
     * @return true или false.
     */
    public abstract boolean remove(E e);

    /**
     * @param e - элемент, который нужно найти в массиве.
     * @return true или false.
     */
    public abstract boolean contains(E e);

    /**
     * Метод, отображающий все элементы в контейнере.
     */
    public abstract void showElements();

    /**
     * @return ссылку на массив в контейнере.
     */
    public E[] getObjects() {
        return objects;
    }

    /**
     * @param e - элемент, который нужно разместить в массиве.
     * @param pos - ячейка в массиве.
     */
    public void setObjectElem(E e, int pos) {
        this.objects[pos] = e;
    }

    /**
     * @param e - ссылка на другой массив элементов обобщенного типа.
     */
    public void setObjects(E[] e) {
        this.objects = e;
    }
}