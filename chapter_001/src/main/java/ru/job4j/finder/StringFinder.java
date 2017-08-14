package ru.job4j.finder;
/**
 * Class StringFinder.
 * @author Vitaly Vasilyev.
 * @version 1.0.0.
 * @since 0.1
 */
public class StringFinder {
    /**
     * The method defines whether String-type variable 'origin' contains sequence of characters of variable 'sub' or not.
     * @param origin The string that should contain a sequence of characters.
     * @param sub The string that should be found within the first parameter.
     * @return True or false.
     */
    boolean contains(String origin, String sub) {
        int i, j, k = 0, subPass = 0, originPass = 0;
        boolean isFirstLetterFound = false;

        for (i = 0; i < sub.length(); i++) {
            for (j = k; j < origin.length(); j++) {
                if (sub.charAt(i) == origin.charAt(j)) {
                    k = j + 1;
                    subPass++;
                    originPass++;
                    isFirstLetterFound = true;
                    break;
                }
                if (isFirstLetterFound) {
                    originPass++;
                }
            }
        }
        return subPass == originPass;
    }
}