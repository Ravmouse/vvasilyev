package ru.job4j.h3monitorsynchronized.t2userstorage;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс, который реализует интерфейс UserStorage.
 */
@ThreadSafe
public class UserStorageImpl implements UserStorage {
    /**
     * Лист User'ов.
     */
    @GuardedBy("this")
    private final List<User> list;

    /**
     * Конструктор.
     */
    public UserStorageImpl() {
        list = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * @param user - элемент для добавления.
     * @return true, если элемент удалось добавить, и false, если - нет.
     */
    @Override
    public boolean add(User user) {
        boolean rsl = false;
        if (user != null) {
            list.add(user);
            Collections.sort(list);
            rsl = true;
        }
        return rsl;
    }

    /**
     * @param user - элемент для изменения.
     * @param amount - новая сумма для элемента user.
     * @return true, если такой элемент существует, и его удалось изменить, и false, если - нет.
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
     * @param user - элемент для удаления.
     * @return true, если такой элемент существует, и его удалось удалить, и false, если - нет.
     */
    @Override
    public synchronized boolean delete(User user) {
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
     * @param fromId - id пользователя, с которого нужно перевести сумму.
     * @param toId - id пользователя, на которого нужно перевести сумму.
     * @param amount - сумма для перевода.
     * @return true, если удалось осуществить перевод и false, если - нет.
     * @throws InterruptedException в случае возникновения исключения.
     */
    @Override
    public synchronized boolean transfer(int fromId, int toId, int amount) throws InterruptedException {
        User fromUser, toUser;
        boolean rsl = false;
        fromUser = bSearch(fromId);
        toUser = bSearch(toId);
        if ((fromUser != null) && (toUser != null)) {
            while (fromUser.getAmount() < amount) {
                wait();
            }
            fromUser.setAmount(fromUser.getAmount() - amount);
            toUser.setAmount(toUser.getAmount() + amount);
            rsl = true;
            notifyAll();
        }
        return rsl;
    }

    /**
     * @param id элемента, которого нужно найти.
     * @return ссылку на найденный элемент или null.
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
     * @return ссылку на текущий лист.
     */
    public List<User> getList() {
        return list;
    }
}