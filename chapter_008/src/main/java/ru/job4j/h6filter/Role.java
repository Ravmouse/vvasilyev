package ru.job4j.h6filter;

/**
 * @author Vitaly Vasilyev, date: 28.11.2018, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Role {
    /**
     * Номер роли.
     */
    private int id;
    /**
     * Название роли.
     */
    private String name;

    /**
     * @param name имя для присвоения.
     */
    public Role(final String name) {
        init(name);
        this.name = name;
    }

    /**
     * @return название роли.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return номер роли.
     */
    public int getId() {
        return id;
    }

    /**
     * @return строковое представление объекта.
     */
    @Override
    public String toString() {
        return String.format("Role=%s", this.name);
    }

    /**
     * Получается номер роли при соответствующем названии.
     * @param name название роли.
     */
    private void init(final String name) {
        switch (name) {
            case "Administrator":   this.id = 1;
                                    break;
            case "Anonymous":       this.id = 2;
                                    break;
            case "Moderator":       this.id = 3;
                                    break;
            default:                this.id = 4;
                                    break;
        }
    }
}