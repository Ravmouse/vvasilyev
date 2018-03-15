package ru.job4j.h1threads.t2outputstandby;
import ru.job4j.h1threads.t1wordsandspaces.CountWordsAndSpaces;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * A class where two additional threads are created. After that they join to the main thread.
 */
public class StandByForOutput extends CountWordsAndSpaces {

    /**
     * @param text is a parameter to be assigned.
     */
    public StandByForOutput(String text) {
        super(text);
    }

    /**
     * @param args args.
     */
    public static void main(String[] args) {
        System.out.println("*** Greetings, User! ***");
        System.out.println("This is a program that counts a number of spaces and words in a text.");
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Please, enter a text: ");
            final String str = br.readLine();
            final Thread spaceThread = new Thread(new StandByForOutput(str).new CountSpaces());
            final Thread wordThread = new Thread(new StandByForOutput(str).new CountWords());
            spaceThread.start();
            wordThread.start();

            spaceThread.join();
            wordThread.join();
            System.out.println("Exiting program.\n*** Good Buy, User! ***");
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }
}