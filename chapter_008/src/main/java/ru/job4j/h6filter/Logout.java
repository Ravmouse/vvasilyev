package ru.job4j.h6filter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Vitaly Vasilyev, date: 09.12.2018, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Logout extends HttpServlet {
    /**
     * @param req запрос.
     * @param res ответ.
     * @throws IOException исключение.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.sendRedirect(String.format("%s", req.getContextPath()));
    }

    /**
     * @param req запрос.
     * @param res ответ.
     * @throws IOException исключение.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        final HttpSession session = req.getSession();
        session.invalidate();
        res.sendRedirect(String.format("%s/login", req.getContextPath()));
    }
}