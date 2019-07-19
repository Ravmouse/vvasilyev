package ru.job4j.h7testtask.input;

import java.util.Scanner;

/**
 * @author Vitaly Vasilyev, date: 05.07.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class ConsoleInput implements Input {
    /**
     * Сканнер.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * @param in строка с предложением.
     * @return строку, считываемую с консоли.
     */
    @Override
    public String askS(String in) {
        System.out.print(in);
        return scanner.nextLine();
    }

    /**
     * @param in строка с предложением.
     * @return целочисленное значение.
     */
    @Override
    public int askI(String in) {
        return Integer.parseInt(askS(in));
    }

    /**
     * Закрыть поток ввода.
     */
    @Override
    public void close() {
        this.scanner.close();
    }
}