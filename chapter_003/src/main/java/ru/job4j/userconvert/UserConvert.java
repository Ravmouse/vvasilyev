package ru.job4j.userconvert;
import java.util.HashMap;
import java.util.List;

/**
 * Class with conversion the List of Users into HashMap.
 * Класс конвертирования List'a c User'ами в HashMap.
 */
public class UserConvert {

    /**
     * Converts List of User elements into HashMap.
     * @param list of User elements.
     * @return HashMap that contains the key (User id) and the value (User itself).
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User user : list) {
            if (user != null) {
                result.put(user.getId(), user);
            }
        }
        return result;
    }
}