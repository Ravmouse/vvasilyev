package ru.job4j.robot;

import org.apache.log4j.Logger;
import ru.job4j.utils.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * @author Vitaly Vasilyev, date: 14.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Robot {
    /**
     * Список ходов для левого края.
     */
    private List<Move> left = Arrays.asList(Move.UP, Move.RIGHT, Move.DOWN);
    /**
     * Список ходов для правого края.
     */
    private List<Move> right = Arrays.asList(Move.UP, Move.DOWN, Move.LEFT);
    /**
     * Список ходов для верхнего края.
     */
    private List<Move> up = Arrays.asList(Move.LEFT, Move.RIGHT, Move.DOWN);
    /**
     * Список ходов для нижнего края.
     */
    private List<Move> down = Arrays.asList(Move.LEFT, Move.RIGHT, Move.UP);
    /**
     * Список ходов для середины доски.
     */
    private List<Move> all = Arrays.asList(Move.LEFT, Move.RIGHT, Move.UP, Move.DOWN);
    /**
     * Карта с ходами в виде ключей и объктами типа Step в виде значений.
     */
    private Map<Move, Step> map = new HashMap<>();
    /**
     * Логгер.
     */
    private static final Logger LOG = Logger.getLogger(Utils.getNameOfTheClass());

    /**
     * Конструктор.
     */
    public Robot() {
        map.put(Move.UP, new Step(0, -1));
        map.put(Move.DOWN, new Step(0, 1));
        map.put(Move.RIGHT, new Step(1, 0));
        map.put(Move.LEFT, new Step(-1, 0));
    }

    /**
     * @param board доска целых чисел с нолями и едининцами.
     * @param sx начало по оси X.
     * @param sy начало по оси Y.
     * @param fx конец по оси X.
     * @param fy конец по оси Y.
     * @return кратчайший путь.
     */
    public int minWay(int[][] board, int sx, int sy, int fx, int fy) {
        final Cell[][] cells = toCellArray(board);
        final Queue<Cell> queue = new LinkedList<>();
        int result = 0;
        final Cell start = cells[sy][sx];

        start.setVisited(true);
        queue.offer(start);
        LOG.info(start);
        while (!queue.isEmpty()) {
            final Cell c = queue.poll();
            final List<Cell> unCells = getAdjUnvisitedCells(c, cells);
            for (Cell cell : unCells) {
                cell.setVisited(true);
                queue.offer(cell);
                cell.setCount(c.getCount() + 1);
                if (isEndCell(cell, fx, fy)) {
                    return cell.getCount();
                }
            }
        }
        return result;
    }

    /**
     * @param c клетка.
     * @param cells список клеток.
     * @return список клеток, смежных с проверяемой клеткой, имеющих значение 1 и еще непосещенных.
     */
    private List<Cell> getAdjUnvisitedCells(final Cell c, final Cell[][] cells) {
        final List<Step> steps = getSteps(c, cells);
        return steps
                .stream()
                .filter(step -> {
            int x = c.getX() + step.getX();
            int y = c.getY() + step.getY();
            return cells[y][x].getValue() == 1 && !cells[y][x].isVisited();
        })
                .peek(Utils::arrow)
                .map(step -> cells[c.getY() + step.getY()][c.getX() + step.getX()])
                .peek(LOG::info)
                .collect(Collectors.toList());
    }

    /**
     * @param board двумерный массив целых чисел.
     * @return двумерный массив объектов типа Cell.
     */
    private static Cell[][] toCellArray(final int[][] board) {
        final Cell[][] cells = new Cell[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                cells[i][j] = new Cell(j, i, board[i][j]);
            }
        }
        return cells;
    }

    /**
     * @param cell клетка, для которой получается список допустимых ходов.
     * @param cells массив клеток.
     * @return список допустимых ходов.
     */
    private List<Step> getSteps(final Cell cell, final Cell[][] cells) {
        int x = cell.getX();
        int y = cell.getY();

        int edgeY = cells.length - 1;
        int edgeX = cells[0].length - 1;

        List<Move> list;
        final List<Step> steps = new ArrayList<>();

        if (y == 0 && x == 0) {
            list = intersect(up, left);
        } else if (y == 0 && x == edgeX) {
            list = intersect(up, right);
        } else if (y == edgeY && x == 0) {
            list = intersect(down, left);
        } else if (y == edgeY && x == edgeX) {
            list = intersect(down, right);
        } else if (y == 0) {
            list = new ArrayList<>(up);
        } else if (y == edgeY) {
            list = new ArrayList<>(down);
        } else if (x == 0) {
            list = new ArrayList<>(left);
        } else if (x == edgeX) {
            list = new ArrayList<>(right);
        } else {
            list = new ArrayList<>(all);
        }

        list.forEach(move -> steps.add(map.get(move)));
        return steps;
    }

    /**
     * @param one 1-й список ходов.
     * @param two 2-й список ходов.
     * @return пересечение ходов.
     */
    private List<Move> intersect(final List<Move> one, final List<Move> two) {
        final List<Move> list = new ArrayList<>(one);
        list.retainAll(two);
        return list;
    }

    /**
     * @param cell проверяемая клетка.
     * @param fX конечная клетка по оси X.
     * @param fY конечная клетка по оси Y.
     * @return true, если клетка является конечной клеткой.
     */
    boolean isEndCell(final Cell cell, int fX, int fY) {
        boolean result = false;
        if (cell.getX() == fX && cell.getY() == fY) {
            result = true;
        }
        return result;
    }

    /**
     * @param args аргс.
     * @throws IOException искл.
     */
    public static void main(String[] args) throws IOException {
        int[][] array = {
                {0, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 1, 1, 1, 1, 1},
                {0, 1, 0, 1, 0, 0, 0, 1},
                {0, 1, 1, 1, 1, 1, 0, 1},
                {0, 0, 0, 1, 0, 1, 1, 1},
                {0, 0, 0, 1, 0, 0, 0, 1},
                {0, 0, 0, 1, 1, 1, 1, 1},
        };
        Robot r = new Robot();
        System.out.println("Min = " + r.minWay(array, 3, 4, 7, 2));
    }
}