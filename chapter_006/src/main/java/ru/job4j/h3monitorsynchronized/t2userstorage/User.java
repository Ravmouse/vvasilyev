package ru.job4j.h3monitorsynchronized.t2userstorage;

/**
 * A user class.
 */
public class User implements Comparable<User> {
    /**
     * ID of user.
     */
    private final int id;
    /**
     * Amount of something.
     */
    private int amount;

    /**
     * @param id to be assigned.
     * @param amount to be assigned.
     */
    public User(final int id, final int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * The default constructor.
     */
    public User() {
        this.id = 0;
    }

    /**
     * @param user to be compared.
     * @return 0, -1 or 1.
     */
    @Override
    public int compareTo(User user) {
        int rsl = Integer.compare(this.id, user.id);
        return rsl == 0 ? Integer.compare(this.amount, user.amount) : rsl;
    }

    /**
     * @param o to be compared.
     * @return true or false.
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User u = (User) o;
        return ((this.id == u.id) && (this.amount == u.amount));
    }

    /**
     * @return the hashcode of the object.
     */
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.id;
        result = 31 * result + this.amount;
        return result;
    }

    /**
     * @param amount to be set.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return the user's amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @return the user's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * @return the string representation.
     */
    @Override
    public String toString() {
        return String.format("id: %d, amount: %d", this.id, this.amount);
    }
}