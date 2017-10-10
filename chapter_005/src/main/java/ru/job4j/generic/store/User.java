package ru.job4j.generic.store;

/**
 * User class.
 * Этот класс расширяет класс Base и переопределяет 2 метода: getId() и setId(String id).
 */
public class User extends Base {
    /**
     * Name of User.
     */
    private String name;
    /**
     * Id of User.
     */
    private String id;
    /**
     * User's age.
     */
    private int age;

    /**
     * @param name for initialization.
     * @param id for initialization.
     * @param age for initialization.
     */
    public User(String name, String id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    /**
     * @return id of User.
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