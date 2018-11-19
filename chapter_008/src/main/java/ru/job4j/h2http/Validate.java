package ru.job4j.h2http;
import java.util.List;

/**
 * Интерфейс для целей проверки данных перед их добавлением/изменением в БД.
 */
public interface Validate {
    /**
     * @param list список строк для добавления данных в хранилище.
     */
    void add(final List<String> list);
    /**
     * @param id   номер.
     * @param list список строк для изменения данных в хранилище.
     */
    void update(int id, final List<String> list);
    /**
     * @param id номер, по которому удаляется строка в хранилище.
     */
    void delete(int id);
    /**
     * @return список юзеров.
     */
    List<User> findAll();
    /**
     * @param id номер.
     * @return юзера по его номеру.
     */
    User findById(int id);
}