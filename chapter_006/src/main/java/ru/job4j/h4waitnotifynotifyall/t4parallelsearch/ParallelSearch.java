package ru.job4j.h4waitnotifynotifyall.t4parallelsearch;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Здесь создаются и запускаются два потока, один из которых в директории ищет файлы по расширениям, а другой - ищет
 * определенное слово в этих файлах.
 */
@ThreadSafe
public class ParallelSearch {
    /**
     * Путь до директории.
     */
    private final String root;
    /**
     * Искомая строка.
     */
    private final String text;
    /**
     * Список расширений.
     */
    private final List<String> exts;
    /**
     * Очередь файлов, которые соответствуют расширениям.
     */
    @GuardedBy("this")
    private final Queue<Path> files = new LinkedList<>();
    /**
     * Список путей до файлов, в которых есть искомая строка.
     */
    @GuardedBy("this")
    private final List<String> paths = new ArrayList<>();
    /**
     * Переменная, которая сигнализирует о том, что 1-й поток закончил работу по добавлению файлов в очередь.
     */
    private volatile boolean finish;

    /**
     * @param root - путь до директории.
     * @param text - текст, который нужно найти в содержимом файла.
     * @param exts - список расширений.
     */
    public ParallelSearch(final String root, final String text, final List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    /**
     * Создает и запускает два потока.
     * @throws InterruptedException в случае возникновения исключения.
     */
    public void init() throws InterruptedException {
        Thread search = new Thread(() -> {
            try {
                Files.walkFileTree(Paths.get(root), new DirTraversal());
                synchronized (files) { //Синхронизация. Когда 1-й поток выходит из walkFileTree() и меняет finish на
                    finish = true;     //true, а 2-й поток находится в wait(), т.к. он освободил очередь чуть ранее.
                    files.notifyAll(); //Из-за этого приложение раньше "висло".
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Thread read = new Thread(() -> check());
        search.start();
        read.start();
        search.join(); //join'ы поместил для того, чтобы main-поток после окончания работы двух потоков
        read.join();   //отобразил строки из paths.
    }

    /**
     * Внутренний класс, который расширяет SimpleFileVisitor.
     */
    private class DirTraversal extends SimpleFileVisitor<Path> {
        /**
         * @param file файл.
         * @param attrs атрибуты файла.
         * @return если есть еще файлы в поддиректориях, то продолжает перебирать следующие файлы.
         */
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            for (String extension : exts) {
                if (file.getFileName().toString().endsWith(extension)) {
                    synchronized (files) { //Синхронизация по Queue<Path> files.
                        files.offer(file);
                        files.notifyAll(); //После того, как положил в очередь, нужно известить об этом 2-й поток.
                    }
                }
            }
            return CONTINUE;
        }
    }

    /**
     * В бесконечном цикле проверяет очередь на пустоту и запускает метод для извлечения файлов.
     */
    private void check() {
        synchronized (files) {
            while (true) {
                while (files.isEmpty() && !finish) { //Если очередь пуста и 1-й поток еще на закончил работу, то
                    try {                            //нужно ждать, т.к. 1-й поток еще может поместить что-то в
                        files.wait();                //очередь и после каждого размещения 1-й поток уведомляет об этом.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (!files.isEmpty()) {      //Если очередь не пуста,то независимо от того,завершил 1-й поток работу
                    pollFileAndSearchText(); //или нет (finish = true or false), нужно забирать из очереди по файлу.
                } else {   //Сюда попадаем только в том случае, если очередь пуста и 1-й поток завершил свою работу,
                    break; //т.е. finish = true.
                }
            }
        }
    }

    /**
     * Вытаскивает файл из очереди, помещает его в символьный поток чтения и ищет искомую строку.
     * Если находит, то добавляет путь до этого файла в список paths.
     */
    private void pollFileAndSearchText() {
        Path file = files.poll();
        try (final BufferedReader br = new BufferedReader(new FileReader(file.toString()))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(text)) {
                    paths.add(file.toString());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return возвращает список путей файлов, где было найдено искомое слово.
     */
    public synchronized List<String> result() {
        return paths;
    }

    /**
     * Отображает пути до файлов, в которых была найдена искомая строка.
     */
    public void show() {
        for (String str : paths) {
            System.out.println(str);
        }
    }

    /**
     * @param args .
     * @throws IOException .
     * @throws InterruptedException .
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        String root = "C:\\projects\\vvasilyev\\chapter_006";
        String text = "Visitor";
        List<String> exts = Arrays.asList("java");
        ParallelSearch pSearch = new ParallelSearch(root, text, exts);
        pSearch.init();
        pSearch.show();
    }
}