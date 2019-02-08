package ru.job4j.model;

import java.util.Objects;

/**
 * @author Vitaly Vasilyev, date: 17.01.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Account {
    /**
     * Имя.
     */
    private String name;
    /**
     * Фамилия.
     */
    private String surname;
    /**
     * Отчество.
     */
    private String patron;
    /**
     * Номер телефона.
     */
    private String mobile;
    /**
     * Место в зрительном зале.
     */
    private int seat;

    /**
     *
     */
    public Account() {
    }

    /**
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name имя.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return фамилию.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname фамилия.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return отчество.
     */
    public String getPatron() {
        return patron;
    }

    /**
     * @param patron отчество.
     */
    public void setPatron(String patron) {
        this.patron = patron;
    }

    /**
     * @return номер телефона.
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile номер телефона.
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return место.
     */
    public int getSeat() {
        return seat;
    }

    /**
     * @param seat место.
     */
    public void setSeat(int seat) {
        this.seat = seat;
    }

    /**
     * @return строковое представление.
     */
    @Override
    public String toString() {
        return String.format("name=%s, surname=%s, patron=%s, mobile=%s, seat=%d", name, surname, patron, mobile, seat);
    }

    /**
     * @return хэш-код.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, surname, patron, mobile, seat);
    }

    /**
     * @param obj экз. класса для сравнения.
     * @return true или false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Account)) {
            return false;
        }
        Account acc = (Account) obj;
        return name.equals(acc.name) && surname.equals(acc.surname) && patron.equals(acc.patron)
                & mobile.equals(acc.mobile) && seat == acc.seat;
    }
}