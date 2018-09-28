package ru.job4j.h5testtask.t2sqlruparser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
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

    /**
     * @param value строковое представление даты, которое нужно преобразовать в формат "ГГГГ-ММ-ДД".
     * @return результат преобразования.
     */
    static String convertFromLettersToDigits(String value) {
        String result;
        if (value.contains("сегодня")) {
            result = LocalDate.now().toString();
        } else if (value.contains("вчера")) {
            result = LocalDate.now().minusDays(1).toString();
        } else {
            int startMonth = value.indexOf(" ") + 1; //Где начинается 1-ая буква слова "МЕС"
            int endMonth = startMonth + 3; //Где заканчивается 3-я буква слова...
            String month = value.substring(startMonth, endMonth);
            switch (month) {
                case "янв": month = "01";
                    break;
                case "фев": month = "02";
                    break;
                case "мар": month = "03";
                    break;
                case "апр": month = "04";
                    break;
                case "май": month = "05";
                    break;
                case "июн": month = "06";
                    break;
                case "июл": month = "07";
                    break;
                case "авг": month = "08";
                    break;
                case "сен": month = "09";
                    break;
                case "окт": month = "10";
                    break;
                case "ноя": month = "11";
                    break;
                default   : month = "12";
                    break;
            }
            String year = value.substring(endMonth + 1, endMonth + 3);
            String day = value.substring(0, value.indexOf(" "));
            if (day.length() == 1) {
                day = String.format("0%s", day); //Если день - это одна цифра, то прибавить к этой цифре ноль.
            }
            result = String.format("20%s-%s-%s", year, month, day);
        }
        return result;
    }

    /**
     * @param currDate дата для отсчета, прошел ли год или день.
     * @param period строка "Y" или "D".
     * @return true или false.
     */
    static boolean isPeriodPassed(String currDate, String period) {
        boolean result = false;
        int currY = Integer.parseInt(currDate.substring(0, 4));
        int currM = Integer.parseInt(currDate.substring(5, 7));
        int currD = Integer.parseInt(currDate.substring(8, 10));
        //Если period равен "Y", то записывается дата год назад, а, если равен "D", то - день назад.
        final String periodDate = period.equals("Y")
                ? LocalDate.now().minusYears(1).toString() : LocalDate.now().minusDays(1).toString();
        int yY = Integer.parseInt(periodDate.substring(0, 4));
        int mM = Integer.parseInt(periodDate.substring(5, 7));
        int dD = Integer.parseInt(periodDate.substring(8, 10));

        if (currY == yY) {
            if (currM == mM && currD <= dD) {
                result = true;
            } else if (currM < mM) {
                result = true;
            }
        } else if (currY < yY) {
            result = true;
        }
        return result;
    }
}