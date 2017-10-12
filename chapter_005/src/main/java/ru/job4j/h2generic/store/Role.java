package ru.job4j.h2generic.store;

/**
 * Role class.
 * Этот класс расширяет класс Base.
 */
public class Role extends Base {
    /**
     * Title of Role.
     */
    private String title;

    /**
     * @param id for initialization.
     * @param title for initialization.
     */
    public Role(String title, String id) {
        super(id);
        this.title = title;
    }

    /**
     * @return string object.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @param title string object to be set.
     */
    public void setTitle(String title) {
        this.title = title;
    }
}