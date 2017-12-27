package ru.job4j.h5map.t4onlyequals;
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
     * @param o - объект, который сравнивается с текущим экземпляром класса.
     * @return true, если объекты логически эквивалентны, и false, если - нет.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        if (children != user.children) {
            return false;
        }
        if ((birthday.get(DATE) != user.birthday.get(DATE)
         || (birthday.get(MONTH) != user.birthday.get(MONTH))
         || (birthday.get(YEAR) != user.birthday.get(YEAR)))) {
            return false;
        }
        return name != null ? name.equals(user.name) : user.name == null;
    }
}