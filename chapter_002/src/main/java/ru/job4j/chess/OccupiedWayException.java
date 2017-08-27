package ru.job4j.chess;

/**
 * Class OccupiedWayException.
 */
public class OccupiedWayException extends RuntimeException {
    /**
     * Конструктор.
     * @param name - строка для текущего исключения.
     */
    public OccupiedWayException(String name) {
        super(name);
    }
}