package ru.job4j.userconvert;
import java.util.Random;

/**
 * Class User.
 */
public class User {
    /**
     * User id.
     */
    private int id;
    /**
     * User name.
     */
    private String name;
    /**
     * City of User.
     */
    private String city;

    /**
     * The constructor.
     * @param name of User.
     * @param city of User.
     */
    public User(String name, String city) {
        this.id = generateId();
        this.name = name;
        this.city = city;
    }

    /**
     * The get method.
     * @return id of User.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Generates random value for User's id field.
     * @return generated id.
     */
    private int generateId() {
        return Math.abs((int) System.currentTimeMillis() + new Random().nextInt());
    }

    /**
     * toString method.
     * @return string representation.
     */
    public String toString() {
        return "id: " + id + ", name: " + name + ", city: " + city;
    }
}