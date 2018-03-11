package ru.job4j.h1threads.t1wordsandspaces;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * A test class.
 */
public class CountWordsAndSpacesTest {
    /**
     * Checks the result of counting spaces in the text.
     */
    @Test
    public void whenPassStringThenCountAmountOfSpaces() {
        final String str = "Hello, what is your name? I don't know.";
        final int amountOfSpaces = new CountWordsAndSpaces(str).countSpaces();
        assertThat(amountOfSpaces, is(7));
    }

    /**
     * Checks the result of counting words in the text.
     */
    @Test
    public void whenPassStringThenCountAmountOfWords() {
        final String str = "And the tides of darkness swept over the world.";
        final int amountOfWords = new CountWordsAndSpaces(str).countWords();
        assertThat(amountOfWords, is(9));
    }
}