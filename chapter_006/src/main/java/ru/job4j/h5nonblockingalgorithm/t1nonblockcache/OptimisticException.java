package ru.job4j.h5nonblockingalgorithm.t1nonblockcache;

/**
 * Класс исключения.
 */
public class OptimisticException extends RuntimeException {

    /**
     * Конструктор.
     */
    public OptimisticException() {
        System.out.println(Thread.currentThread().getName() + " threw Optimistic Exception");
    }
}