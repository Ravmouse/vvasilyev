package ru.job4j.service;

import ru.job4j.model.Account;
import ru.job4j.model.DbStore;
import ru.job4j.model.Seat;
import ru.job4j.model.Store;
import java.util.List;

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
    public int add(Account account) {
        return store.add(account);
    }

    /**
     * @return список всех мест в к/т.
     */
    @Override
    public List<Seat> selectAll() {
        return store.selectAll();
    }
}