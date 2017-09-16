package ru.job4j.tracker;
import java.util.List;

/**
 * Class StubInput.
 */
public class StubInput implements Input {
    /**
     * List with answers.
     */
    private List<String> answers;
    /**
     * Position of String object in the List.
     */
    private int position;

    /**
     * The constructor.
     * @param answers The List of String elements.
     */
    public StubInput(List<String> answers) {
        this.answers = answers;
    }

    /**
     * Returns each String object in the List of Strings.
     * @param question as a passed parameter.
     * @return The String object.
     */
    @Override
    public String ask(String question) {
        return answers.get(position++);
    }

    /**
     * The overloaded method.
     * @param question question.
     * @param range range.
     * @return int.
     */
    @Override
    public int ask(String question, List<Integer> range) {
        int key = Integer.parseInt(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Please, enter a number that should be from 0 to 6.");
        }
    }
}