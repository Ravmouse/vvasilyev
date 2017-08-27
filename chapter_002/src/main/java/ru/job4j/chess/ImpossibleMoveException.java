package ru.job4j.chess;

/**
 * Class ImpossibleMoveException.
 */
public class ImpossibleMoveException extends RuntimeException {
    /**
     * Конструктор.
     * @param name - строка для текущего исключения.
     */
    public ImpossibleMoveException(String name) {
        super(name);
    }
}