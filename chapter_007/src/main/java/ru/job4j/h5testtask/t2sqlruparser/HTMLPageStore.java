package ru.job4j.h5testtask.t2sqlruparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Map;
import static ru.job4j.h5testtask.t2sqlruparser.Utils.convertFromLettersToDigits;
import static ru.job4j.h5testtask.t2sqlruparser.Utils.isPeriodPassed;

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
}