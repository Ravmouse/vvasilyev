package ru.job4j.service;

import ru.job4j.model.Seat;
import java.util.List;

/**
 * @author Rav, date: 19.01.2019
 * @version 1.0
 */
public interface Validate {
    /**
     * @param seatNumber номер места.
     * @param data список данных зрителя.
     */
    void add(int seatNumber, final List<String> data);

    /**
     * @return список всех мест в к/т.
     */
    List<Seat> selectAll();
}