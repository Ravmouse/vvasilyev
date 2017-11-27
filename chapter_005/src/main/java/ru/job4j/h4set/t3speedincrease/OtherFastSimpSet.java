package ru.job4j.h4set.t3speedincrease;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @param <E> - обобщенный параметр.
 */
public class OtherFastSimpSet<E> implements Iterable<E> {
    /**
     * Кол-во добавленных элементов в основном массиве.
     */
    private int count;
    /**
     * Массив элементов обобщенного типа.
     */
    private E[] objects;
    /**
     * Индекс элемента, перебираемого итератором.
     */
    private int index;

    /**
     * @param size - кол-во элементов в массиве при создании объекта.
     */
    public OtherFastSimpSet(int size) {
        if (size > 0) {
            objects = (E[]) new Object[size];
        }
    }

    /**
     * @param e - элемент для добавления.
     * @return true, если элемент добавлен и false, если элемент не добавлен.
     */
    public boolean add(E e) {
        boolean result = false;
        if (e != null) {
            if (count == (objects.length * 3) / 4) {
                increaseCapacity();
            }
            int h = hashFunc(e);
            while (objects[h] != null) {
                if (!objects[h].equals(e)) {
                    h++;                       // Линейное пробирование
                    h %= objects.length;
                } else {
                    return false;
                }
            }
            objects[h] = e;
            count++;
            result = true;
        }
        return result;
    }

    /**
     * Создается новый массив Object'ов с длиной в 2 раза больше имеющегося, затем приводится к типу Е.
     * И для каждого элемента имеющегося массива, который != null, вызывается replace().
     */
    private void increaseCapacity() {
        E[] newObjects = (E[]) (new Object[objects.length * 2]);
        for (E e : objects) {
            if (e != null) {
                replace(e, newObjects);
            }
        }
        objects = newObjects;
    }

    /**
     * Вычисляет хэш код для каждого элемента типа Е по отношению к длине нового массива
     * и вставляет каждый элемент из старого массива в новый.
     * @param e - элемент для вставки в новый массив увеличенной длины.
     * @param newArray - новый массив увеличенной длины.
     */
    private void replace(E e, E[] newArray) {
        int h = Math.abs(e.hashCode());
        h = h % newArray.length;
        while (newArray[h] != null) {
            h++;
            h %= newArray.length;
        }
        newArray[h] = e;
    }

    /**
     * @param e - элемент, который д.б. проверен на наличие в массиве.
     * @return true, если такой элемент имеется и false, если такого элемента нет.
     */
    public boolean contains(E e) {
        boolean result = false;
        if (e != null) {
            int h = hashFunc(e);
            while (objects[h] != null) {
                if (objects[h].equals(e)) {
                    result = true;
                    break;
                }
                h++;
                h %= objects.length;
            }
        }
        return result;
    }

    /**
     * @param e - элемент, который д.б. удален.
     * @return true, если элемент удалось удалить и false, если - нет.
     */
    public boolean remove(E e) {
        boolean result = false;
        if (e != null) {
            int h = hashFunc(e);
            while (objects[h] != null) {
                if (objects[h].equals(e)) {
                    objects[h] = null;
                    result = true;
                    break;
                }
                h++;
                h %= objects.length;
            }
        }
        return result;
    }

    /**
     * @param e - элемент, для которого рассчитывается хэш ф-я.
     * @return целочисленное значение.
     */
    private int hashFunc(E e) {
        int h = Math.abs(e.hashCode());
        h %= objects.length;
        return h;
    }

    /**
     * Выводит на экран все элементы хэш таблицы.
     */
    public void showElements() {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] != null) {
                System.out.print(objects[i] + " ");
            } else {
                System.out.print("** ");
            }
        }
        System.out.println();
    }

    /**
     * @return - итератор, проходящий по всем элементам.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                boolean result = false;
                if ((index < count) && (objects[index] != null)) {
                    result = true;
                }
                return result;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    return objects[index++];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}