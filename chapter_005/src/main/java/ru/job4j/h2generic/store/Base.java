package ru.job4j.h2generic.store;

/**
 * Base class.
 * Абстрактный класс, в котором есть поле id, конструктор и объявлены 2 метода: получить id и установить id.
 */
public abstract class Base {
    /**
     * Id of User.
     */
    private String id;

    /**
     * @param id for initialization.
     */
    public Base(String id) {
        this.id = id;
    }

    /**
     * @return id of this class.
     */
    public String getId() {
        return this.id;
    }

    /**
     * @param id string object to be set.
     */
    public void setId(String id) {
        this.id = id;
    }
}