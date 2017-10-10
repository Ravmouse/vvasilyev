package ru.job4j.generic.store;

/**
 * Role class.
 * Этот класс расширяет класс Base и переопределяет 2 метода: getId() и setId(String id).
 */
public class Role extends Base {
    /**
     * Title of Role.
     */
    private String title;
    /**
     * Id of Role.
     */
    private String id;

    /**
     * @param title for initialization.
     * @param id for initialization.
     */
    public Role(String title, String id) {
        this.title = title;
        this.id = id;
    }

    /**
     * @return string object.
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * @param id string object to be set.
     */
    @Override
    public void setId(String id) {
        this.id = id;
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