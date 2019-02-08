package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.model.Account;
import ru.job4j.service.Validate;
import ru.job4j.service.ValidateService;
import ru.job4j.utils.Utils;
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
        final Account account = Utils.createGenObjFromReqWithJxn(request, Account.class);
        int rsl = SERVICE.add(account);
        response.getWriter().write("{\"result\":" + rsl + "}");
    }
}