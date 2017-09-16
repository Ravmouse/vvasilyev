package ru.job4j.tracker;

/**
 * Abstract class BaseAction.
 */
public abstract class BaseAction implements UserAction {
    /**
     * Int value.
     */
    private int key;
    /**
     * String value.
     */
    private String name;

    /**
     * The constructor.
     * @param key key to place in the object.
     * @param name name to place in the object.
     */
    public BaseAction(int key, String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Returns the key value.
     * @return int.
     */
    public int key() {
        return  key;
    }

    /**
     * Displays the String with the key and the name values.
     * @return String.
     */
    public String info() {
        return String.format("%s. %s", key(), this.name);
    }
}