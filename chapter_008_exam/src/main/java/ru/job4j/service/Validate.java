package ru.job4j.service;

import ru.job4j.model.Account;
import ru.job4j.model.Seat;
import java.util.List;

/**
 * @author Rav, date: 19.01.2019
 * @version 1.0
 */
public interface Validate {
    /**
     * @param account аккаунт зрителя.
     * @return  код успешного завершения метода или нет.
     */
    int add(Account account);
    /**
     * @return список всех мест в к/т.
     */
    List<Seat> selectAll();
}