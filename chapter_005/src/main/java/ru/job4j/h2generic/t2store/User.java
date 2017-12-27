package ru.job4j.h2generic.t2store;

/**
 * User class.
 * Этот класс расширяет класс Base.
 */
public class User extends Base {
    /**
     * Name of User.
     */
    private String name;

    /**
     * User's age.
     */
    private int age;

    /**
     * @param id for initialization.
     * @param name for initialization.
     * @param age for initialization.
     */
    public User(String name, String id, int age) {
        super(id);
        this.name = name;
        this.age = age;
    }

    /**
     * @return name of User.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name string object to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return age of User.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * @param age string object to be set.
     */
    public void setAge(int age) {
        this.age = age;
    }
}