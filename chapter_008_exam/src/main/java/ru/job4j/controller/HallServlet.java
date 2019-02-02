package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.model.Account;
import ru.job4j.service.Validate;
import ru.job4j.service.ValidateService;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vitaly Vasilyev, date: 16.01.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class HallServlet  extends HttpServlet {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final String seats = mapper.writeValueAsString(SERVICE.selectAll());
        response.getWriter().write(seats);
    }

    /**
     * @param request запрос.
     * @param response ответ.
     * @throws ServletException искл.
     * @throws IOException искл.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SERVICE.add(parseRequestedString(request));
    }

    /**
     * @param request запрос, из которого нужно получить параметры.
     * @return экземпляр класса Account.
     */
    private Account parseRequestedString(HttpServletRequest request) {
        Account rsl;
        String line = "";
        try (final BufferedReader reader = request.getReader()) {
            line = reader.readLine();
        } catch (final IOException io) {
            io.printStackTrace();
        }
        final String[] array = line.split("&");
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].substring(array[i].indexOf("=") + 1);
        }
        rsl = new Account(array[0], array[1], array[2], array[3], SERVICE.getSeats().get(Integer.parseInt(array[4])));
        return rsl;
    }
}