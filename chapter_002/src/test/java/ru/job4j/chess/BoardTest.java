package ru.job4j.chess;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class BoardTest.
 */
public class BoardTest {
    /**
     * Тест метод.
     */
    @Test
    public void whenThereIsAWayThenReturnThisWay() {
        Board board = new Board();
        board.generate();
        board.move(new Cell(3, 1), new Cell(6, 4));
        assertThat(new int[] {board.getFigures()[0].getPosition().getX(),
                              board.getFigures()[0].getPosition().getY()}, is(new int[] {6, 4}));
    }
}