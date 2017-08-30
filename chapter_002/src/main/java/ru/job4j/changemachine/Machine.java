package ru.job4j.changemachine;

/**
 * Class Machine.
 */
public class Machine {

    /**
     * A variable with a string literal 10.
     * Строковый литерал 10.
     */
    private String s1 = String.format("%6d", 10);
    /**
     * A variable with two string literals 5 & 5.
     * Два строковых литерала 5 и 5.
     */
    private String s2 = String.format("%6d %6d", 5, 5);
    /**
     * A variable with two string literals 5 & 11111.
     * Два строковых литерала 5 и 11111.
     */
    private String s3 = String.format("%6d %6d", 5, 11111);
    /**
     * A variable with two string literals 11111 & 11111.
     * Два строковых литерала 11111 и 11111.
     */
    private String s4 = String.format("%6d %6d", 11111, 11111);

    /**
     * A value that should be added to the tmp array's length after returning from recursion call.
     * Переменная, на значение которой увеличивается величина массива tmp после возврата из вызова рекурсивного
     * метода exc().
     */
    private int outer = 5;
    /**
     * A value that should be subtracted from the res array's length after returning from recursion call.
     * Переменная, которая вычитается из длины массива res, и на каждом шаге вызова рекурсивного метода exc()
     * увеличивается на 2.
     */
    private int inner = 3;

    /**
     * Returns a two-dimensional array of string elements that contains all the variants for exchanging
     * from note to coins.
     * Метод для построения двумерного массива со всеми вариантами выдачи монет в зависмости от параметра value.
     * @param value Денежный эквивалент в купюрах для размена на монеты.
     * @return Двумерный массив.
     */
    private String[][] exc(int value) {
        String[][] res = new String[4][];
        int index = 0;
        res[index++] = new String[] {s1};
        res[index++] = new String[] {s2};
        res[index++] = new String[] {s3};
        res[index++] = new String[] {s4};

        if (value == 10) {
            return res;
        }

        res = exc(value - 10);

        String[][] tmp = new String[res.length + outer][1];
        int i = 0;
        int k = res.length - inner;

        for (; i < res.length; i++) {
            tmp[i][0] = s1 + res[i][0];
        }

        for (; i < tmp.length - 2; i++) {
            tmp[i][0] = s2 + res[k++][0];
        }
        tmp[i++][0] = s3 + res[res.length - 1][0];
        tmp[i][0] = s4 + res[res.length - 1][0];
        outer += 2;
        inner += 2;
        return tmp;
    }

//    /**
//     * A main method.
//     * Метод main().
//     * @param args Массив строк.
//     */
//    public static void main(String[] args) {
//        for (String[] s : new Machine().exc(50)) {
//            System.out.println(Arrays.toString(s));
//        }
//    }
}