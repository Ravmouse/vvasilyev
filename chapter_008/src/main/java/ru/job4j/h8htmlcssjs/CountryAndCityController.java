package ru.job4j.h8htmlcssjs;

import ru.job4j.utils.Utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vitaly Vasilyev, date: 04.01.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class CountryAndCityController extends HttpServlet {
    /**
     * @param request запрос.
     * @param response ответ.
     * @throws IOException искл.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/json");
        final PrintWriter writer = new PrintWriter(response.getOutputStream());
        try (final BufferedReader br = new BufferedReader(new FileReader(Utils.getResourcePath("ru/job4j/h8htmlcssjs/country.json")))) {
            String line;
            while ((line = br.readLine()) != null) {
                writer.append(line);
            }
        }
        writer.flush();
    }
}