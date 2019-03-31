package ru.job4j.h3testtask;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

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
     * Список ключей.
     */
    private final List<String> keys = Arrays.asList("-d", "-n", "-m", "-f", "-o");
    /**
     * Консьюмеры для валидации экземпляра класса File.
     */
    private final List<Consumer<File>> checkers = Arrays.asList(
        file -> {
            if (!file.exists()) {
                throw new RuntimeException("Директория не существует!");
            }
        },
        file -> {
            if (!file.isDirectory()) {
                throw new RuntimeException("Это - файл, а не директория!");
            }
        }
    );

    /**
     * @param args массив строк - аргументы.
     */
    public Validator(String[] args) {
        this.args = args;
    }

    /**
     * Проверка количества аргументов и того, чтобы один из ключей аргументов присутствовал в списке keys.
     * Порядок нахождения ключей теперь не играет роли.
     */
    public void validate() {
        if (this.args == null || this.args.length != 7) {
            throw new RuntimeException("Неверное количество аргументов!");
        }
        Arrays.stream(this.args).filter(s -> s.startsWith("-")).forEach(s -> {
            int count = 0;
            for (String key : this.keys) {
                if (s.equals(key)) {
                    break;
                } else {
                    count++;
                }
            }
            if (count == this.keys.size()) {
                throw new RuntimeException("Неверный(-е) ключ(-и) среди аргументов!");
            }
        });
    }

    /**
     * Проверка того, что путь у ключа -d существует и является директорией.
     */
    public void directory() {
        final File path = new File(this.args[1]);
        this.checkers.forEach(checker -> checker.accept(path));
    }
}