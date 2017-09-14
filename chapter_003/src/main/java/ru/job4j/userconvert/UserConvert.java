package ru.job4j.userconvert;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;

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

    /**
     * main.
     * @param args String[].
     */
    public static void main(String[] args) {
        ArrayList<User> aList = new ArrayList<>();
        aList.add(new User("Ivan", "Moscow"));
        aList.add(new User("Lena", "London"));
        aList.add(new User("Masha", "Berlin"));
        aList.add(new User("Dima", "Paris"));
        UserConvert userConvert = new UserConvert();
        Map<Integer, User> hash = userConvert.process(aList);
        Set<Map.Entry<Integer, User>> mapE = hash.entrySet();

        for (Map.Entry<Integer, User> tmp : mapE) {
            System.out.print(tmp.getKey() + ": ");
            System.out.println(tmp.getValue());
        }
    }
}