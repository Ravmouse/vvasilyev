package ru.job4j.chess;

/**
 * Class FigureNotFoundException.
 */
public class FigureNotFoundException extends RuntimeException {
    /**
     * Конструктор.
     * @param name - строка для текущего исключения.
     */
    public FigureNotFoundException(String name) {
        super(name);
    }
}