package ru.job4j.utils;

import java.time.LocalDate;

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
     * @param createDate начальная дата.
     * @param expiryDate конечная дата.
     * @return количество дней между двумя датами.
     */
    public static int daysBetween(final LocalDate createDate, final LocalDate expiryDate) {
        int result = 0;
        int startYear = createDate.getYear();
        int finishYear = expiryDate.getYear();
        int startMonth = createDate.getMonthValue();
        int finishMonth = expiryDate.getMonthValue();

        //Если год начала и год конца совпадает, а также совпадают месяц начала и конца, то рассчитывается здесь.
        if (startYear == finishYear && startMonth == finishMonth) {
            int f = daysInMonth(startMonth, startYear) - expiryDate.getDayOfMonth();
            int s = daysInMonth(startMonth, startYear) - createDate.getDayOfMonth();
            return s - f + 1;
        }

        int mS = startYear == finishYear ? startMonth : 1;
        int mF = startYear == finishYear ? finishMonth : 12;

        //Если год начала и конца совпадает, но месяцы разные, то перебираются месяцы только начала и конца.
        //Если год начала и конца не совпадает, то перебираются все 12 месяцев, но в годе начала (конца) проход
        //осуществляется только от (до) определенного месяца. В остальные года - все 12 мес.
        for (int i = startYear; i <= finishYear; i++) {
            for (int j = mS; j <= mF; j++) {
                if (i == startYear && j == startMonth) {
                    result += daysInMonth(j, i) - createDate.getDayOfMonth() + 1;
                } else if (i == finishYear && j == finishMonth) {
                    result += expiryDate.getDayOfMonth();
                } else {
                    if ((i > startYear && i < finishYear) || (i == startYear && j > startMonth) || (i == finishYear && j < finishMonth)) {
                        result += daysInMonth(j, i);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Учитывается также, является ли год високосным или нет.
     * @param month месяц.
     * @param year год.
     * @return количество дней в месяце года.
     */
    private static int daysInMonth(int month, int year) {
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return  30;
        } else {
            return 31;
        }
    }

    /**
     * @param year год.
     * @return true, если год является високосным, и false, если - нет.
     */
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 > 0) || (year % 400 == 0);
    }
}