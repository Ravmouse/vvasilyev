package ru.job4j.h3monitorsynchronized.t2userstorage;

/**
 * An interface.
 */
public interface UserStorage {
    /**
     * @param user to be added.
     * @return true if the user is added successfully and false otherwise.
     */
    boolean add(User user);

    /**
     * @param user to be updated.
     * @param amount to be changed.
     * @return true if the user is updated successfully and false otherwise.
     */
    boolean update(User user, int amount);

    /**
     * @param user to be deleted.
     * @return true if the user is deleted successfully and false otherwise.
     */
    boolean delete(User user);

    /**
     * @param fromId is the ID of the first user.
     * @param toId is the ID of the second user.
     * @param amount to be replaced from the first user to the second one.
     * @return true if the transfer is complete successfully.
     */
    boolean transfer(int fromId, int toId, int amount);
}