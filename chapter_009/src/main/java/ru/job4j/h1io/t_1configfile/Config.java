package ru.job4j.h1io.t_1configfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vitaly Vasilyev, date: 02.04.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Config {
    /**
     * Путь до файла с настройками.
     */
    private final String path;
    /**
     * Отображение "ключ-значение" файла с настройками.
     */
    private final Map<String, String> values = new HashMap<>();

    /**
     * @param path путь до файла с настройками.
     */
    public Config(final String path) {
        this.path = path;
    }

    /**
     * @throws IOException искл.
     */
    public void load() throws IOException {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(s -> !s.startsWith("##"))
                    .filter(s -> !s.isEmpty())
                    .forEach(s -> values.put(s.substring(0, s.lastIndexOf("="))
                                           , s.substring(s.indexOf("=") + 1)));
        }
    }

    /**
     * @param key ключ для получения значения.
     * @return значение из отображения.
     */
    public String value(String key) {
        final String value = this.values.get(key);
        if (value == null) {
            throw new RuntimeException("Не удается найти значение по ключу " + key);
        }
        return value;
    }
}