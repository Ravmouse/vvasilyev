package ru.job4j.generic.store;

/**
 * Base class.
 * Абстрактный класс, в котором объявлены 2 метода для переопределения: получить id и установить id.
 */
public abstract class Base {
    /**
     * @return string object.
     */
    public abstract String getId();

    /**
     * @param id string object to be set.
     */
    public abstract void setId(String id);
}