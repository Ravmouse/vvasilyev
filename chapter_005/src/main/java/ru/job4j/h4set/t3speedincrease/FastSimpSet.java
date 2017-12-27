package ru.job4j.h4set.t3speedincrease;

/**
 * @param <E> - обобщенный параметр.
 */
public class FastSimpSet<E> extends AbstractSimpSet<E> {
    /**
     * Кол-во добавленных элементов в основном массиве.
     */
    private int count;
    /**
     * Ссылка на массив элементов типа Е для создания массива увеличенной длины.
     */
    private E[] newObjects;
    /**
     * Переменная для хранения значения хэш-кода каждого из элементов.
     */
    private int hashVal;

    /**
     * @param size - кол-во элементов в массиве при создании объекта.
     */
    public FastSimpSet(int size) {
        super(size);
    }

    /**
     * @param e - элемент для добавления.
     * @return true, если элемент добавлен и false, если элемент не добавлен.
     */
    @Override
    public boolean add(E e) {
        boolean result = false;
        if (e != null) {
            if (count == (getObjects().length * 3) / 4) {
                increaseCapacity();
            }
            hashFunc(e);
            while (getObjects()[hashVal] != null) {
                if (!getObjects()[hashVal].equals(e)) {
                    hashVal++;                       // Линейное пробирование
                    hashVal %= getObjects().length;
                } else {
                    return false;
                }
            }
            setObjectElem(e, hashVal);
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
        newObjects = (E[]) (new Object[getObjects().length * 2]);
        for (int i = 0; i < getObjects().length; i++) {
            if (getObjects()[i] != null) {
                replace(getObjects()[i]);
            }
        }
        setObjects(newObjects);
    }

    /**
     * Вычисляет хэш-код для каждого элемента типа Е по отношению к длине нового массива.
     * @param e - элемент для вставки в новый массив увеличенной длины (newObjects).
     */
    private void replace(E e) {
        int h = e.hashCode();
        if (h < 0) {
            h = -h;
        }
        h = h % newObjects.length;
        while (newObjects[h] != null) {
            h++;
            h %= newObjects.length;
        }
        newObjects[h] = e;
    }

    /**
     * @param e - элемент, который д.б. проверен на наличие в массиве.
     * @return true, если такой элемент имеется и false, если такого элемента нет.
     */
    @Override
    public boolean contains(E e) {
        boolean result = false;
        if (e != null) {
            hashFunc(e);
            while (getObjects()[hashVal] != null) {
                if (getObjects()[hashVal].equals(e)) {
                    result = true;
                    break;
                }
                hashVal++;
                hashVal %= getObjects().length;
            }
        }
        return result;
    }

    /**
     * @param e - элемент, который д.б. удален.
     * @return true, если элемент удалось удалить и false, если - нет.
     */
    @Override
    public boolean remove(E e) {
        boolean result = false;
        if (e != null) {
            hashFunc(e);
            while (getObjects()[hashVal] != null) {
                if (getObjects()[hashVal].equals(e)) {
                    getObjects()[hashVal] = null;
                    result = true;
                    break;
                }
                hashVal++;
                hashVal %= getObjects().length;
            }
        }
        return result;
    }

    /**
     * @param e - элемент, для которого рассчитывается хэш ф-я
     */
    private void hashFunc(E e) {
        hashVal = e.hashCode();
        if (hashVal < 0) {
            hashVal = -hashVal;
        }
        hashVal %= getObjects().length;
    }

    /**
     * Выводит на экран все элементы хэш-таблицы.
     */
    @Override
    public void showElements() {
        for (int i = 0; i < getObjects().length; i++) {
            if (getObjects()[i] != null) {
                System.out.print(getObjects()[i] + " ");
            } else {
                System.out.print("** ");
            }
        }
        System.out.println();
    }

    /**
     * @return длина массива.
     */
    public int size() {
        return getObjects().length;
    }
}