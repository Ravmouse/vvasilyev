package ru.job4j.tracker;
import java.util.List;

/**
 * Class ValidateInput.
 */
public class ValidateInput extends ConsoleInput {
    /**
     * The overridden method of ConsoleInput class.
     * @param question String.
     * @param range List of Integer.
     * @return int.
     */
    public int ask(String question, List<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("\nA number should be from 0 to 6 range.");
            } catch (NumberFormatException nfe) {
                System.out.println("\nYou should input only NUMBERS from the menu items range, from 0 to 6!!!");
            }
        } while (invalid);
        return value;
    }
}