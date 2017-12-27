package ru.job4j.h5map.t1user;
import java.util.Calendar;
import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

/**
 * Класс User.
 */
public class User {
    /**
     * Имя User'а.
     */
    private String name;
    /**
     * Кол-во детей.
     */
    private int children;
    /**
     * Дата рождения.
     */
    private Calendar birthday;

    /**
     * @param name - имя.
     * @param children - кол-во детей.
     * @param year - год рождения.
     * @param month - месяц рождения.
     * @param date - день рождения.
     */
    public User(String name, int children, int year, int month, int date) {
        this.name = name;
        this.children = children;
        birthday = Calendar.getInstance();
        birthday.set(year, month, date);
    }

    /**
     * Показывает дату рождения.
     */
    public void showBirthday() {
        System.out.println(birthday.get(DATE) + "."
                         + birthday.get(MONTH) + "."
                         + birthday.get(YEAR));
    }
}