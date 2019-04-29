package ru.job4j.h1srp.input;

/**
 * @author Vitaly Vasilyev, date: 09.04.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public interface Input extends AutoCloseable {
    /**
     * @param in строка с предложением.
     * @return строковое представление.
     */
    String askS(String in);
    /**
     * @param in строка с предложением.
     * @return целочисленное значение.
     */
    int askI(String in);
    /**
     * Закрыть поток ввода.
     */
    void close();
}