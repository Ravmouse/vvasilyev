package ru.job4j.testtask;
import java.util.Comparator;

/**
 * ReverseComp class.
 */
public class ReverseComp implements Comparator<String> {
    /**
     * @param left - string to be compared.
     * @param right - string to be compared.
     * @return 0 if the left is equal to right, a value greater than 0 the left is greater than right, a value less
     * than 0 if the left is less than right.
     */
    @Override
    public int compare(String left, String right) {
        int i = 1;
        int dig = 0; int slash = 0;
        while ((dig == 0) && (slash == 0)) {
            dig = numberCheck(left, right, i);
            slash = backSlashCheck(left, right, i + 1);
            if (i == 5) {
                i += 5;
            } else {
                i += 4;
            }
        }
        return dig != 0 ? dig : slash;
    }

    /**
     * @param str1 - string to be compared.
     * @param str2 - string to be compared.
     * @param position to start comparing from.
     * @return 0, -1 or 1.
     */
    private static int backSlashCheck(String str1, String str2, int position) {
        int first = str1.indexOf('\\', position);
        int second = str2.indexOf('\\', position);
        return Integer.compare(first, second);
    }

    /**
     * @param str1 - string to be compared.
     * @param str2 - string to be compared.
     * @param position to start comparing from.
     * @return 0, -1 or 1.
     */
    private static int numberCheck(String str1, String str2, int position) {
        char first = str1.charAt(position);
        char second = str2.charAt(position);
        int res = Integer.compare(first, second);
        return res == 0 ? res : -res;
    }
}