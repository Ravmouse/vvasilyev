package ru.job4j.h2http;

/**
 * @author Vitaly Vasilyev, date: 17.10.2018, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class NotExistedUserException extends RuntimeException {
    /**
     * @param text сообщение, передаваемое исключению.
     */
    public NotExistedUserException(String text) {
        super(text);
    }
}