package ru.job4j.h6testtask.t3switcher;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;
/**
 * Класс Switcher.
 * Два потока поочередно добавляют в StringBuilder набор единиц и двоек.
 */
public class Switcher {
    /**
     * Строка, куда добавляются новые строки в виде наборов чисел.
     */
    private final StringBuilder string = new StringBuilder("");
    /**
     * Переменная для синхронизации работы двух потоков.
     */
    private AtomicInteger synchInt = new AtomicInteger(1);
    /**
     * Переменная для подсчета того, сколько раз один поток "догоняет" другой поток.
     */
    private AtomicInteger counter = new AtomicInteger();


    /**
     * Один из двух потоков 10 раз добавляет в StringBuilder свое значение.
     * @param value значение.
     */
    private void addValue(int value) {
        while (synchInt.get() != value) {
            //Ничего не делать.
            byte x = 0; //Чтобы maven-checkstyle-plugin не ругался, что while - пустой.
        }
        for (int i = 0; i < 10; i++) {
            this.string.append(value);
        }
        synchInt.set(synchInt.get() == 1 ? 2 : 1);
    }

    /**
     * Выводит на печать поле string данного экземпляра класса.
     */
    public void printString() {
        int count = 0;
        for (char ch : string.toString().toCharArray()) {
            ++count;
            System.out.print(ch);
            if (count % 10 == 0) {
                System.out.println();
            }
        }
    }

    /**
     * В цикле создаются 2 потока, которые после своего запуска ждут, когда main "даст отмашку" для начала работы.
     * Потоки инкрементируют AtomicInteger для вызова метода addValue() 10 раз.
     * Последний latch нужен для вызова метода printString(), чтобы main подождал завершения работы всех потоков.
     * @throws InterruptedException .
     */
    public void init() throws InterruptedException {
        int threads = 2;
        CountDownLatch latchFirst = new CountDownLatch(1);
        CountDownLatch latchLast = new CountDownLatch(threads);
        AtomicInteger threadVal = new AtomicInteger();
        AtomicBoolean flag = new AtomicBoolean();
        for (int i = 0; i < threads; i++) {
            new Thread(() -> {
                try {
                    latchFirst.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int count = 0;
                int value = threadVal.incrementAndGet(); //Каждый поток инкрементирует перем. для вызова addValue().
                if (flag.get()) {                        //1-й поток проходит if-секцию,т.к. flag = false.
                    counter.incrementAndGet();           //2-й поток увеличивает счетчик,т.к. 1-й поток ниже уже
                }                                        //изменил flag на true.
                flag.set(true);
                while (count++ < 10) {
                    addValue(value);
                }
                flag.set(false);
                latchLast.countDown();
            }).start();
        }
        latchFirst.countDown();
        latchLast.await();
    }

    /**
     * @return ссылку на счетчик.
     */
    public AtomicInteger getCounter() {
        return counter;
    }
}