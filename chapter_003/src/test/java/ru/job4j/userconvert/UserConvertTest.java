package ru.job4j.userconvert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * UserConvertTest Class.
 */
public class UserConvertTest {
    /**
     * Checks whether the added in ArrayList Users and their id are contained in the HashMap.
     */
    @Test
    public void whenAddUsersInListThenUsersAndUserIdAreContainedInHashMapAsValuesAndKeys() {
        ArrayList<User> aList = new ArrayList<>();
        User one = new User("Vasya", "Moscow");
        User two = new User("Dima", "Saint Petersburg");
        User three = new User("Ivan", "Volgograd");
        User four = new User("Kolya", "Sochi");
        aList.add(one);
        aList.add(two);
        aList.add(three);
        aList.add(four);

        UserConvert userConvert = new UserConvert();
        Map<Integer, User> hash = userConvert.process(aList);
        Set<Map.Entry<Integer, User>> mapE = hash.entrySet();

        ArrayList<User> userList = new ArrayList<>();
        for (Map.Entry<Integer, User> me : mapE) {
            userList.add(me.getValue());
        }

        ArrayList<Integer> intList = new ArrayList<>();
        for (Map.Entry<Integer, User> me : mapE) {
            intList.add(me.getKey());
        }

        assertThat(userList, containsInAnyOrder(one, two, three, four));
        assertThat(intList, containsInAnyOrder(one.getId(), two.getId(), three.getId(), four.getId()));
    }
}