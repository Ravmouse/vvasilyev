package ru.job4j.h7testtask.t3stock;

/**
 * The StubInput class.
 */
public class StubInput implements SimpleInput {
    /**
     * The array of string elements.
     */
    private String[] array;
    /**
     * The position.
     */
    private int pos;

    /**
     * @param array to be assigned.
     */
    public StubInput(String[] array) {
        this.array = array;
    }

    /**
     * @param s is the string to be printed out.
     * @return the User's choice.
     */
    @Override
    public int ask(String s) {
        System.out.print(s);
        return Integer.parseInt(array[pos++]);
    }

    /**
     * @param s is the string to be printed out.
     * @return the User's choice.
     */
    @Override
    public double askD(String s) {
        System.out.print(s);
        return Double.parseDouble(array[pos++]);
    }

    /**
     * @return the User's choice.
     */
    @Override
    public int ask() {
        return Integer.parseInt(array[pos++]);
    }
}