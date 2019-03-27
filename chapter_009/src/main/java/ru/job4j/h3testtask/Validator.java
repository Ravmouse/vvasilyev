package ru.job4j.h3testtask;

import java.io.File;

/**
 * @author Vitaly Vasilyev, date: 24.03.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Validator {
    /**
     * Аргументы.
     */
    private final String[] args;

    /**
     * @param args массив строк - аргументы.
     */
    public Validator(String[] args) {
        this.args = args;
    }

    /**
     * @return true, если массив не null и ключи являются верными и находятся на своих местах.
     */
    public boolean validate() {
        if (args == null || args.length != 7) {
            throw new RuntimeException("Неверное количество аргументов!");
        }
        if (args[0].equals("-d") && args[2].equals("-n") && (args[4].equals("-m") || args[4].equals("-f"))
                && args[5].equals("-o")) {
            return true;
        } else {
            throw new RuntimeException("Неверные ключи или неправильный порядок расстановки аргументов!");
        }
    }

    /**
     * @return true, если путь у ключа -d существует и является директорией.
     */
    public boolean directory() {
        final File path = new File(this.args[1]);
        if (path.exists()) {
            if (path.isDirectory()) {
                return true;
            } else {
                throw new RuntimeException("Это - файл, а не директория!");
            }
        } else {
            throw new RuntimeException("Директория не существует!");
        }
    }
}