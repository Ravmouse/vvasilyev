package ru.job4j.h2stream.t3uniqueness;

import org.junit.Test;
import ru.job4j.h2stream.t2listaddress.Address;
import ru.job4j.h2stream.t2listaddress.Profile;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vitaly Vasilyev, date: 21.09.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class UniqueProfilesTest {
    private final Address one = new Address("Moscow", "Main street", 10, 1);
    private final Address two = new Address("Saint Petersburg", "Main prospect", 20, 14);
    private final Address three = new Address("Samara", "Main boulevard", 116, 3);
    private final Address four = new Address("Springfield", "Evergreen Terrace", 742, 1);
    private final Address five = new Address("Moscow", "Main street", 10, 1);
    private final Address six = new Address("Perm", "Street", 58, 4);
    private final Address seven = new Address("Springfield", "Evergreen Terrace", 742, 1);
    private final Address eight = new Address("Moscow", "Main street", 10, 1);
    private final Address nine = new Address("Vladimir", "Highway", 89, 19);

    /**
     * Тест на получение списка адресов из списка анкет клиентов.
     */
    @Test
    public void collectSortedTest() {
        List<Profile> profiles = Arrays.asList(
                new Profile(one),
                new Profile(two),
                new Profile(three),
                new Profile(four),
                new Profile(five),
                new Profile(six),
                new Profile(seven),
                new Profile(eight),
                new Profile(nine));
        List<Address> addresses = UniqueProfiles.collectSorted(profiles);
        List<Address> expected = Arrays.asList(one, six, two, three, four, nine);
        assertThat(addresses, is(expected));
    }
}