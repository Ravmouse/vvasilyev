package ru.job4j.h2http;
import ru.job4j.h6filter.Role;
import java.util.List;

/**
 * Интерфейс для целей хранения, получения, удаления данных из памяти (Heap) или БД.
 */
public interface Store {
    /**
     * @param user юзер, чьи данные нужно добавить в БД.
     */
    void add(final User user);
    /**
     * @param user юзер, чьи данные нужно обновить в БД.
     */
    void update(final User user);
    /**
     * @param user юзер, чьи данные нужно удалить из БД.
     */
    void delete(final User user);
    /**
     * @return список юзеров.
     */
    List<User> findAll();
    /**
     * @param id номер юзера для нахождения в хранилище.
     * @return юзера по его номеру.
     */
    User findById(int id);
    /**
     * @return список ролей.
     */
    List<Role> findAllRoles();
    /**
     * @param user юзер, чью роль нужно найти.
     * @return роль юзера.
     */
    Role findRole(final User user);
}