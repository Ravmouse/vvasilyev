package ru.job4j.utils;

import org.apache.log4j.Logger;
import ru.job4j.robot.Step;

/**
 * Утилиты.
 */
public class Utils {
    /**
     * Логгер.
     */
    private static final Logger LOG = Logger.getLogger(getNameOfTheClass());

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
     * @param step шаг.
     */
    public static void arrow(final Step step) {
        if (step.getX() == -1) {
            LOG.info("\u2190");
        }
        if (step.getX() == 1) {
            LOG.info("\u2192");
        }
        if (step.getY() == -1) {
            LOG.info("\u2191");
        }
        if (step.getY() == 1) {
            LOG.info("\u2193");
        }
    }
}