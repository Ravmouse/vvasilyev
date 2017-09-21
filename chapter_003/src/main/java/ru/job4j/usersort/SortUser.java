package ru.job4j.usersort;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;

/**
 * Class SortUser.
 */
public class SortUser {
    /**
     * @param list which elements must be sorted bu age.
     * @return A sorted by age TreeSet object.
     */
    public Set<User> sort(List<User> list) {
        Set<User> result = new TreeSet<>();
        result.addAll(list);
        return result;
    }
}