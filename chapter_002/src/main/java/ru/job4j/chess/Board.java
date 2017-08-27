package ru.job4j.chess;

/**
 * Class Board.
 */
public class Board {
    /**
     * Массив фигур на доске.
     */
    private Figure[] figures = new Figure[2];

    /**
     * Перемещает фигуру из положения source в положение dist.
     * @param source - координаты ячейки, в которой должна находиться фигура.
     * @param dist - координаты ячейки, в которую нужно переместить фигуру.
     * @return true или false.
     * @throws ImpossibleMoveException Исключение в случае, если фигура не может "пойти" в направлении dist.
     * @throws OccupiedWayException Исключение в случае, если на пути фигуры к направлению dist расположены др.фигуры.
     * @throws FigureNotFoundException Исключение в случае, если в положении source нет никаких фигур, т.е. ячейка пуста.
     */
    boolean move(Cell source, Cell dist) throws ImpossibleMoveException,
            OccupiedWayException, FigureNotFoundException {
        boolean result = true;
        Cell[] wayCells = null;
        Figure fig = null;
        for (Figure figure : figures) {
            if ((figure.getPosition().getX() == source.getX()) && (figure.getPosition().getY() == source.getY())) {
                fig = figure;
                wayCells = figure.way(dist);
                break;
            } else {
                throw new FigureNotFoundException("Figure is not found on the source cell!");
            }
        }
        for (Cell cell : wayCells) {
            for (Figure figure : figures) {
                if ((cell.getX() == figure.getPosition().getX()) && (cell.getY() == figure.getPosition().getY())) {
                    throw new OccupiedWayException("The figure can't be moved on the destination because of another figure on that position!");
                }
            }
        }
        fig.clone(dist);
        return result;
    }

    /**
     * Создает фигуры на поле.
     */
    public void generate() {
        int k = 3;
        for (int i = 0; i < figures.length; i++) {
            figures[i] = new Bishop("bishop", "black", new Cell(k, 1));
            k *= 2;
        }
    }

    /**
     * Геттер всех фигур.
     * @return ссылку на массив фигур.
     */
    public Figure[] getFigures() {
        return figures;
    }
}