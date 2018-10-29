package ru.job4j.h2http;

import java.util.List;

/**
 * Интерфейс.
 */
public interface Validate {
    /**
     * @param name имя.
     * @param login логин.
     * @param email эл.почта.
     * @param createDate дата создания.
     */
    void add(String name, String login, String email, String createDate);

    /**
     * @param id id User'а.
     * @param list список строк со значениями User'а.
     */
    void update(int id, List<String> list);

    /**
     * @param id id User'а, которого нужно удалить.
     */
    void delete(int id);

    /**
     * Возвращается массив, чтобы было проще отобразить его с помощью Arrays.toString().
     * @return массив User'ов.
     */
    User[] findAll();

    /**
     * @param id номер User'а.
     * @return ссылку на найденного User'а.
     */
    User findById(int id);
}