package ru.job4j.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Утилиты.
 */
public class Utils {
    /**
     * Находится файл-ресурс и от начала его пути удаляется строка "file:/".
     * @param fileName имя файла-ресурса, которое должно начинаться с ru/job4j/h6filter/ или ru/job4j/h8htmlcssjs/ и т.д.
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
     * Этот метод для параметров запроса, которые должны быть преобразованы к JSON типу, например, после
     * применения метода JSON.stringify() в html-файле. ObjectMapper создает объект обобщенного типа.
     * @param request запрос.
     * @param clazz класс объекта, экземпляр которого нужно создать.
     * @param <T> обобщенный класс.
     * @return экземпляр объекта.
     * @throws IOException исключение.
     */
    public static <T> T createGenObjFromReqWithJxn(HttpServletRequest request, Class<T> clazz) throws IOException {
        String line;
        try (final BufferedReader br = request.getReader()) {
            line = br.readLine();
        }
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(line, clazz);
    }
}