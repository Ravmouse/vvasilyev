package ru.job4j.h6tdd;

import java.util.Map;

/**
 * Интерфейс с методом получения строки с использованием регулярных выражений.
 */
public interface Template {
    /**
     * @param template строка-шаблон.
     * @param data карта с ключами и значениями.
     * @return строку из строки template, в которой искомые элементы заменяются значениями из карты data.
     */
    String generate(String template, Map<String, String> data);
}