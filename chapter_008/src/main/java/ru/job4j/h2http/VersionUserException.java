package ru.job4j.h2http;

/**
 * Класс checked-исключения.
 */
public class VersionUserException extends RuntimeException {
    /**
     * @param text сообщение, передаваемое исключению.
     */
    public VersionUserException(String text) {
        super(text);
    }
}