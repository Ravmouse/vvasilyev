package ru.job4j.tracker.input;

import ru.job4j.tracker.exception.MenuOutException;
import java.util.Scanner;

/**
 * Class ConsoleInput.
 */
public class ConsoleInput implements Input {
    /**
     * The reference to Scanner object.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * The method returns the characters in string representation that are entered by user.
     * @param question The String parameter.
     * @return The reference to String object.
     */
    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * Returns the int value that should be between 0 and 6.
     * If the String value is entered by user then NumberFormatException occurs.
     * If the int value is entered by user that is not between 0 and 6 then MenuOutException occurs.
     * @param question question.
     * @param range range.
     * @return int.
     */
    public int ask(String question, int[] range) {
        int key = Integer.parseInt(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Please, enter a number that should be from 0 to 6.");
        }
    }
}