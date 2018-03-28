package ru.job4j.h4waitnotifynotifyall.t1producerconsumer;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Rav, date: 29.03.2018
 * @version 1.0
 * @param <T> - обобщенный тип.
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    /**
     * Ссылка типа "очередь" на связный список.
     */
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    /**
     * Счетчик кол-ва добавленных элементов.
     */
    private int count;
    /**
     * Максимальное число элементов в очереди.
     */
    private static final int MAX_SIZE = 5;


    /**
     * Добавляет элемент в блокирующую очередь.
     * @param value - элемент для добавления.
     * @throws InterruptedException в случае возникновения исключения.
     */
    public synchronized void offer(T value) throws InterruptedException {
        while (count == MAX_SIZE) {
            this.wait();
        }
        queue.offer(value);
        System.out.println("Producer puts: " + value);
        Thread.sleep(1000);
        count++;
        this.notify();
    }

    /**
     * Получает первый элемент блокирующей очереди.
     * @return первый элемент.
     * @throws InterruptedException в случае возникновения исключения.
     */
    public  synchronized T poll() throws InterruptedException {
        while (count == 0) {
            this.wait();
        }
        T rsl = queue.poll();
        System.out.println("Consumer takes: " + rsl);
        Thread.sleep(1000);
        count--;
        this.notify();
        return rsl;
    }
}