package ru.job4j.h2http;

import java.util.concurrent.atomic.AtomicInteger;
import ru.job4j.h6filter.Role;

/**
 * Класс юзера.
 */
public class User {
    /**
     * Номер.
     */
    private int id;
    /**
     * Имя.
     */
    private String name;
    /**
     * Логин.
     */
    private String login;
    /**
     * Электронная почта.
     */
    private String email;
    /**
     * Дата создания.
     */
    private String createDate;
    /**
     * Комментарии.
     */
    private String comments;
    /**
     * Пароль.
     */
    private String password;
    /**
     * Роль.
     */
    private Role role;
    /**
     * Версия.
     */
    private AtomicInteger version = new AtomicInteger(0);

    /**
     * Конструктор для метода add() в классе ValidateService, где id юзера не нужен, т.к. id - SERIAL PRIMARY KEY.
     * @param name имя.
     * @param login логин.
     * @param email электронная почта.
     * @param comments комментарии.
     * @param password пароль.
     * @param role роль.
     */
    public User(String name, String login, String email, String comments, String password, Role role) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.comments = comments;
        this.password = password;
        this.role = role;
    }

    /**
     * Конструктор для метода update() в классе ValidateService, где нужен id юзера, чтобы его найти в БД.
     * @param id номер.
     * @param name имя.
     * @param login логин.
     * @param email электронная почта.
     * @param comments комментарии.
     * @param password пароль.
     * @param role роль.
     */
    public User(int id, String name, String login, String email, String comments, String password, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.comments = comments;
        this.password = password;
        this.role = role;
    }

    /**
     * Конструктор для класса DBStore при получении всех юзеров из БД.
     * @param id номер.
     * @param name имя.
     * @param login логин.
     * @param email электронная почта.
     * @param createDate дата создания.
     * @param comments комментарии.
     * @param password пароль.
     * @param role роль.
     */
    public User(int id, String name, String login, String email, String createDate, String comments, String password, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.comments = comments;
        this.password = password;
        this.role = role;
    }

    /**
     * Конструктор для метода delete() в классе ValidateService, где другие параметры кроме id не нужны.
     * @param id номер.
     */
    public User(int id) {
        this.id = id;
    }

    /**
     * Конструктор для метода findRole() в классе ValidateService, где нужны только login и password юзера.
     * @param login логин.
     * @param password пароль.
     */
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * @return номер юзера.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id номер.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return версию юзера.
     */
    public AtomicInteger getVersion() {
        return version;
    }

    /**
     * @return строковое представление объекта.
     */
    @Override
    public String toString() {
        return String.format("ID = %d, Name = %s, Login = %s, E-mail = %s, Create date = %s, Comments = %s, Password = %s, Role = %s",
                              id, name, login, email, createDate, comments, password, role.getName());
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
     * @return электронная почта.
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

    /**
     * @return комментарии.
     */
    public String getComments() {
        return comments;
    }

    /**
     * @return пароль.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return роль.
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param user юзер с данными для присвоения.
     */
    public void exchange(final User user) {
        this.name = user.name;
        this.login = user.login;
        this.email = user.email;
        this.comments = user.comments;
        this.password = user.password;
        this.role = user.role;
    }
}