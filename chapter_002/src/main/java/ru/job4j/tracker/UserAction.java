package ru.job4j.tracker;

/**
 * Interface UserAction.
 */
public interface UserAction {
    /**
     * The method to implement.
     * @return int.
     */
    int key();

    /**
     * The method to implement.
     * @param input input.
     * @param tracker tracker.
     */
    void execute(Input input, Tracker tracker);

    /**
     * The method to implement.
     * @return String.
     */
    String info();
}
