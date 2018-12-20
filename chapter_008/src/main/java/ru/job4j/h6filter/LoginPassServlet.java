package ru.job4j.h6filter;

import ru.job4j.h2http.ValidateService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Vitaly Vasilyev, date: 27.11.2018, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class LoginPassServlet extends HttpServlet {
    /**
     * @param req запрос.
     * @param res ответ.
     * @throws IOException исключение.
     * @throws ServletException исключение.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, res);
    }

    /**
     * @param req запрос.
     * @param res ответ.
     * @throws ServletException исключение.
     * @throws IOException исключение.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        Role role = ValidateService.getInstance().findRole(login, password);
        if (role != null) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            session.setAttribute("role", role);
            res.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("message", "Wrong login or password!");
            doGet(req, res);
        }
    }
}