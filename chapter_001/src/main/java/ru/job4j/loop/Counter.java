package ru.job4j.loop;
/**
 * Class Counter.
 * @author Vitaly Vasilyev
 * @version $Id$
 * @since 0.1
 */
public class Counter {

    /**
     * The method calculates the sum of even integers of two passed arguments.
     * @param start The first number
     * @param finish The second number
     * @return The sum of even numbers.
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}