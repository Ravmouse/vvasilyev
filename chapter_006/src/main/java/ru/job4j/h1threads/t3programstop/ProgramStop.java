package ru.job4j.h1threads.t3programstop;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * A class with two threads.
 */
public class ProgramStop {
    /**
     * A ref. to the first thread.
     */
    private Thread countThread;
    /**
     * A ref. to the second thread.
     */
    private Thread timeThread;

    /**
     * An inner static class.
     */
    public final class Time implements Runnable {
        /**
         * The app's work time.
         */
        private final long workTime = 20_000;
        /**
         * Counts the time and interrupts the second thread.
         */
        @Override
        public void run() {
            long start = System.currentTimeMillis();
            long curr;
            while (!Thread.currentThread().isInterrupted()) { //Если текущий поток не прерван...а он может быть прерван,
                                                              //если пользователь введет "exit".
                curr = System.currentTimeMillis();
                if ((curr - start) >= workTime) {
                    countThread.interrupt();                  //Выставить флаг прерывания на 2-ой поток.
                    break;
                }
            }
        }
    }

    /**
     * An inner static class.
     */
    public final class CountChar implements Runnable {
        /**
         * For storing a text that is entered by a user.
         */
        private String text;
        /**
         * Counts symbols and shows the number of entered symbols in the loop.
         */
        @Override
        public void run() {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                do {
                    System.out.println("Enter any text ['exit' for quit]:");
                    text = br.readLine();
                    System.out.println(text);
                    System.out.println("The number of symbols: " + text.toCharArray().length);
                } while ((!Thread.currentThread().isInterrupted()) && (!text.equalsIgnoreCase("exit")));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (text.equalsIgnoreCase("exit")) { //Если пользователь ввел 'exit', то "прервать" 1-ый поток, который
                timeThread.interrupt();          //еще не отсчитал положенного времени.
            }
        }
    }

    /**
     * @param args args.
     */
    public static void main(String[] args) {
        ProgramStop p = new ProgramStop();
        p.timeThread = new Thread(p.new Time());
        p.timeThread.start();
        p.countThread = new Thread(p.new CountChar());
        p.countThread.start();
    }
}