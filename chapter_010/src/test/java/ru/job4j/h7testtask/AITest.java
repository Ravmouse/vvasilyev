package ru.job4j.h7testtask;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Rav, date: 17.07.2019
 * @version 1.0
 */
public class AITest {
    /**
     * .
     */
    @Test
    public void whenThereIsBoardThenConstructList() {
        Board board = new Board(3);
        AI ai = new AI(board);

        List<Integer> one = Arrays.asList(0, 1, 2);
        List<Integer> two = Arrays.asList(3, 4, 5);
        List<Integer> three = Arrays.asList(6, 7, 8);
        List<Integer> four = Arrays.asList(0, 3, 6);
        List<Integer> five = Arrays.asList(1, 4, 7);
        List<Integer> six = Arrays.asList(2, 5, 8);
        List<Integer> seven = Arrays.asList(0, 4, 8);
        List<Integer> eight = Arrays.asList(2, 4, 6);

        assertThat(ai.getWinners(), is(Arrays.asList(one, two, three, four, five, six, seven, eight)));
    }
}