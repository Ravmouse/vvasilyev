package ru.job4j.tracker;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.ValidateInput;

/**
 * Class StartUI.
 */
public class StartUI {
    /**
     * The reference to the Tracker object with the array of Item elements.
     */
    private Tracker tracker = new Tracker();
    /**
     * The reference to the Input interface.
     */
    private Input input;
//    private static final int FIRST_MENU_ITEM = 0;
//    private static final int LAST_MENU_ITEM = 6;
    /**
     * The array of integers.
     */
    private int[] range;


    /**
     * The constructor.
     * @param input The reference to Input object.
     */
    public StartUI(Input input) {
        this.input = input;
    }

    /**
     * Initializes input and tracker fields.
     * @param input of Input type.
     * @param tracker of Tracker type.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * The entry point of the program.
     * @param args The array of String variables.
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput()).init();
    }

    /**
     * Creates the UI and displays it on the screen.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        int answer;
        menu.fillActions();
        initializeRange(menu);
        do {
            menu.show();
            answer = input.ask("Please, choose a menu option: ", range);
            menu.select(answer);
        } while (answer != 6);
    }

    /**
     * Creates the array of integers and initializes it according to the length of UserAction array in the
     * MenuTracker class.
     * В этом методе создается и инициализируется массив int'ов (range) в соответствии с длиной массива UserAction
     * из класса MenuTracker. Это нужно для метода ask() из ConsoleInput, чтобы проверять, что число
     * пользователя находится в нужном диапазоне (например, от 0 до 6).
     * @param menu The ref. to the MenuTracker object.
     */
    private void initializeRange(MenuTracker menu) {
        range = new int[menu.getActions().length];
        for (int i = 0; i < menu.getActions().length; i++) {
            range[i] = i;
        }
    }
}