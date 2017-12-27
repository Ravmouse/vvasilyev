package ru.job4j.h5map.t3onlyhashcode;
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

    /**
     * @return хеш код для экземпляра этого класса.
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + children;
        result = 31 * result + birthday.get(DATE);
        result = 31 * result + birthday.get(MONTH);
        result = 31 * result + birthday.get(YEAR);
        return result;
//        return Objects.hash(name, children, birthday.get(DATE), birthday.get(MONTH),
//                birthday.get(YEAR));
    }
}