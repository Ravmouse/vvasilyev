package ru.job4j.h5nonblockingalgorithm.t1nonblockcache;
/**
 * @param <K> обобщенный параметр.
 * @param <V> обобщенный параметр, расширяющий класс Model.
 */
public interface NonBlockCache<K, V extends Model> {
    /**
     * @param k ключ для добавления в отображение.
     * @param v значение для ключа k.
     */
    void add(K k, V v);

    /**
     * @param k ключ, значение которого нужно заменить.
     * @param newVal новое значение для ключа k.
     * @throws OptimisticException в случае возникновения исключения.
     */
    void update(K k, V newVal) throws OptimisticException;

    /**
     * @param k ключ, который будет удален вместе со своим значением.
     */
    void delete(K k);
}