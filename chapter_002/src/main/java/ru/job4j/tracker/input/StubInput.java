package ru.job4j.tracker.input;

/**
 * Class StubInput.
 */
public class StubInput implements Input {
    /**
     *
     */
    private String[] answers;
    /**
     *
     */
    private int position;

    /**
     *
     * @param answers The String array.
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     *
     * @param question The String parameter.
     * @return The String object.
     */
    @Override
    public String ask(String question) {
        return answers[position++];
    }

    /**
     * The overloaded method.
     * @param question question.
     * @param range range.
     * @return int.
     */
    public int ask(String question, int[] range) {
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
            throw new RuntimeException("Please, enter a number that should be from 0 to 6.");
        }
    }
}