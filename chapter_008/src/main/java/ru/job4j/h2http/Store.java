package ru.job4j.h2http;

import java.util.List;

/**
 * Интерфейс.
 */
public interface Store {
    /**
     * @param user User, который нужно добавить.
     */
    void add(final User user);

    /**
     * @param id id User'а, которого нужно найти.
     * @param user User, который нужно заменить.
     */
    void update(int id, final User user);

    /**
     * @param user User, которого нужно удалить.
     */
    void delete(final User user);

    /**
     * @return список User'ов.
     */
    List<User> findAll();

    /**
     * @param id номер User'а.
     * @return User'а по его номеру.
     */
    User findById(int id);
}