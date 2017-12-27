package ru.job4j.h5map.t2noequalsandhashcode;
import ru.job4j.h5map.t1user.User;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * Класс MapTest.
 */
public class MapTest {
    /**
     * Первый User.
     */
    private static User u1 = new User("Ivan", 2, 1980, 6, 15);
    /**
     * Второй User.
     */
    private static User u2 = new User("Ivan", 2, 1980, 6, 15);

    /**
     * @param args - массив строк.
     */
    public static void main(String[] args) {
        Map<User, Object> userMap = new HashMap<>();
        userMap.put(u1, new Object());
        userMap.put(u2, new Object());

        Set<Map.Entry<User, Object>> set = userMap.entrySet();
        for (Map.Entry<User, Object> me : set) {
            System.out.println("[" + me.getKey() + "], [" + me.getValue() + "]");
        }
    }
}