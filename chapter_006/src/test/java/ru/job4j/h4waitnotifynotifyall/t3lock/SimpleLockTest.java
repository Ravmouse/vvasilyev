package ru.job4j.h4waitnotifynotifyall.t3lock;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Rav, date: 18.04.2018
 * @version 1.0
 */
public class SimpleLockTest {
    /**
     * Ссылка на объект типа SimpleLock.
     */
    private SimpleLock simpleLock = new SimpleLock();
    /**
     * Количество повторений в цикле.
     */
    private static final int COUNT = 1_000_000;
    /**
     * Результирующая переменная.
     */
    private int result;

    /**
     * В зависимости от знака инкрементирует или декрементирует переменную result.
     * @param sign - знак "+" или "-".
     */
    private void execute(char sign) {
        for (int i = 0; i < COUNT; i++) {
            if (sign == '+') {
                result++;
            } else {
                result--;
            }
        }
        System.out.println("result is: " + result);
    }

    /**
     * Выполняет метод execute() со знаком "+".
     * @throws InterruptedException в случае возникновения исключения.
     */
    private void increment() throws InterruptedException {
        simpleLock.lock();
        try {
            execute('+');
        } finally {
            simpleLock.unlock();
        }
    }

    /**
     * Выполняет метод execute() со знаком "-".
     * @throws InterruptedException в случае возникновения исключения.
     */
    private void decrement() throws InterruptedException {
        simpleLock.lock();
        try {
            execute('-');
        } finally {
            simpleLock.unlock();
        }
    }

    /**
     * Для проверки реентерабельности.
     * Повторяет метод increment(), но, не отпуская simpleLock, вызывает decrement(), в котором происходит
     * повторный захват simpleLock.
     * @throws InterruptedException в случае возникновения исключения.
     */
    private void incAndDec() throws InterruptedException {
        simpleLock.lock();
        try {
            execute('+');
            decrement();
        } finally {
            simpleLock.unlock();
        }
    }

    /**
     * В каждом потоке вызывается только increment() для проверки того, что потоки поочередно
     * инкрементируют переменную result.
     * @throws InterruptedException в случае возникновения исключения.
     */
    @Test
    public void whenIncrementFromDiffThreadsThenValueIsCorrect() throws InterruptedException {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Thread(() -> {
                try {
                    increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }
        for (Thread t : list) {
            t.start();
        }
        for (Thread t : list) {
            t.join();
        }
        assertThat(result, is(10_000_000));
    }

    /**
     * В каждом потоке вызывается incAndDec() для проверки реентерабельности.
     * @throws InterruptedException в случае возникновения исключения.
     */
    @Test
    public void whenIncAndDecFromDiffThreadsThenValueIsCorrect() throws InterruptedException {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Thread(() -> {
                try {
                    incAndDec();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }
        for (Thread t : list) {
            t.start();
        }
        for (Thread t : list) {
            t.join();
        }
        assertThat(result, is(0));
    }
}