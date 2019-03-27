package ru.job4j.h3testtask;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Vitaly Vasilyev, date: 25.03.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Finder {
    /**
     * Аргументы.
     */
    private final String[] args;

    /**
     * @param args массив строк - аргументы.
     */
    public Finder(String[] args) {
        this.args = args;
    }

    /**
     * @return список файлов, содержащий искомый(-ые) файл(-ы).
     */
    public List<File> fill() {
        final List<File> files = new ArrayList<>();
        final Queue<File> queue = new LinkedList<>();
        queue.offer(new File(this.args[1]));
        while (!queue.isEmpty()) {
            final File file = queue.poll();
            if (!file.isDirectory()) {
                if (this.args[4].equals("-m")) {
                    addByExt(this.args[3], file, files);
                } else {
                    addByName(this.args[3], file, files);
                }
            } else {
                File[] dirs = file.listFiles();
                if (dirs != null) {
                    for (File f : dirs) {
                        queue.offer(f);
                    }
                }
            }
        }
        if (files.isEmpty()) {
            throw new RuntimeException("Файл не был найден.");
        }
        return files;
    }

    /**
     * @param s название файла с расширением в виде *.extension.
     * @param file файл для проверки своего имени.
     * @param files список для добавления файлов.
     */
    private void addByExt(String s, File file, List<File> files) {
        final String ext = s.substring(s.lastIndexOf(".") + 1);
        if (file.getName().substring(file.getName().lastIndexOf(".") + 1).equals(ext)) {
            files.add(file);
        }
    }

    /**
     * @param s название файла с расширением в виде file.extension.
     * @param file файл для проверки своего имени.
     * @param files список для добавления файлов.
     */
    private void addByName(String s, File file, List<File> files) {
        if (file.getName().equals(s)) {
            files.add(file);
        }
    }
}