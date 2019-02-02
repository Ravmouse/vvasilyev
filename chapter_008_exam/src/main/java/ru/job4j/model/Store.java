package ru.job4j.model;

import java.util.List;

/**
 * @author Rav, date: 17.01.2019
 * @version 1.0
 */
public interface Store {
    /**
     * @param account зритель в к/т.
     */
    void add(final Account account);
    /**
     * @return список всех мест в к/т.
     */
    List<Seat> selectAll();
    /**
     * @param number номер места в к/т.
     * @return экз. класса Seat.
     */
    Seat checkSeat(int number);
}