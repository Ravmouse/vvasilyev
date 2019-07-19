package ru.job4j.h7testtask;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Rav, date: 17.07.2019
 * @version 1.0
 */
public class AITest {
    /**
     * Тест метода findWinner() для диагонали.
     */
    @Test
    public void whenBoardHasLineOfTheSameSymbolsThenItIsWinner() {
        String x = "x";
        String o = "o";
        Board board = new Board(3);
        board.changeCell(0, o);
        board.changeCell(4, o);
        board.changeCell(8, o);
        AI ai = new AI(x, o, board);
        String s = ai.findWinner();
        assertThat(s, is("c"));
    }
}