package ru.job4j.chess;

/**
 * Class Figure.
 */
public abstract class Figure {
    /**
     * Имя фигуры.
     */
    private final String name;
    /**
     * Цвет фигуры.
     */
    private final String color;
    /**
     * Координаты фигуры.
     */
    private Cell position;

    /**
     * Конструктор.
     * @param name - имя фигуры.
     * @param color - цвет фигуры.
     * @param position - координаты фигуры в виде объекта класса Cell.
     */
    public Figure(String name, String color, Cell position) {
        this.name = name;
        this.color = color;
        this.position = position;
    }

    /**
     * Геттер для координат фигуры.
     * @return координаты фигуры.
     */
    public Cell getPosition() {
        return position;
    }

    /**
     * Возвращает массив объектов типа Cell (координат доски) от положения текущей фигуры до ячейки dist при условии
     * что фигура может "ходить" в том направлении.
     * @param dist - ячейка, до которой нужно вернуть массив Cell.
     * @return массив Cell.
     * @throws ImpossibleMoveException Исключение в случае, если фигура не может "пойти" в направлении dist.
     */
    public abstract Cell[] way(Cell dist) throws ImpossibleMoveException;

    /**
     * Для текущей фигуры перенаправляет ссылку с текущей позиции на позицию dist.
     * @param dist - ячейка, куда нужно переместить текущую фигуру.
     * @return ссылку на текущую фигуру.
     */
    public Figure clone(Cell dist) {
        this.position = dist;
        return this;
    }

    /**
     * Переопределение toString() для отображения экземпляра класса.
     * @return Объект типа String.
     */
    public String toString() {
        return name + " " + color + " " + position;
    }
}