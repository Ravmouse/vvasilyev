package ru.job4j.h5map.t3onlyhashcode;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Класс MapTest.
 */
public class MapUser {
    /**
     * Первый User.
     */
    private static User us1 = new User("Vit", 1, 1984, 2, 17);
    /**
     * Второй User.
     */
    private static User us2 = new User("Vit", 1, 1984, 2, 17);

    /**
     * @param args - массив строк.
     */
    public static void main(String[] args) {
        Map<User, Object> usMap = new HashMap<>();
        Object ob = new Object();
        usMap.put(us1, ob);
        usMap.put(us2, ob);

        Set<Map.Entry<User, Object>> set = usMap.entrySet();
        for (Map.Entry<User, Object> me : set) {
            System.out.println("[" + me.getKey() + "], [" + me.getValue() + "]");
        }
    }
}