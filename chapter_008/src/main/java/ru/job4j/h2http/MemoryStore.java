package ru.job4j.h2http;

import net.jcip.annotations.ThreadSafe;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * Синглтон. Класс для хранения данных.
 */
@ThreadSafe
public class MemoryStore implements Store { //Persistence
    /**
     * Статическое поле.
     */
    private static final MemoryStore MEMORY = new MemoryStore();
    /**
     * Потокобезопасное отображение.
     */
    final Map<Integer, User> users = new ConcurrentHashMap<>();

    /**
     * Приватный конструктор.
     */
    private MemoryStore() {
    }

    /**
     * @return возвращает ссылку на экземпляр этого класса.
     */
    public static MemoryStore getInstance() {
        return MEMORY;
    }


    /**
     * @param name имя.
     * @param login логин.
     * @param email эл.почта.
     * @param createDate дата создания.
     */
    @Override
    public void add(final String name, final String login, final String email, final String createDate) {
        final User user = new User(name, login, email, createDate);
        users.put(user.getId(), user);
    }

    /**
     * @param id id User'а, которого нужно найти.
     * @param user User, который нужно заменить.
     */
    @Override
    public void update(int id, final User user) {
        users.replace(id, user);
    }

    /**
     * @param id id User'а.
     * @param user User, которого нужно удалить.
     */
    @Override
    public void delete(int id, final User user) {
        users.remove(id, user);
    }

    /**
     * @return список User'ов.
     */
    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        for (Map.Entry<Integer, User> u : users.entrySet()) {
            result.add(u.getValue());
        }
        return result;
    }

    /**
     * @param id номер User'а.
     * @return User'а по его номеру.
     */
    @Override
    public User findById(int id) {
        return users.get(id);
    }
}