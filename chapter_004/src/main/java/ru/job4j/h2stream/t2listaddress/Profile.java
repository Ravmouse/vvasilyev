package ru.job4j.h2stream.t2listaddress;

/**
 * @author Vitaly Vasilyev, date: 20.09.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Profile {
    /**
     * Адрес.
     */
    private Address address;

    /**
     * @param address адрес.
     */
    public Profile(Address address) {
        this.address = address;
    }

    /**
     * @return адрес клиента.
     */
    public Address getAddress() {
        return address;
    }
}