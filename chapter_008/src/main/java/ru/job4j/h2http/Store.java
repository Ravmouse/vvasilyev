package ru.job4j.h2http;
import ru.job4j.h6filter.Role;
import java.util.List;

/**
 * Интерфейс для целей хранения, получения, удаления данных в памяти (Heap) или БД.
 */
public interface Store {
    /**
     * @param list список строк для добавления данных в хранилище.
     * @param number номер для таблицы users, которая ссылается на таблицу roles.
     */
    void add(final List<String> list, int number);
    /**
     * @param id номер юзера.
     * @param list список строк для изменения данных в хранилище.
     * @param number номер для таблицы users, которая ссылается на таблицу roles.
     */
    void update(int id, final List<String> list, int number);
    /**
     * @param id номер, по которому удаляется строка из хранилища.
     */
    void delete(int id);
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
     * @param login логин юзера.
     * @param password пароль юзера.
     * @return роль юзера.
     */
    Role findRoleByLoginPassword(String login, String password);
}