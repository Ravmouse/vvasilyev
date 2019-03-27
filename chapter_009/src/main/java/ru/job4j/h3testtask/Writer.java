package ru.job4j.h3testtask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 25.03.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Writer {
    /**
     * Аргументы.
     */
    private final String[] args;
    /**
     * Список с файлом(-ами) для внесения в текстовый файл.
     */
    private final List<File> files;

    /**
     * @param args аргументы.
     * @param files список с файлом(-ами) для внесения в текстовый файл.
     */
    public Writer(String[] args, List<File> files) {
        this.args = args;
        this.files = files;
    }

    /**
     * @throws IOException искл.
     */
    public void write() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.args[6]))) {
            files.forEach(f -> writer.println(f.getPath()));
        }
    }
}