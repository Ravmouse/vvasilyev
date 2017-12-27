package ru.job4j.h5map.t8myownhashmap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @param <K> ключ.
 * @param <V> значение.
 */
public class HashMapArray<K, V> implements Iterator<K> {
    /**
     * Массив Node'ов, в каждом из которых хранится и ключ, и значение.
     */
    private Node[] objects;
    /**
     * Подсчет количества Node'ов, добавленных в массив.
     */
    private int count;
    /**
     * Счетчик итератора.
     */
    private int index;

    /**
     * @param size - размер массива при его создании.
     */
    public HashMapArray(int size) {
        if (size > 0) {
            while (!isPrime(size)) {
                size++;
            }
            objects = new Node[size];
        }
    }

    /**
     * @param value - число, которое должно быть проверено, является ли оно простым или нет.
     * @return true, если число - простое и false, если - нет.
     */
    private boolean isPrime(int value) {
        boolean result = true;
        int i = 2;
        for (; i < value; i++) {
            if (value % i == 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * @param key - ключ.
     * @param value - значение.
     * @return true, если удалось добавить элемент и false, если - нет.
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        if ((key != null) && (value != null)) {
            if (count == (objects.length * 3) / 4) {
                resize();
            }
            int h = hashFunc1(key);
            final int step = hashFunc2(key);
            while (objects[h] != null) {
                if (!key.equals(objects[h].getKey())) {
                    h += step;
                    h %= objects.length;
                } else {
                    return false;
                }
            }
            final Node<K, V> node = new Node<>(key, value);
            objects[h] = node;
            count++;
            result = true;
        }
        return result;
    }

    /**
     * Увеличивает размер текущего массива в 2 раза и доводит это значение до ближайшего простого числа.
     */
    private void resize() {
        Node[] tmpNodeAr;                 //Сначала просто объявляется ссылка на массив Node'ов.
        int newSize = objects.length * 2; //Далее размер основного массива умножается на 2.
        while (!isPrime(newSize)) {       //Если полученное число не является простым,
            newSize++;                    //то увеличить на 1. И так до тех пор, пока число не станет простым.
        }
        tmpNodeAr = new Node[newSize];    //Только после этого создать новый объект.
        for (Node node : objects) {       //Для всех элементов старого массива...
            if (node != null) {
                int h = Math.abs(node.getKey().hashCode());
                h %= tmpNodeAr.length;
                final int step = hashFunc2((K) node.getKey()); //Расчет шага для двойного хеширования.
                while (tmpNodeAr[h] != null) {
                    h += step;            //Сместиться на величину шага.
                    h %= tmpNodeAr.length;
                }
                tmpNodeAr[h] = node;
            }
        }
        objects = tmpNodeAr;
    }

    /**
     * @param key - ключ.
     * @return хеш-код.
     */
    private int hashFunc1(K key) {         //Здесь не нужна проверка на null, т.к. она есть в insert().
        int h = Math.abs(key.hashCode());
        h %= objects.length;
        return h;
    }

    /**
     * @param key - ключ.
     * @return величину шага смещения для двойного хеширования.
     */
    private int hashFunc2(K key) {
        return 5 - key.hashCode() % 5;
    }

    /**
     * @param key - ключ.
     * @return - значение, ассоциированное с ключом.
     */
    public V get(K key) {
        V result = null;
        if (key != null) {
            int h = hashFunc1(key);
            final int step = hashFunc2(key);
            while (objects[h] != null) {                //Пока элемент не равен null
                if (key.equals(objects[h].getKey())) {  //Если ключ равен ключу в элементе, то
                    result = (V) objects[h].getValue(); //Вернуть значение элемента
                    break;
                }                                       //Если ключ не равен ключу в элементе, то
                h += step;                              //Перешагнуть к след.ячейке
                h %= objects.length;
            }
        }
        return result;
    }

    /**
     * @param key - ключ.
     * @return true, если удалось удалить элемент и false, если - нет.
     */
    public boolean delete(K key) {
        boolean result = false;
        if (key != null) {
            int h = hashFunc1(key);
            final int step = hashFunc2(key);
            while (objects[h] != null) {
                if (key.equals(objects[h].getKey())) {
                    objects[h] = null;
                    count--;
                    result = true;
                    break;
                }
                h += step;
                h %= objects.length;
            }
        }
        return result;
    }

    /**
     * Выводит на печать все ключи хеш-таблицы.
     */
    public void show() {
        for (Node n : objects) {
            if (n != null) {
                System.out.print(" " + n.getKey() + " ");
            } else {
                System.out.print(" ** ");
            }
        }
        System.out.println();
    }

    /**
     * @return true, если еще остались элементы для итерации и false, если - нет.
     */
    @Override
    public boolean hasNext() {
        boolean result;
        if ((index < objects.length) && (objects[index] != null)) {
            result = true;
        } else {
            while ((index < objects.length) && (objects[index] == null)) { //Пока index меньше длины массива и
                index++;                                                   //пока элемент равен null, увеличивать index.
            }
            result = (index != objects.length); //Выход из цикла: если index не равен длине массива, значит есть
        }                                       //элемент, который не null. А если равен, - элементов нет.
        return result;
    }

    /**
     * @return ключ в хеш-таблице.
     */
    @Override
    public K next() {
        if (hasNext()) {
            return (K) objects[index++].getKey();
        } else {
            throw new NoSuchElementException();
        }
    }
}