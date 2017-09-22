package ru.job4j.usersort;

/**
 * Class User.
 */
public class User implements Comparable<User> {
    /**
     * The name of User.
     */
    private String name;
    /**
     * The age of User.
     */
    private int age;

    /**
     * @param name of User.
     * @param age of User.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * @return age of User.
     */
    public int getAge() {
        return age;
    }

    /**
     * @return name of User.
     */
    public String getName() {
        return name;
    }

    /**
     * @param obj User object to be compared.
     * @return the value 0, less than 0 or greater than 0.
     */
    @Override
    public int compareTo(final User obj) {
        final int value = Integer.compare(this.getAge(), obj.getAge());
        return value != 0 ? value : this.getName().compareTo(obj.getName());
    }

    /**
     * @return A formatted string.
     */
    public String toString() {
        return String.format("Name: %s, age: %d", name, age);
    }
}