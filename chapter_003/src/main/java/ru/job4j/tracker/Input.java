package ru.job4j.tracker;
import java.util.List;

/**
 * Interface Input.
 */
public interface Input {
    /**
     * The method ask.
     * @param question The String parameter.
     * @return The String object.
     */
    String ask(String question);

    /**
     * The overloaded method ask() with two parameters.
     * @param question question.
     * @param range range.
     * @return int.
     */
    int ask(String question, List<Integer> range);
}