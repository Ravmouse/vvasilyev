package ru.job4j.h4waitnotifynotifyall.t2threadpool;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @param <T> - обобщенный тип.
 */
@ThreadSafe
public class SBQ<T> {
    /**
     * Ссылка типа "очередь" на связный список.
     */
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    /**
     * Максимальное число элементов в очереди.
     */
    private final int size;

    /**
     * @param size - размер блокирующей очереди.
     */
    public SBQ(int size) {
        this.size = size;
    }

    /**
     * Добавляет элемент в блокирующую очередь.
     * @param value - элемент для добавления.
     * @throws InterruptedException в случае возникновения исключения.
     */
    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == size) {
            this.wait();
        }
        queue.offer(value);
        this.notifyAll();
    }

    /**
     * Получает первый элемент блокирующей очереди.
     * @return первый элемент.
     * @throws InterruptedException в случае возникновения исключения.
     */
    public  synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }
        T rsl = queue.poll();
        this.notifyAll();
        return rsl;
    }

    /**
     * @return размер блокирующей очереди.
     */
    public int getSize() {
        return size;
    }
}