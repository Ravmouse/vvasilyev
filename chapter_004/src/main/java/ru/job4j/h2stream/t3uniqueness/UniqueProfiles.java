package ru.job4j.h2stream.t3uniqueness;

import ru.job4j.h2stream.t2listaddress.Address;
import ru.job4j.h2stream.t2listaddress.Profile;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vitaly Vasilyev, date: 21.09.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class UniqueProfiles {
    /**
     * @param profiles список с анектами.
     * @return упорядоченный по названию города список с адресами, в котором нет повторяющихся элементов.
     */
    public static List<Address> collectSorted(List<Profile> profiles) {
        return profiles
                .stream()
                .map(Profile::getAddress)
                .distinct()
                .sorted(Comparator.comparing(Address::getCity))
                .collect(Collectors.toList());
    }
}