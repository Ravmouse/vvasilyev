package ru.job4j.h3monitorsynchronized.t2userstorage;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class-container with a list of Users.
 */
@ThreadSafe
public class UserStorageImpl implements UserStorage {
    /**
     * The list of Users.
     */
    @GuardedBy("this")
    private final List<User> list;

    /**
     * The constructor.
     */
    public UserStorageImpl() {
        list = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * @param user to be added.
     * @return true if the user is added successfully and false otherwise.
     */
    @Override
    public boolean add(User user) {
        boolean rsl = false;
        if (user != null) {
            list.add(user);
//            Collections.sort(list); //Заккоментировал из-за двух циклов for в двух разных потоках в классе
            rsl = true;               //UserStorageImplTest.
        }
        return rsl;
    }

    /**
     * @param user to be updated.
     * @param amount to be changed.
     * @return true if the user is updated successfully and false otherwise.
     */
    @Override
    public boolean update(User user, int amount) {
        boolean rsl = false;
        if (user != null) {
            int pos = Collections.binarySearch(list, user);
            if (pos >= 0) {
                list.get(pos).setAmount(amount);
                rsl = true;
            }
        }
        return rsl;
    }

    /**
     * @param user to be deleted.
     * @return true if the user is deleted successfully and false otherwise.
     */
    @Override
    public boolean delete(User user) {
        boolean rsl = false;
        if (user != null) {
            int pos = Collections.binarySearch(list, user);
            if (pos >= 0) {
                list.remove(pos);
                rsl = true;
            }
        }
        return rsl;
    }

    /**
     * @param fromId is the ID of the first user.
     * @param toId is the ID of the second user.
     * @param amount to be replaced from the first user to the second one.
     * @return true if the transfer is complete successfully.
     */
    @Override
    public boolean transfer(int fromId, int toId, int amount) {
        User fromUser, toUser;
        boolean rsl = false;
        fromUser = bSearch(fromId);
        toUser = bSearch(toId);
        if ((fromUser != null) && (toUser != null)) {
            fromUser.setAmount(fromUser.getAmount() - amount);
            toUser.setAmount(toUser.getAmount() + amount);
            rsl = true;
        }
        return rsl;
    }

    /**
     * @param id to be found in the data structure.
     * @return the user if the id is found or the null.
     */
    private User bSearch(int id) {
        int low, up, cur;
        low = 0;
        up = list.size() - 1;
        while (true) {
            cur = (low + up) >>> 1;
            final User u = list.get(cur);
            if (u.getId() == id) {
                return u;
            }  else if (low > up) {
                return null;
            } else {
                if (u.getId() < id) {
                    low = cur + 1;
                } else {
                    up = cur - 1;
                }
            }
        }
    }

    /**
     * @return the ref. to the list.
     */
    public List<User> getList() {
        return list;
    }
}