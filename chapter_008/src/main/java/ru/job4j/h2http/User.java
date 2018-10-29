package ru.job4j.h2http;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс пользователя.
 */
public class User {
    /**
     * id.
     */
    private final int id;
    /**
     * Имя.
     */
    private String name;
    /**
     * Логин.
     */
    private String login;
    /**
     * Эл.почта.
     */
    private String email;
    /**
     * Дата создания.
     */
    private String createDate;
    /**
     * Версия.
     */
    private AtomicInteger version = new AtomicInteger(0);
    /**
     * Для подсчета и хранения кол-ва созданных пользователей.
     */
    private static final AtomicInteger COUNT = new AtomicInteger(0);

    /**
     * @param name имя.
     * @param login логин.
     * @param email эл.почта.
     * @param createDate дата создания.
     */
    public User(String name, String login, String email, String createDate) {
        this.id = COUNT.incrementAndGet();
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    /**
     * @return id User'а.
     */
    public int getId() {
        return id;
    }

    /**
     * @return версию User'а.
     */
    public AtomicInteger getVersion() {
        return version;
    }

    /**
     * @return строковое представление.
     */
    @Override
    public String toString() {
        return String.format("id = %d, name = %s, login = %s, e-mail = %s, date = %s\n",
                              id, name, login, email, createDate);
    }

    /**
     * @param list список строк со значениями User'а.
     */
    public void changeFields(List<String> list) {
        this.name = list.get(0);
        this.login = list.get(1);
        this.email = list.get(2);
        this.createDate = list.get(3);
    }

    /**
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * @return логин.
     */
    public String getLogin() {
        return login;
    }

    /**
     * @return эл.почта.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return дата создания.
     */
    public String getCreateDate() {
        return createDate;
    }
}