package ru.job4j.tracker;
import java.util.ArrayList;
import java.util.List;

/**
 * Class StartUI.
 */
public class StartUI {
    /**
     * Tracker.
     */
    private Tracker tracker = new Tracker();
    /**
     * Input.
     */
    private Input input;
    /**
     * The List of Integers.
     */
    private List<Integer> range;

    /**
     * The constructor.
     * @param input The reference to Input object.
     */
    public StartUI(Input input) {
        this.input = input;
    }

    /**
     * The constructor for StubInputTest class.
     * Этот конструктор нужен для класса StubInputTest, где в качестве ссылки на Input передается объект StubInput.
     * @param input object.
     * @param tracker object.
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
            answer = input.ask("Please, choose the option of the menu: ", range);
            menu.select(answer);
        } while (answer != 6);
    }

    /**
     * Fills the List of Integers with values according to the size of List of UserActions in the MenuTracker class.
     * @param menu The ref. to the MenuTracker object.
     */
    private void initializeRange(MenuTracker menu) {
        range = new ArrayList<>();
        int i = 0;
        for (UserAction ua : menu.getActions()) {
            range.add(i++);
        }
    }
}