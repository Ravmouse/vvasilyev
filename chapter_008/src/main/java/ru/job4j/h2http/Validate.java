package ru.job4j.h2http;

/**
 * Интерфейс.
 */
public interface Validate {
    /**
     * @param id id.
     * @param name имя.
     * @param login логин.
     * @param email эл.почта.
     * @param createDate дата создания.
     */
    void add(String id, String name, String login, String email, String createDate);

    /**
     * @param id id.
     * @param name имя.
     * @param login логин.
     * @param email эл.почта.
     * @param createDate дата создания.
     */
    void update(String id, String name, String login, String email, String createDate);

    /**
     * @param id id User'а, которого нужно удалить.
     */
    void delete(String id);

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