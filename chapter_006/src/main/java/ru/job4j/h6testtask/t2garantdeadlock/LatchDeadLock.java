package ru.job4j.h6testtask.t2garantdeadlock;
import java.util.concurrent.CountDownLatch;

/**
 * В классе используются 2 экземпляра класса CountDownLatch.
 * @author Rav, date: 03.07.2018
 * @version 1.0
 */
public class LatchDeadLock {
    /**
     * Работа для процессора.
     */
    private static void sillyWork() {
        double d = 2.0;
        for (int i = 0; i < 10_000_000; i++) {
            d = Math.sin(d);
        }
    }

    /**
     * @param args .
     * @throws InterruptedException .
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countFirst = new CountDownLatch(1);
        CountDownLatch countSecond = new CountDownLatch(5);

        Thread latchOne = new Thread(() -> {
            try {
                countFirst.await();
                for (int i = 0; i < 2; i++) {
                    countSecond.countDown();
                    sillyWork();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 2 times!");
        });
        Thread latchTwo = new Thread(() -> {
            try {
                countFirst.await();
                for (int i = 0; i < 2; i++) {
                    countSecond.countDown();
                    sillyWork();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 2 times!");
        });
        latchOne.start();
        latchTwo.start();
        sillyWork();
        countFirst.countDown();
        sillyWork();
        countSecond.await();
    }
}