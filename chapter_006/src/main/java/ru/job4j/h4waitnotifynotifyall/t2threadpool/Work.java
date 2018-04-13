package ru.job4j.h4waitnotifynotifyall.t2threadpool;
/**
 * @author Rav, date: 07.04.2018
 * @version 1.0
 */
public class Work {
    /**
     * ID работы.
     */
    private int id;

    /**
     * @param id работы.
     */
    public Work(int id) {
        this.id = id;
    }

    /**
     * Метод работы.
     */
    public void begin() {
        System.out.println(String.format("%s runs Work# %d", Thread.currentThread().getName(), id));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}