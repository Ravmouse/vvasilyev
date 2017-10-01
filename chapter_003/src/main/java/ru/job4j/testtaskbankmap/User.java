package ru.job4j.testtaskbankmap;
import java.util.Objects;

/**
 * User class.
 */
public class User {
    /**
     * Name of User.
     */
    private String name;
    /**
     * Passport number of User.
     */
    private int passport;

    /**
     * @param name of User.
     * @param passport number of User.
     */
    public User(String name, int passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * @param obj to be compared.
     * @return true if the object is equal to obj parameter and false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return this.name.equals(user.name) && this.passport == user.passport;
    }

    /**
     * @return int value that is a hashcode of the User object.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + passport;
        result = 31 * result + Objects.hash(name);
        return result;
    }

    /**
     * @return string representation of the User object.
     */
    public String toString() {
        return String.format("name: %s, passport: %d", name, passport);
    }
}