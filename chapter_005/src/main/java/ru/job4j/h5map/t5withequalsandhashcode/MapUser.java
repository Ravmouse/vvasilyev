package ru.job4j.h5map.t5withequalsandhashcode;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Класс MapUser.
 */
public class MapUser {
    /**
     * Первый User.
     */
    private static User user1 = new User("Vit", 1, 1984, 2, 17);
    /**
     * Второй User.
     */
    private static User user2 = new User("Vit", 1, 1984, 2, 17);

    /**
     * @param args - массив строк.
     */
    public static void main(String[] args) {
        Map<User, Object> usMap = new HashMap<>();
        Object ob = new Object();
        usMap.put(user1, ob);
        usMap.put(user2, ob);

        Set<Map.Entry<User, Object>> s = usMap.entrySet();
        for (Map.Entry<User, Object> me : s) {
            System.out.println("[" + me.getKey() + "], [" + me.getValue() + "]");
        }
    }
}