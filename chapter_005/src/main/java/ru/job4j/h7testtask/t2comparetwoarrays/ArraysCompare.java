package ru.job4j.h7testtask.t2comparetwoarrays;
import java.util.Arrays;
import java.util.Random;
import java.util.Collections;
import java.util.HashSet;

/**
 * The class with comparing methods.
 */
public class ArraysCompare {
    /**
     * @param one is the first array to be compared.
     * @param two is the second array to be compared.
     * @param <E> is the type parameter.
     * @return true if the arrays have the same length and the same set of elements.
     */
    public <E extends Number> boolean areTwoArraysEqualFirst(E[] one, E[] two) {
        int i;
        if (one.length != two.length) {
            return false;
        }
        Arrays.sort(one);              //Arrays.sort offers a Dual-Pivot quicksort, that is faster than a one-pivot qs.
        Arrays.sort(two);
        for (i = 0; i < one.length; i++) {
            if (!one[i].equals(two[i])) { //If the first found element isn't equal to the element on the same position
                break;                    //of the second array, then these arrays aren't equal.
            }
        }
        return i == one.length;
    }

    /**
     * @param one is the first array to be compared.
     * @param two is the second array to be compared.
     * @param <E> is the type parameter.
     * @return true if the arrays have the same length and the same set of elements.
     */
    public <E extends Number> boolean areTwoArraysEqualSecond(E[] one, E[] two) {
        if (one.length != two.length) {
            return false;
        }
        int j; E tmp;
        for (int i = 0; i < one.length; i++) {
            j = i;
            while (!one[i].equals(two[j])) { //Поиск во 2-м массиве элемента, который был бы равен элементу из 1-го
                j++;                         //массива. Если эл-т 2-го массива, например, в позиции [0] не равен
                if (j == two.length) {       //эл-ту в позиции [0] 1-го массива, то перейти к след.эл-ту 2-го массива.
                    return false;            //Если дошли до конца 2-го массива, не найдя эл-та из 1-го массива, то
                }                            //массивы не равны.

                                             //Searching the second array's element that would be equal to an element
                                             //of the first array. If the second array's element in the first position
                                             //isn't equal to the same position's element of the first array, then need
                                             //to iterate the element of the second array.
            }
            if (i != j) {
                tmp = two[i];
                two[i] = two[j];
                two[j] = tmp;
            }
        }
        return true;
    }

    /**
     * @param one is the first array of int elements.
     * @param two is the second array of int elements.
     * @return true if these two arrays have the same elements in different order and the same length.
     */
    public boolean areTwoArraysEqualThird(int[] one, int[] two) {
        if (one.length != two.length) {
            return false;
        }
        HashSet<Integer> set = new HashSet<>();
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

    /**
     * @param one is the first array of int elements.
     * @param two is the second array of int elements.
     * @return 1 if the i-element of the first array is greater than the i-element of the second array,
     * -1 if the i-element of the first array is less than the i-element of the second array and 0 if they're equal.
     */
    public int compareTwoArrays(int[] one, int[] two) {
        int i, rsl = 0;
        int min = (one.length <= two.length) ? one.length : two.length;
        for (i = 0; i < min; i++) {
            if (one[i] > two[i]) {
                rsl = 1;
                break;
            } else if (one[i] < two[i]) {
                rsl = -1;
                break;
            }
        }
        if ((i == min) && (one.length != two.length)) {
            rsl = (one.length > two.length) ? 1 : -1;
        }
        return rsl;
    }

    /**
     * @param args is the String array.
     */
    public static void main(String[] args) {

        Integer[] one = new Integer[1000_000];
        Integer[] two;
        Random rnd = new Random();
        for (int i = 0; i < one.length; i++) {
            one[i] = rnd.nextInt(100);
        }
        two = one.clone();
        Collections.shuffle(Arrays.asList(two));
        ArraysCompare ar = new ArraysCompare();
        long start;
        start = System.currentTimeMillis();
        ar.areTwoArraysEqualFirst(one, two);
        System.out.println("The first method: " + (System.currentTimeMillis() - start));
        //=====================================================

        for (int i = 0; i < one.length; i++) {
            one[i] = rnd.nextInt(100);
        }
        two = one.clone();
        Collections.shuffle(Arrays.asList(two));
        start = System.currentTimeMillis();
        ar.areTwoArraysEqualSecond(one, two);
        System.out.println("The second method: " + (System.currentTimeMillis() - start));
        //=====================================================

        int[] a = new int[1000_000];
        int[] b;
        for (int i = 0; i < a.length; i++) {
            a[i] = rnd.nextInt(100);
        }
        b = a.clone();
        Collections.shuffle(Arrays.asList(b));
        start = System.currentTimeMillis();
        System.out.println(ar.areTwoArraysEqualThird(a, b));
        System.out.println("The third method: " + (System.currentTimeMillis() - start));
    }
}