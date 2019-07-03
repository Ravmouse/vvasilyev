package ru.job4j.h6tdd;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Vitaly Vasilyev, date: 01.07.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class SimpleGenerator implements Template {
    /**
     * Паттерн с регулярным выражением.
     */
    private static final Pattern KEYS = Pattern.compile("[${].+?}", Pattern.CASE_INSENSITIVE);

    /**
     * TreeSet нужен для добавления найденных в data значений. Потом осуществляется сравнивание размера сета и карты.
     * Если размеры не равны, то - исключение.
     * Нужно, чтобы не удалять из карты найденные ключи-значения, т.к. шаблон для поиска может повторяться в template.
     *
     * @param template строка-шаблон.
     * @param data карта с ключами и значениями.
     * @return строку из строки template, в которой искомые элементы заменяются значениями из карты data.
     */
    @Override
    public String generate(String template, final Map<String, String> data) {
        String result = template;
        final Set<String> values = new TreeSet<>();
        if (data.isEmpty()) {
            throw new RuntimeException("Не найдено ни одной пары ключ-значение");
        }
        final Matcher matcher = KEYS.matcher(template);

        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String target = template.substring(start, end);
            String key = template.substring(start + 2, end - 1);
            if (!data.containsKey(key)) {
                throw new RuntimeException("В карте нет ключа, идентичного значению найденного шаблона");
            }
            result = result.replace(target, data.get(key));
            values.add(data.get(key));
        }
        if (data.values().size() != values.size()) {
            throw new RuntimeException("В карте присутствуют лишние ключи и значения");
        }
        return result;
    }
}