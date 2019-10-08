package ru.job4j.h2stream.t6banktostream;

import java.util.Objects;

/**
 * @author Vitaly Vasilyev, date: 07.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class User {
    /**
     * Имя.
     */
    private String name;
    /**
     * Паспорт.
     */
    private String passport;

    /**
     * @param name имя.
     * @param passport паспорт.
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * @param o объект для сравнения.
     * @return true, если два объекта равны.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if ((o == null) || !(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return this.name.equals(user.name) && this.passport.equals(user.passport);
    }

    /**
     * @return паспорт.
     */
    public String getPassport() {
        return passport;
    }

    /**
     * @return хэш-код объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, passport);
    }

    /**
     * @return строковое представление.
     */
    @Override
    public String toString() {
        return String.format("Name: %s, passport: %s", name, passport);
    }
}