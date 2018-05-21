package ru.job4j.h5nonblockingalgorithm.t1nonblockcache;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс Model.
 */
public class Model {
    /**
     * Название модели.
     */
    private String name;

    /**
     * Версия модели.
     */
    private AtomicInteger version;

    /**
     * @param name название модели.
     */
    public Model(String name) {
        this.name = name;
        this.version = new AtomicInteger(0);
    }

    /**
     * @return версия модели.
     */
    public AtomicInteger getVersion() {
        return this.version;
    }

    /**
     * @param version значение для установки поля version.
     */
    public void setVersion(int version) {
        this.version.set(version);
    }

    /**
     * @return строковое представление модели.
     */
    public String toString() {
        return this.name;
    }
}