package ru.job4j.tracker;

/**
 * Interface UserAction.
 */
public interface UserAction {
    /**
     * Returns int.
     * @return int.
     */
    int key();

    /**
     * Does something with input and tracker.
     * @param input object.
     * @param tracker object.
     */
    void execute(Input input, Tracker tracker);

    /**
     * Returns String.
     * @return String.
     */
    String info();
}