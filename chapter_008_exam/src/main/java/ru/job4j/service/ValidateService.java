package ru.job4j.service;

import ru.job4j.model.Account;
import ru.job4j.model.DbStore;
import ru.job4j.model.Seat;
import ru.job4j.model.Store;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Vitaly Vasilyev, date: 19.01.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class ValidateService implements Validate {
    /**
     * Ссылка на экземпляр данного класса.
     */
    private static final ValidateService VALIDATE = new ValidateService();
    /**
     * Потокобезопасное хэш-отображение.
     */
    private final Map<Integer, Seat> seats = new ConcurrentHashMap<>();
    /**
     * Ссылка на экземпляр класса, реализующего интерфейс Store.
     */
    private final Store store = DbStore.getInstance();

    /**
     * Приватный конструктор.
     */
    private ValidateService() {

    }

    /**
     * @return ссылку.
     */
    public static ValidateService getInstance() {
        return VALIDATE;
    }

    /**
     * @param seatNumber номер места.
     * @param data       список данных зрителя.
     */
    @Override
    public void add(int seatNumber, final List<String> data) {
        final Seat seat = seats.get(seatNumber);
        final Account account = new Account(data.get(0), data.get(1), data.get(2), data.get(3), seat);
        store.add(account);
    }

    /**
     * @return список всех мест в к/т.
     */
    @Override
    public List<Seat> selectAll() {
        final List<Seat> rsl = store.selectAll();
        for (Seat seat : rsl) {
            seats.put(seat.getNumber(), seat);
        }
        return rsl;
    }
}