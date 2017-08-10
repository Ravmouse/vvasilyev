package ru.job4j.loop;
/**
 * Class Factorial.
 * @author Vitaly Vasilyev
 * @version $Id$
 * @since 0.1
 */
public class Factorial {

    /**
     * The method calculates the factorial of the passed parameter.
     * @param n The int number
     * @return The factorial.
     */
    public int calc(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return calc(n - 1) * n;
    }
}

