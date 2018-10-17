package ru.job4j.h2http;

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
     * @param id id.
     * @param name имя.
     * @param login логин.
     * @param email эл.почта.
     * @param createDate дата создания.
     */
    public User(int id, String name, String login, String email, String createDate) {
        this.id = id;
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
     * @param name имя User'а.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param login логин User'а.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @param email эл.почта User'а.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param createDate дата создания User'а.
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * @return строковое представление.
     */
    @Override
    public String toString() {
        return String.format("id = %d, name = %s, login = %s, e-mail = %s, date = %s\n",
                              id, name, login, email, createDate);
    }
}