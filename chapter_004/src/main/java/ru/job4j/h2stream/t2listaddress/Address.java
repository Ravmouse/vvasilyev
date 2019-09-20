package ru.job4j.h2stream.t2listaddress;

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
}