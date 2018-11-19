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
    private final Map<Integer, User> users = new ConcurrentHashMap<>();

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


    @Override
    public void add(final List<String> liste) {
//        final User user = new User(name, login, email, createDate);
//        users.put(user.getId(), user);
    }

    /**
     * @param id id User'а, которого нужно найти.
     */
    @Override
    public void update(int id, final List<String> list) {
//        users.replace(id, user);
    }

    /**
     * @param id id User'а.
     */
    @Override
    public void delete(int id) {
//        users.remove(id, user);
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