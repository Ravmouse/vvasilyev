package ru.job4j.h2stream.t2listaddress;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vitaly Vasilyev, date: 20.09.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Profiles {
    /**
     * @param profiles список с анкетами.
     * @return список с адресами клиентов.
     */
    public static List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(Profile::getAddress).collect(Collectors.toList());
    }
}