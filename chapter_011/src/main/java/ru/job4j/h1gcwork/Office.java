package ru.job4j.h1gcwork;

import org.apache.log4j.Logger;
import ru.job4j.utils.Utils;

/**
 * @author Vitaly Vasilyev, date: 16.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Office {
    /**
     * Логгер.
     */
    private static final Logger LOG = Logger.getLogger(Utils.getNameOfTheClass());
    private static final Runtime RUNTIME = Runtime.getRuntime();

    /**
     * Состояние памяти.
     */
    public static void info() {
//        int mb = 1024 * 1024;
//        int mb = 1024;
        int mb = 1;

        LOG.info("Max memory: " + RUNTIME.maxMemory() / mb);
        LOG.info("Total memory: " + RUNTIME.totalMemory() / mb);
        LOG.info("Free memory: " + RUNTIME.freeMemory() / mb);
        LOG.info("Used memory: " + (RUNTIME.totalMemory() - RUNTIME.freeMemory()) / mb);
    }

    /**
     * @param args аргс.
     */
    public static void main(String[] args) {
        info();
//        new User(1);
//        new String();
//        int a;
        for (int i = 0; i < 15_000; i++) {
            User u = new User(i);
        }
        info();
    }
}