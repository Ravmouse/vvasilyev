package ru.job4j.tracker;
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
}