package ru.job4j.max;
/**
 * Class Max.
 * @author Vitaly Vasilyev
 * @version $Id$
 * @since 0.1
 */
public class Max {
    /**
     * The method returns maximum of two passed parameters.
     * @param first first value
     * @param second second value
     * @return maximum value
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }
}
