package ru.job4j.h2;

import java.math.BigInteger;

/**
 * @author Vitaly Vasilyev, date: 02.11.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class OverHeap {

    public static void main(String[] args) {
        int i = 0;
        while (true) {
            BigInteger[] big = new BigInteger[1_000_000];
            System.out.println(++i);
        }
    }
}