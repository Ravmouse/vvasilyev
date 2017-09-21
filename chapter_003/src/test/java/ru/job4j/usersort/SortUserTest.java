package ru.job4j.usersort;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class SortUserTest.
 */
public class SortUserTest {
    /**
     * Checks if the User elements are sorted by age field when sort() method is invoked.
     */
    @Test
    public void whenAddUserObjectsToSetThenSortThemByAge() {
        List<User> list = new ArrayList<>();
        list.add(new User("Viktor", 56));
        list.add(new User("Alex", 42));
        list.add(new User("Dan", 34));
        list.add(new User("Pasha", 15));
        SortUser sUser = new SortUser();
        Set<User> set = sUser.sort(list);

        int i = 0;
        int[] expectedArray = new int[list.size()];
        for (User user : list) {
            expectedArray[i++] = user.getAge();
        }
        Arrays.sort(expectedArray);

        i = 0;
        int[] resultArray = new int[set.size()];
        for (User user : set) {
            resultArray[i++] = user.getAge();
        }
        assertThat(resultArray, is(expectedArray));
    }
}