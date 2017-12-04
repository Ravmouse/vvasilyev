package ru.job4j.h5map.t8myownhashmap;

/**
 * @param <K> ключ.
 * @param <V> значение.
 */
public class Node<K, V> {
    /**
     * Ключ.
     */
    private K key;
    /**
     * Значение.
     */
    private V value;

    /**
     * @param key - ключ для инициализации.
     * @param value - значение для инициализации.
     */
    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return ключ.
     */
    public K getKey() {
        return key;
    }

    /**
     * @return значение.
     */
    public V getValue() {
        return value;
    }
}