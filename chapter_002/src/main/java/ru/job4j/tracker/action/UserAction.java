package ru.job4j.tracker.action;

import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.input.Input;

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