package ru.job4j.usersort;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class SortUser.
 */
public class SortUser {
    /**
     * @param list which elements must be sorted by age.
     * @return A sorted by age TreeSet object.
     */
    public Set<User> sort(List<User> list) {
        Set<User> result = new TreeSet<>();
        result.addAll(list);
        return result;
    }

    /**
     * @param list with elements that are sorted by their name's length.
     * @return A sorted by name's length List of Users.
     */
    public List<User> sortNameLength(List<User> list) {
        Comparator<User> comp = new Comparator<User>() {
            @Override
            public int compare(User userOne, User userTwo) {
                return Integer.compare(userOne.getName().length(), userTwo.getName().length());
            }
        };
        Collections.sort(list, comp);
        return list;
    }

    /**
     * @param list with elements that are sorted by name's length and age.
     * @return A sorted by all fields List of Users.
     */
    public List<User> sortByAllFields(List<User> list) {
        Comparator<User> comp = new Comparator<User>() {
            public int compare(User left, User right) {
                final int len = Integer.compare(left.getName().length(), right.getName().length());
                return len != 0 ? len : Integer.compare(left.getAge(), right.getAge());
            }
        };
        Collections.sort(list, comp);
        return list;
    }
}