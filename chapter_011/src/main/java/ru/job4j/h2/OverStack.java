package ru.job4j.h2;

/**
 * @author Vitaly Vasilyev, date: 02.11.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class OverStack {

    private static void call(int i) {
        System.out.println(i);
        if (i == 0) {
            return;
        } else {
            try {
                call(++i);
            } catch (StackOverflowError e) {
                System.out.println("The length: " + e.getStackTrace().length);
            }
        }
    }

    public static void main(String[] args) {
        call(1);
    }
}