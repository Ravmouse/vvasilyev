package ru.job4j.h1io.t3scanfilesys;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Vitaly Vasilyev, date: 03.03.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Search {
    /**
     * @param parent путь до каталога, с которого нужно осуществлять поиск.
     * @param exts список расширений файлов, которых мы хотим найти.
     * @return список файлов.
     */
    public static List<File> files(String parent, List<String> exts) {
        final List<File> files = new ArrayList<>();
        final Queue<File> queue = new LinkedList<>();
        queue.offer(new File(parent));
        while (!queue.isEmpty()) {
            final File file = queue.poll();
            if (!file.isDirectory()) {
                for (String s : exts) {
                    if (file.getName().contains(s)) {
                        files.add(file);
                    }
                }
            } else {
                for (File f : file.listFiles()) {
                    queue.offer(f);
                }
            }
        }
        return files;
    }
}