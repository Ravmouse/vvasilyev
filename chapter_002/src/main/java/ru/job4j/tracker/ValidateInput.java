package ru.job4j.tracker;

/**
 * Class ValidateInput.
 */
public class ValidateInput extends ConsoleInput {
    /**
     * The overridden method of ConsoleInput class.
     * @param question String.
     * @param range array of ints.
     * @return int.
     */
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("\nPlease, enter a number that should be from 0 to 6.");
            } catch (NumberFormatException nfe) {
                System.out.println("\nYou should input only NUMBERS from the menu items range, from 0 to 6!!!");
            }
        } while (invalid);
        return value;
    }
}