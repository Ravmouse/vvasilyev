package ru.job4j.h2http;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс юзера.
 */
public class User {
    /**
     * Номер.
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
    private String role;
    /**
     * Версия.
     */
    private AtomicInteger version = new AtomicInteger(0);
    /**
     * Для подсчета и хранения кол-ва созданных юзеров.
     */
    private static final AtomicInteger COUNT = new AtomicInteger(0);

    /**
     * @param name имя.
     * @param login логин.
     * @param email электронная почта.
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
     * @param id номер.
     * @param name имя.
     * @param login логин.
     * @param email электронная почта.
     * @param createDate дата создания.
     * @param comments комментарии.
     */
    public User(int id, final String name, final String login, final String email, final String createDate, final String comments) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.comments = comments;
    }

    /**
     * @param id номер.
     * @param name имя.
     * @param login логин.
     * @param email электронная почта.
     * @param createDate дата создания.
     * @param comments комментарии.
     * @param password пароль.
     * @param role роль.
     */
    public User(int id, final String name, final String login, final String email, final String createDate,
                final String comments, final String password, final String role) {
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
     * @return номер юзера.
     */
    public int getId() {
        return id;
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
                              id, name, login, email, createDate, comments, password, role);
    }

    /**
     * @param list список строк со значениями юзера.
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
    public String getRole() {
        return role;
    }
}