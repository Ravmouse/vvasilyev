package ru.job4j.h2stream.t2listaddress;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vitaly Vasilyev, date: 20.09.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class ProfilesTest {
    private final Address addrOne = new Address("Moscow", "Main street", 10, 1);
    private final Address addrTwo = new Address("Saint Petersburg", "Main prospect", 20, 14);
    private final Address addrThree = new Address("Samara", "Main boulevard", 116, 3);
    private final Address addrFour = new Address("Springfield", "Evergreen Terrace", 742, 1);

    /**
     * Тест на получение списка адресов из списка анкет клиентов.
     */
    @Test
    public void collectTest() {
        List<Profile> profiles = Arrays.asList(
                new Profile(addrOne),
                new Profile(addrTwo),
                new Profile(addrThree),
                new Profile(addrFour));
        List<Address> addresses = Profiles.collect(profiles);
        List<Address> expected = Arrays.asList(addrOne, addrTwo, addrThree, addrFour);
        assertThat(addresses, is(expected));
    }
}