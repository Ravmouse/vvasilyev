package ru.job4j.h8htmlcssjs;

import org.apache.log4j.Logger;
import ru.job4j.utils.Utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vitaly Vasilyev, date: 04.01.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class CountryAndCityController extends HttpServlet {
    /**
     * Для хранения JSON-данных.
     */
    private final StringBuilder data = new StringBuilder();
    /**
     * Логгер.
     */
    public static final Logger LOGGER = Logger.getLogger(Utils.getNameOfTheClass());

    /**
     * Данные из файла country.json кэшируются в StringBuilder'е при создании сервлета.
     * @param sc servletConfig.
     * @throws ServletException искл.
     */
    @Override
    public void init(ServletConfig sc) throws ServletException {
        try (final BufferedReader br = new BufferedReader(new FileReader(Utils.getResourcePath("ru/job4j/h8htmlcssjs/country.json")))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.append(line);
            }
        } catch (IOException io) {
            LOGGER.warn("Exception in CountryAndCityController class", io);
        }
    }

    /**
     * @param request запрос.
     * @param response ответ.
     * @throws IOException искл.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/json");
        try (final PrintWriter writer = new PrintWriter(response.getOutputStream())) {
            writer.append(data);
            writer.flush();
        }
    }
}