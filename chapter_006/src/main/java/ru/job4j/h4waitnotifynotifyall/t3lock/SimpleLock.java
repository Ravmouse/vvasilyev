package ru.job4j.h4waitnotifynotifyall.t3lock;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author Rav, date: 15.04.2018
 * @version 1.0
 */
@ThreadSafe
public class SimpleLock {
    /**
     * Булева переменная, как флаг.
     */
    @GuardedBy("this")
    private boolean blocked;
	/**
	 * Ссылка на тот поток, который владеет блокировкой этого объекта.
	 */
    @GuardedBy("this")
	private Thread blocker;
	/**
	 * Количество раз, когда один и тот же поток захватывает блокировку.
	 */
	@GuardedBy("this")
	private int holdCount;

    /**
     * Первый поток, заходящий в метод, изменяет булеву переменную
     * и записывает в поле экземпляра ссылку на самого себя.
     * Реентерабельность. Инкрементирует каждый раз счетчик.
     * @throws InterruptedException в случае возникновения исключения.
     */
    public void lock() throws InterruptedException {
        synchronized (this) {
			Thread currThread = Thread.currentThread();
            while (blocked && currThread != blocker) {
                wait();
            }
            blocked = true;
			blocker = currThread;
			holdCount++;
        }
    }

    /**
     * Поток заходит в метод, декрементирует счетчик и, если счетчик равен 0,
     * снова изменяет булеву переменную для захвата другими потоками.
     */
    public void unlock() {
        synchronized (this) {
            if (Thread.currentThread() == blocker) {
				holdCount--;
			}
			if (holdCount == 0) {
				blocked = false;
				notifyAll();
			}
        }
    }
}