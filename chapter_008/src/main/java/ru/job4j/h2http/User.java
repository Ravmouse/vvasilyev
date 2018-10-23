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
    public void setFields(List<String> list) {
        this.name = list.get(1);
        this.login = list.get(2);
        this.email= list.get(3);
        this.createDate= list.get(4);
    }
}