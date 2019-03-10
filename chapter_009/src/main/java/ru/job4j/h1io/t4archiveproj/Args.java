package ru.job4j.h1io.t4archiveproj;

import org.apache.log4j.Logger;
import ru.job4j.utils.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Vitaly Vasilyev, date: 07.03.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Args {
    /**
     * Массив строк, вводимый пользователем при запуске приложения.
     */
    private String[] args;
    /**
     * Хэш-отображение с ключом-строкой и значением-методом.
     */
    private final Map<String, Consumer<String>> methods = new HashMap<>();
    /**
     * Логгер.
     */
    private static final Logger LOGGER = Logger.getLogger(Utils.getNameOfTheClass());
    /**
     * Директория, которую нужно заархивировать.
     */
    private File path;
    /**
     * Список файлов в директории, которую нужно заархивировать.
     */
    private List<File> files = new ArrayList<>();
    /**
     * Переменная, которая сигнализирует, есть ли введеный путь для архивации или нет.
     */
    private boolean existedDir;

    /**
     * @param args массив строк.
     */
    public Args(String[] args) {
        if (args == null || args.length != 6) {
            LOGGER.warn("Неверное количество аргументов!");
        } else {
            this.args = args;
            methods.put("-d", directory());
            methods.put("-e", exclude());
            methods.put("-o", output());
            parseArgs("-d");
            if (existedDir) {
                parseArgs("-e");
                parseArgs("-o");
            }
        }
    }

    /**
     * Если директория существует и это не файл, то все ОК!
     * @return экземпляр класса Consumer.
     */
    public Consumer<String> directory() {
        return dirName -> {
            path = new File(dirName);
            if (path.exists()) {
                if (path.isDirectory()) {
                    existedDir = true;
                } else {
                    LOGGER.warn("Это - файл, а не директория!");
                }
            } else {
                LOGGER.warn("Директория не существует!");
            }
        };
    }

    /**
     * Здесь применяется алгоритм в ширину.
     * Из очереди последовательно берется File. Если это директория, то в очередь кладутся внутренние подпапки и файлы.
     * Если не директория, то файл сравнивается с fileName, и, если есть разница, то файл кладется в список.
     * @return экземпляр класса Consumer.
     */
    public Consumer<String> exclude() {
        return fileName -> {
            final Queue<File> queue = new LinkedList<>();
            queue.offer(path);
            while (!queue.isEmpty()) {
                final File file = queue.poll();
                if (!file.isDirectory()) {
                    if (!file.getName().equals(fileName)) {
                        files.add(file);
                    }
                } else {
                    for (File f : file.listFiles()) {
                        queue.offer(f);
                    }
                }
            }
        };
    }

    /**
     * @return экземпляр класса Consumer.
     */
    public Consumer<String> output() {
        return projectName -> {
            try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(projectName))) {
                for (File f : this.files) {
                    zout.putNextEntry(new ZipEntry(f.getPath().substring(f.getPath().indexOf(path.getName()))));
                    try (InputStream is = new FileInputStream(f)) {
                        int value;
                        while ((value = is.read()) != -1) {
                            zout.write(value);
                        }
                    }
                    zout.closeEntry();
                }
            } catch (IOException e) {
                LOGGER.warn("Ошибка в методе output(): " + e);
            }
        };
    }

    /**
     * @param param строковый символ.
     */
    private void parseArgs(final String param) {
        for (int i = 0; i < this.args.length; i++) {
            if (this.args[i].equals(param)) {
                methods.get(this.args[i]).accept(this.args[++i]);
                break;
            }
        }
    }

    /**
     * @param args массив строк.
     */
    public static void main(String[] args) {
        new Args(args);
    }
}