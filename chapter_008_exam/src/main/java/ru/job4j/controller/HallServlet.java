package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.service.Validate;
import ru.job4j.service.ValidateService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        response.setContentType("text/json");
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
        int number = Integer.parseInt(request.getParameter("place"));
        SERVICE.add(number, parseRequest(request));
        response.sendRedirect(String.format("%s/", request.getContextPath()));
    }

    /**
     * @param request запрос, из которого нужно получить параметры.
     * @return список строк.
     */
    private List<String> parseRequest(HttpServletRequest request) {
        List<String> list = new ArrayList<>();
        list.add(request.getParameter("name"));
        list.add(request.getParameter("surname"));
        list.add(request.getParameter("patron"));
        list.add(request.getParameter("mobile"));
        return list;
    }
}