package ru.job4j.tracker;

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
}