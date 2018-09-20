package ru.job4j.h5testtask.t2sqlruparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

/**
 * В классе осуществляется соединение со страницей sql.ru и получение информации.
 */
public class HTMLPageStore {
    /**
     * Отображение вакансий типа "название_вакансии" - "время_публикации".
     */
    private final Map<String, String> jobMap;
    /**
     * @param jobMap  ссылка на объект, куда нужно будет сохранять данные по вакансиям.
     */
    public HTMLPageStore(Map<String, String> jobMap) {
        this.jobMap = jobMap;
    }

    /**
     * @param period "Y" или "D" - год или день.
     * @throws IOException в случае возникновения исключения.
     */
    public void connectAndGetOffer(String period) throws IOException {
        System.out.println("========================================================================");
        String date = null, formattedDate = null; //formattedDate - для того, чтобы сконвертировать дату в формат для
        int page = 1;                             //сравнения, но в Map положить "обычную" date.
        do {
            final Document doc = Jsoup.connect("http://www.sql.ru/forum/job-offers/" + page++).get();
            final Elements elements = doc.getElementsByTag("tr");
            //Нужны элементы с тегом "tr" только с 8-го по size - 2.
            for (int i = 7; i < elements.size() - 2; i++) {
                String title = elements.get(i).getElementsByTag("a").get(0).wholeText(); //С тегом "а" нужен только 1-ый элемент.
                if (title.toLowerCase().contains("Java".toLowerCase())
                        && (!title.contains("Java Script") && !title.toLowerCase().contains("JavaScript".toLowerCase()))) {
                    System.out.println(title);
                    //С атрибутом тега "style" нужен последний элемент, т.е. size - 1.
                    date = elements.get(i).getElementsByAttribute("style").get(elements.get(i).getElementsByAttribute("style").size() - 1).wholeText();
                    System.out.println(date);
                    formattedDate = convertFromLettersToDigits(date);
                    System.out.println(formattedDate);
                    jobMap.put(title, date); //Положить в Map название вакансии и время публикации.
                }
            }
            System.out.println("-----------------------------------");
        } while ((date == null) || period.equals("Y") ? !isPeriodPassed(formattedDate, "Y") : !isPeriodPassed(formattedDate, "D"));
    }

    /**
     * @param value строковое представление даты, которое нужно преобразовать в формат "ГГГГ-ММ-ДД".
     * @return результат преобразования.
     */
    private static String convertFromLettersToDigits(String value) {
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
    private static boolean isPeriodPassed(String currDate, String period) {
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