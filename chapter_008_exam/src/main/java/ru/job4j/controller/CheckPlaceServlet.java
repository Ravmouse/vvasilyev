package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.service.Validate;
import ru.job4j.service.ValidateService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Vitaly Vasilyev, date: 31.01.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class CheckPlaceServlet extends HttpServlet {
    /**
     * Ссылка на экземпляр класса, реализующего интерфейс Validate.
     */
    private static final Validate SERVICE = ValidateService.getInstance();

    /**
     * @param request запрос.
     * @param response ответ.
     * @throws ServletException искл.
     * @throws IOException искл.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        int number = getNumber(request);
        final String str = mapper.writeValueAsString(SERVICE.checkSeat(number));
        response.getWriter().write(str);
    }

    /**
     * @param request запрос.
     * @return номер места.
     */
    private int getNumber(HttpServletRequest request) {
        String line = "";
        try (final BufferedReader reader = request.getReader()) {
            line = reader.readLine();
        } catch (final IOException io) {
            io.printStackTrace();
        }
        return Integer.parseInt(line.substring(line.indexOf("=") + 1));
    }
}