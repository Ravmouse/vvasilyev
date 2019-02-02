package ru.job4j.service;

import ru.job4j.model.Account;
import ru.job4j.model.Seat;
import java.util.List;
import java.util.Map;

/**
 * @author Rav, date: 19.01.2019
 * @version 1.0
 */
public interface Validate {
    /**
     * @param account аккаунт зрителя.
     */
    void add(Account account);
    /**
     * @return список всех мест в к/т.
     */
    List<Seat> selectAll();
    /**
     * @param number номер места в зале.
     * @return ссылку на экз.класса Seat.
     */
    Seat checkSeat(int number);
    /**
     * @return хэш-отображение.
     */
    Map<Integer, Seat> getSeats();
}