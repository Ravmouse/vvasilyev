package ru.job4j.h7mockito;

import ru.job4j.h2http.User;
import ru.job4j.h6filter.Role;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vitaly Vasilyev, date: 21.12.2018, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class DbStoreStub extends DBStore {
    /**
     * Отображение.
     */
    private Map<Integer, User> users = new HashMap<>();
    /**
     * Счетчик.
     */
    private int ids;

    /**
     * @param user юзер, чьи данные нужно добавить в БД.
     */
    @Override
    public void add(User user) {
        user.setId(this.ids++);
        this.users.put(user.getId(), user);
    }

    /**
     * @param user юзер, чьи данные нужно обновить в БД.
     */
    @Override
    public void update(User user) {
        this.users.get(user.getId()).exchange(user);
    }

    /**
     * @param user юзер, чьи данные нужно удалить из БД.
     */
    @Override
    public void delete(User user) {
        this.users.remove(user.getId());
    }

    /**
     * @return список юзеров.
     */
    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.users.values());
    }

    /**
     * @param id номер юзера для нахождения в хранилище.
     * @return юзера.
     */
    @Override
    public User findById(int id) {
        return this.users.get(id);
    }

    /**
     * @return список ролей.
     */
    @Override
    public List<Role> findAllRoles() {
        final List<Role> roles = new ArrayList<>();
        for (User user : this.users.values()) {
            roles.add(user.getRole());
        }
        return roles;
    }

    /**
     * @param user юзер, чью роль нужно найти.
     * @return роль.
     */
    @Override
    public Role findRole(User user) {
        return user.getRole();
    }
}