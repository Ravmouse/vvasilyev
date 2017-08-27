package ru.job4j.chess;

/**
 * Class Bishop.
 */
public class Bishop extends Figure {

    /**
     * Конструктор.
     * @param name - имя фигуры.
     * @param color - цвет фигуры.
     * @param pos - координаты фигуры в виде объекта класса Cell.
     */
    public Bishop(String name, String color, Cell pos) {
        super(name, color, pos);
    }

    /**
     * Возвращает массив объектов типа Cell (координат доски) от положения текущей фигуры до ячейки dist при условии
     * что фигура может "ходить" в том направлении.
     * @param dist - ячейка, до которой нужно вернуть массив Cell.
     * @return массив Cell.
     * @throws ImpossibleMoveException Исключение в случае, если фигура не может "пойти" в направлении dist.
     */
    @Override
    public Cell[] way(Cell dist) throws ImpossibleMoveException {
        Cell[] result = null;
        if ("right".equals(this.getPosition().find(dist))) {
            result = this.getPosition().wayForFirstFunc(dist);
        } else if ("left".equals(this.getPosition().find(dist))) {
            result = this.getPosition().wayForSecondFunc(dist);
        } else {
            throw new ImpossibleMoveException("You can't move the figure on that position!");
        }
        return result;
    }
}