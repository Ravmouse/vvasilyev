package ru.job4j.h7testtask.t3stock;

/**
 * The SimpleInput interface.
 */
public interface SimpleInput {
    /**
     * @param s is the string to be printed out.
     * @return the User's choice.
     */
    int ask(String s);

    /**
     * @param s is the string to be printed out.
     * @return the User's choice.
     */
    double askD(String s);

    /**
     * @return the User's choice.
     */
    int ask();
}