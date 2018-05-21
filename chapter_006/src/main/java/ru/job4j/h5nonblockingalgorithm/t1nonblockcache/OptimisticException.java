package ru.job4j.h5nonblockingalgorithm.t1nonblockcache;

/**
 * Класс исключения.
 */
public class OptimisticException extends RuntimeException {

    public OptimisticException() {
        System.out.println("Optimistic Exception");
    }
}