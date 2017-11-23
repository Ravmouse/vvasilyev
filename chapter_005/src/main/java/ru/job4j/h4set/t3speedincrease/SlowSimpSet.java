package ru.job4j.h4set.t3speedincrease;
import java.util.Arrays;

/**
 * @param <E> - обобщенный параметр.
 */
public class SlowSimpSet<E> extends AbstractSimpSet<E> {
    /**
     * Кол-во добавленных элементов в основном массиве.
     */
    private int count;
    /**
     * Позиция найденного элемента.
     */
    private int valPosition;

    /**
     * @param size - кол-во элементов в массиве при создании объекта.
     */
    public SlowSimpSet(int size) {
        super(size);
        valPosition = -1;
    }

    /**
     * @param e - элемент для добавления в массив.
     * @return true, если элемент добавлен и false, если элемент не добавлен.
     */
    @Override
    public boolean add(E e) {
        boolean result = false;
        if ((e != null) && (!contains(e))) {
            if (count == getObjects().length) {
                setObjects(Arrays.copyOf(getObjects(), (getObjects().length * 3) / 2 + 1));
            }
            setObjectElem(e, count);
            count++;
            result = true;
        }
        return result;
    }

    /**
     * @param e - элемент, который нужно найти в массиве.
     * @return true, если такой элемент имеется и false, если такого элемента нет.
     */
    @Override
    public boolean contains(E e) {
        int i;
        for (i = 0; i < count; i++) {
            if (getObjects()[i].equals(e)) {
                valPosition = i;
                break;
            }
        }
        return i != count;
    }

    /**
     * @param e - элемент, который нужно удалить из массива.
     * @return true, если элемент удалось удалить и false, если - нет.
     */
    @Override
    public boolean remove(E e) {
        boolean result = false;
        if ((e != null) && (contains(e))) {
            System.arraycopy(getObjects(), valPosition + 1, getObjects(), valPosition,
                    getObjects().length - valPosition - 1);
            count--;
            result = true;
        }
        return result;
    }

    /**
     * Выводит на экран все элементы хэш-таблицы.
     */
    @Override
    public void showElements() {
        for (int i = 0; i < count; i++) {
            System.out.print(getObjects()[i] + " ");
        }
    }
}