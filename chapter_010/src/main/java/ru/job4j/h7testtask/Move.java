package ru.job4j.h7testtask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Vitaly Vasilyev, date: 04.07.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Move {
    /**
     *
     */
    private static final List<int[]> COMBS = new ArrayList<>();

    static {
        COMBS.add(new int[] {1, 2, 3});
        COMBS.add(new int[] {1, 4, 7});
        COMBS.add(new int[] {1, 5, 9});
        COMBS.add(new int[] {2, 5, 8});
        COMBS.add(new int[] {3, 5, 7});
        COMBS.add(new int[] {3, 6, 9});
        COMBS.add(new int[] {4, 5, 6});
        COMBS.add(new int[] {7, 8, 9});
    }

    /**
     * @param position
     * @return
     */
    public static int getNextMove(int position) {
        int result = 0;
        boolean flag = false;
        for (int[] arr : COMBS) {
            for (int i = 0; i < arr.length; i++) {
                if (position == arr[i]) {
                    result = getRandomValue(i, arr);
                    COMBS.remove(arr);
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        return result;
    }

    /**
     * @param index индекс массива.
     * @param array массив, из которого нужно получить значение, с индексом, не равным index.
     * @return рандомное значение с индексом, не равным index.
     */
    private static int getRandomValue(int index, int[] array) {
        int result;
        do {
            result = new Random().nextInt(array.length);
        } while (result == index);
        return array[result];
    }

//    public static void main(String[] args) {
//        COMBS.forEach(value -> System.out.print(Arrays.toString(value)));
//        System.out.println();
//        System.out.println(Move.getNextMove(5));
//        COMBS.forEach(value -> System.out.print(Arrays.toString(value)));
//    }
}