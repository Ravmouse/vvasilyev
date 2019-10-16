package ru.job4j.h1gcwork;

/**
 * @author Vitaly Vasilyev, date: 16.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class User {
    /**
     * Имя.
     */
    private String name;
    /**
     * Номер.
     */
    private int id;

    /**
     * @param id номер.
     */
    public User(int id) {
        this.id = id;
    }

    /**
     * @throws Throwable искл.
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("GC is doing his job! " + id);
    }

    @Override
    public String toString() {
        return String.format("User #%d", id);
    }
}