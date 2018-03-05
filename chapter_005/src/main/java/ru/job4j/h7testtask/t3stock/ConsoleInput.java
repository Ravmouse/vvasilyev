package ru.job4j.h7testtask.t3stock;
import java.util.Scanner;

/**
 * The ConsoleInput class.
 */
public class ConsoleInput implements SimpleInput {
    /**
     * The scanner.
     */
    private Scanner scanner;

    /**
     * The constructor.
     */
    public ConsoleInput() {
        scanner = new Scanner(System.in);
    }

    /**
     * @param s is the string to be printed out.
     * @return the User's choice.
     */
    @Override
    public int ask(String s) {
        System.out.print(s);
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * @param s is the string to be printed out.
     * @return the User's choice.
     */
    @Override
    public double askD(String s) {
        System.out.print(s);
        return Double.parseDouble(scanner.nextLine());
    }

    /**
     * @return the User's choice.
     */
    @Override
    public int ask() {
        return Integer.parseInt(scanner.nextLine());
    }
}