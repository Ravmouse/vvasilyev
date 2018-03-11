package ru.job4j.h1threads.t1wordsandspaces;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * A class wih two inner classes and methods counting amount of words and spaces in a text.
 */
public class CountWordsAndSpaces {
    /**
     * A variable containing entered text as a string.
     */
    private final String text;

    /**
     * @param text is a parameter to be assigned.
     */
    public CountWordsAndSpaces(String text) {
        this.text = text;
    }

    /**
     * An inner class.
     */
    public final class CountSpaces implements Runnable {
        /**
         * Shows amount of spaces in a text.
         */
        @Override
        public void run() {
            System.out.println("Spaces: " + countSpaces());
        }
    }

    /**
     * An inner class.
     */
    public final class CountWords implements Runnable {
        /**
         * Shows amount of words in a text.
         */
        @Override
        public void run() {
            System.out.println("Words: " + countWords());
        }
    }

    /**
     * @return the amount of spaces.
     */
    public int countSpaces() {
        return handleString(0, text);
    }

    /**
     * @return the amount of words.
     */
    public int countWords() {
        return handleString(1, text);
    }

    /**
     * @param value is a parameter that could be 0 or 1.
     * @param string is a parameter to be checked.
     * @return amount of spaces if the value is 0, or amount of words if the value is 1.
     */
    private int handleString(int value, String string) {
        for (char ch : string.toCharArray()) {
            if (ch == ' ') {
                value++;
            }
        }
        return value;
    }

    /**
     * @param args args.
     */
    public static void main(String[] args) {
        String s;
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                System.out.println("Enter any text ['exit' for quit]: ");
                s = br.readLine();
                System.out.println(s);
                new Thread(new CountWordsAndSpaces(s).new CountSpaces()).start();
                new Thread(new CountWordsAndSpaces(s).new CountWords()).start();
            } while (!s.equalsIgnoreCase("exit"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}