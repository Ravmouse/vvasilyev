package ru.job4j.tracker;

/**
 * Class MenuOutException.
 */
public class MenuOutException extends RuntimeException {
    /**
     * The constructor takes the String parameter and passes it to the constructor of superclass.
     * @param str str.
     */
    public MenuOutException(String str) {
        super(str);
    }
}