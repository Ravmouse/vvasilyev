package ru.job4j.utils;

import ru.job4j.h6filter.Role;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Утилиты.
 */
public class Utils {
    /**
     * Находится файл-ресурс и от начала его пути удаляется строка "file:/".
     * @param fileName имя файла-ресурса.
     * @return путь до файла-ресурса.
     */
    public static String getResourcePath(final String fileName) {
        final String temp = Utils.class.getClassLoader().getResource(fileName).toExternalForm();
        return temp.substring(temp.indexOf("C"), temp.length());
    }

    /**
     * Создается экземпляр класса Properties, находится файл .properties, который потом загружается в
     * экземпляр класса Properties.
     * @param fileName имя файла-ресурса.
     * @return ссылка на экземпляр класса Properties.
     */
    public static Properties createAndLoadProp(final String fileName) {
        final Properties result = new Properties();
        try (final InputStream is = new FileInputStream(getResourcePath(fileName))) {
            result.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Выкидывается исключение и, ввиду того, что этот метод должен запускаться из другого класса, получается имя
     * класса, из которого был вызван данный метод.
     * @return имя класса в виде строки, из которого был выполнен этот метод.
     */
    public static String getNameOfTheClass() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException re) {
            return re.getStackTrace()[1].getClassName();
        }
    }

    /**
     * Строка разбивается на массив строк при помощи метода split(), далее в цикле ищется ключ, который находится до
     * знака '=', и значение, которое расположено после знака '='. После чего все это помещается в Map.
     * action=add&id=1&name=Mike&login=mister&email=bla@mail.ru&createDate=2018-10-15
     * @param str строка для парсинга.
     * @return хэш-отображение.
     */
    public static Map<String, String> parseString(final String str) {
        Map<String, String> result = new HashMap<>();
        String[] arr = str.split("&");
        System.out.println(Arrays.toString(arr));
        for (String s : arr) {
            result.put(s.substring(0, s.indexOf("=")), s.substring(s.indexOf("=") + 1, s.length()));
        }
        result.remove("action");
        return result;
    }

    /**
     * Декодирование, чтобы был символ @, а не %40.
     * @param value строка для декодирования.
     * @param encoding схема декодирования.
     * @return декодированную строку.
     */
    public static String convert(final String value, final String encoding) {
        String rsl = null;
        try {
            rsl = URLDecoder.decode(value, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    /**
     * @param name .
     * @param collection .
     * @param <E> .
     * @return .
     */
    public static <E extends Role> int indexOf(final String name, final Collection<E> collection) {
        int rsl = -1;
        for (E e : collection) {
            if (e.getName().equals(name)) {
                rsl = e.getId() - 1;
                break;
            }
        }
        return rsl;
    }
}