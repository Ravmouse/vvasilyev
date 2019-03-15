package ru.job4j.h1io.t4archiveproj;

import org.apache.log4j.Logger;
import ru.job4j.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.*;

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
     * Имя zip-архива.
     */
    private String output;

    /**
     * @param args массив строк.
     */
    public Args(String[] args) {
        if (args == null || args.length != 6) {
            LOGGER.warn("Неверное количество аргументов!");
        } else {
            this.args = args;
            methods.put("-d", directory());
            methods.put("-i", include());
            methods.put("-o", output());
            parseArgs("-d");
            if (existedDir) {
                parseArgs("-i");
                parseArgs("-o");
            }
        }
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
     * Создается LinkedList, в него кладется File с директорией начала поиска. Затем из очереди последовательно
     * берется File. Если это директория, то в очередь кладутся внутренние подпапки и файлы этого File.
     * Если не директория, то выясняется, начинается ли файл с "*" (все файлы) или имеет свое имя.
     * Если *, то в список кладутся все файлы с таким расширением.
     * Если есть имя, то ищется только определенный файл.
     * @return экземпляр класса Consumer.
     */
    public Consumer<String> include() {
        return fileName -> {
            final Queue<File> queue = new LinkedList<>();
            queue.offer(path);
            while (!queue.isEmpty()) {
                final File file = queue.poll();
                if (!file.isDirectory()) {
                    if (fileName.startsWith("*")) {
                        addByExt(fileName, file, files);
                    } else {
                        addByName(fileName, file, files);
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
     * @param s имя файла в виде *.расширение.
     * @param file экземпляр класса File, который представляет собой только файлы, но не директории.
     * @param list список.
     */
    private void addByExt(String s, File file, List<File> list) {
        final String ext = s.substring(s.lastIndexOf(".") + 1);
        if (file.getName().substring(file.getName().lastIndexOf(".") + 1).equals(ext)) {
            list.add(file);
        }
    }

    /**
     * @param s имя файла в виде имя.расширение.
     * @param file экземпляр класса File, который представляет собой только файлы, но не директории.
     * @param list список.
     */
    private void addByName(String s, File file, List<File> list) {
        if (file.getName().equals(s)) {
            list.add(file);
        }
    }

    /**
     * @return экземпляр класса Consumer.
     */
    public Consumer<String> output() {
        return projectName -> this.output = projectName;
    }

    /**
     * @param args аргументы.
     * @throws IOException искл.
     */
    public static void main(String[] args) throws IOException {
        Args a = new Args(args);
        new Zip().output(a.output, a.path, a.files);
    }
}