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

    /**
     * Checks if the User elements are sorted only by name.
     */
    @Test
    public void whenAddUsersWithDiffNamesThenSortThemByLengthName() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("ABCD", 8));
        userList.add(new User("ABC", 3));
        userList.add(new User("AB", 6));
        userList.add(new User("A", 10));
        SortUser s = new SortUser();
        s.sortNameLength(userList);

        String[] sa = new String[userList.size()];
        for (int i = 0; i < sa.length; i++) {
            sa[i] = userList.get(i).getName();
        }
        assertThat(sa, is(new String[] {"A", "AB", "ABC", "ABCD"}));
    }

    /**
     * Checks if the User elements are sorted by name and after that by age.
     */
    @Test
    public void whenAddUsersWithDiffNamesAndAgesThenSortThemByAllFields() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("Сергей", 25));
        userList.add(new User("Иван", 30));
        userList.add(new User("Сергей", 20));
        userList.add(new User("Иван", 25));
        SortUser s = new SortUser();
        s.sortByAllFields(userList);

        String[] stringArray = new String[userList.size()];
        for (int i = 0; i < stringArray.length; i++) {
            stringArray[i] = userList.get(i).getName();
        }
        assertThat(stringArray, is(new String[] {"Иван", "Иван", "Сергей", "Сергей"}));

        int[] intArray = new int[userList.size()];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = userList.get(i).getAge();
        }
        assertThat(intArray, is(new int[] {25, 30, 20, 25}));
    }
}