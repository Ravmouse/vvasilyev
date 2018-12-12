package ru.job4j.h6filter;

/**
 * @author Vitaly Vasilyev, date: 28.11.2018, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Role {
    /**
     * Имя юзера.
     */
    private String name;

    /**
     * @param name имя для присвоения.
     */
    public Role(final String name) {
        this.name = name;
    }

    /**
     * @return имя юзера.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return строковое представление объекта.
     */
    @Override
    public String toString() {
        return String.format("Role=%s", this.name);
    }
}