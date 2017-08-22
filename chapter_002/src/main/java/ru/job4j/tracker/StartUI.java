package ru.job4j.tracker;

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
    /**
     * The FIRST_MENU_ITEM const.
     */
    private static final int FIRST_MENU_ITEM = 0;
    /**
     * The LAST_MENU_ITEM const.
     */
    private static final int LAST_MENU_ITEM = 6;


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
        new StartUI(new ConsoleInput()).init();
    }

    /**
     * Creates the UI and displays it on the screen.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        int answer;
        menu.fillActions();
        for (;;) {
            do {
                menu.show();
                answer = Integer.parseInt(input.ask("Please, choose a menu option: "));
            } while (answer < FIRST_MENU_ITEM || answer > LAST_MENU_ITEM);

            menu.select(answer);

            if (answer == LAST_MENU_ITEM) {
                break;
            }
        }
    }
}