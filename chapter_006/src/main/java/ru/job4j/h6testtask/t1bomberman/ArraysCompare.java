package ru.job4j.h6testtask.t1bomberman;
import java.util.HashSet;

/**
 * Класс для сравнения двух одномерных массивов.
 */
class ArraysCompare {
    /**
     * @param one 1-й массив целочисленных значений.
     * @param two 2-й массив целочисленных значений.
     * @return true, если массивы равны и false, если - нет.
     */
    static boolean compareTwoArrays(final int[] one, final int[] two) {
        if (one.length != two.length) {
            return false;
        }
        final HashSet<Integer> set = new HashSet<>();
        for (int i : one) {
            set.add(i);
        }
        int lenOne = set.size();
        for (int i : two) {
            set.add(i);
        }
        int lenTwo = set.size();
        return lenOne == lenTwo;
    }
}