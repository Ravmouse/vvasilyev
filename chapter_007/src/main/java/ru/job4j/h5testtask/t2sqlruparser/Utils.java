package ru.job4j.h5testtask.t2sqlruparser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Rav, date: 18.09.2018
 * @version 1.0
 */
class Utils {
    /**
     * @param name имя ресурса.
     * @return путь до ресурса в строковом представлении без слова "file".
     */
    static String getResourcePath(String name) {
        String temp = DataBaseConnection.class.getResource(name).toExternalForm();
        return temp.substring(temp.indexOf("C"), temp.length());
    }

    /**
     * @return имя класса, из которого был выполнен метод этого класса.
     */
    static String getNameOfTheClass() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException re) {
            return re.getStackTrace()[1].getClassName();
        }
    }

    /**
     * @param name имя файла настроек.
     * @return ссылку на Properties.
     */
    static Properties createAndLoadProp(String name) {
        Properties result = new Properties();
        try (final InputStream is = new FileInputStream(getResourcePath(name))) {
            result.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}