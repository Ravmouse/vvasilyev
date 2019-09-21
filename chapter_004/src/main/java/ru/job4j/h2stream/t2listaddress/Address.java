package ru.job4j.h2stream.t2listaddress;

import java.util.Objects;

/**
 * @author Vitaly Vasilyev, date: 20.09.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Address {
    /**
     * Город.
     */
    private String city;
    /**
     * Улица.
     */
    private String street;
    /**
     * Номер дома.
     */
    private int home;
    /**
     * Номер квартиры.
     */
    private int apartment;

    /**
     * @param city город.
     * @param street улица.
     * @param home номер дома.
     * @param apartment номер квартиры.
     */
    public Address(String city, String street, int home, int apartment) {
        this.city = city;
        this.street = street;
        this.home = home;
        this.apartment = apartment;
    }

    /**
     * @return город.
     */
    public String getCity() {
        return city;
    }

    /**
     * @param o объект для сравнения.
     * @return true, если 2 объекта равны, и false, если - нет.
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || !(o instanceof Address)) {
            return false;
        }
        final Address addr = (Address) o;
        return city.equals(addr.city) && street.equals(addr.street) && home == addr.home
                && apartment == addr.apartment;
    }

    /**
     * @return хэш-код объекта.
     */
    public int hashCode() {
        return Objects.hash(city, street, home, apartment);
    }

    /**
     * @return строковое представление.
     */
    @Override
    public String toString() {
        return String.format("City = %s, street = %s, home = %d, apartment = %d", city, street, home, apartment);
    }
}