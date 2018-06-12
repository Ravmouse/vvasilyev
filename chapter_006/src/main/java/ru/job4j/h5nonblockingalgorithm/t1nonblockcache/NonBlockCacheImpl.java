package ru.job4j.h5nonblockingalgorithm.t1nonblockcache;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @param <K> обобщенный параметр.
 * @param <V> обобщенный параметр, расширяющий класс Model.
 */
public class NonBlockCacheImpl<K, V extends Model> implements NonBlockCache<K, V> {
    /**
     * Потокобезопасное хэш-отображение.
     */
    private final ConcurrentHashMap<K, V> cache = new ConcurrentHashMap<>();

    /**
     * @param k ключ для добавления в отображение.
     * @param v значение для ключа k.
     */
    @Override
    public void add(K k, V v) {
        if ((k != null) && (v != null)) {
            cache.put(k, v);
        }
    }

    /**
     * @param k ключ, значение которого нужно заменить.
     * @param newVal новое значение для ключа k.
     * @throws OptimisticException в случае возникновения исключения.
     */
    @Override
    public void update(K k, V newVal) throws OptimisticException {
        if ((k != null) && (newVal != null)) {
            int expect = this.cache.get(k).getVersion().get(); //В каждом потоке есть лок.переменная expect,
                                                               //куда копируется значение версии Модели.
            boolean done = this.cache.get(k).getVersion().compareAndSet(expect, ++expect); //Атомарно сравнивается
            if (!done) {                                       //значение лок.переменной и опять значение версии Модели.
                throw new OptimisticException();               //Если они равны (т.е. другой поток не успел изменить),то
            }                                                  //значение версии Модели инкрементируется.
            this.cache.get(k).setName(newVal.getName()); //Здесь имя текущей Модели по ключу k изменяется на имя Модели newVal.
        }
    }

    /**
     * @param k ключ, который будет удален вместе со своим значением.
     */
    @Override
    public void delete(K k) {
        cache.remove(k);
    }

    /**
     * @return размер отображения.
     */
    public int size() {
        return this.cache.size();
    }

    /**
     * Выводит на печать все ключи и их значения.
     */
    public void show() {
        for (Map.Entry<K, V> me : cache.entrySet()) {
            System.out.println("[" + me.getKey() + "] [" + me.getValue() + "]");
        }
    }
}