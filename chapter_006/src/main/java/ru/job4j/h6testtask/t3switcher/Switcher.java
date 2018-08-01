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
     * @throws InterruptedException если поток был прерван.
     */
    public void init() throws InterruptedException {
        CountDownLatch latchFirst = new CountDownLatch(1); //Этот latch нужен для того, чтобы 2 потока ждали в одной
                                                           //точке, пока main не даст им отмашку на начало работы.
        CountDownLatch latchLast = new CountDownLatch(2); //Этот latch нужен для того, чтобы main ждал завершения
                                                          //работы 2-х потоков.
        AtomicBoolean flag = new AtomicBoolean(); //Нужна для проверки, догоняет ли один поток другой или нет?
        new Thread(() -> {
            try {
                latchFirst.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int count = 0;
            if (flag.get()) {              //Первый из 2-х потоков проходит if-секцию, т.к. flag = false.
                counter.incrementAndGet(); //Второй из 2-х потоков, если догоняет первый, инкрементирует counter, т.к.
            }                              //первый из 2-х потоков успел переключить flag на true.
            flag.set(true);
            while (count++ < 10) {
                addValue(1);
            }
            flag.set(false);
            latchLast.countDown();
        }).start();
        new Thread(() -> {
            try {
                latchFirst.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int count = 0;
            if (flag.get()) {
                counter.incrementAndGet();
            }
            flag.set(true);
            while (count++ < 10) {
                addValue(2);
            }
            flag.set(false);
            latchLast.countDown();
        }).start();
        latchFirst.countDown();
        latchLast.await();
    }

    /**
     * @return ссылку на счетчик.
     */
    public AtomicInteger getCounter() {
        return counter;
    }

    /**
     * @param args .
     * @throws InterruptedException .
     */
    public static void main(String[] args) throws InterruptedException {
        Switcher s = new Switcher();
        s.init();
        s.printString();
    }
}