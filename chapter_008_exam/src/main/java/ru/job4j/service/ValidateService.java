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
     * @param account аккаунт зрителя.
     */
    @Override
    public void add(Account account) {
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

    /**
     * @param number номер места в зале.
     * @return ссылку на экз.класса Seat.
     */
    @Override
    public Seat checkSeat(int number) {
        return store.checkSeat(number);
    }

    /**
     * @return хэш-отображение.
     */
    @Override
    public Map<Integer, Seat> getSeats() {
        return seats;
    }
}