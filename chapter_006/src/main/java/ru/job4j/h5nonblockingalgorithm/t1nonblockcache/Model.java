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
     * @return имя данного экземпляра класса.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name новое имя модели.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return строковое представление модели.
     */
    public String toString() {
        return this.name;
    }
}