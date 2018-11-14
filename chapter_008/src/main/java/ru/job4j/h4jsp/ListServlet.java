package ru.job4j.h4jsp;

import ru.job4j.h2http.UserServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public class ListServlet extends UserServlet {
    /**
     * Этот метод - для захода на страницу просмотра всех пользователей, введя http://localhost:8080/chapter_008/list.
     * Перенаправляет на list.jsp.
     * Также, если мы переходим со страницы create.jsp.
     * @param req запрос.
     * @param res ответ.
     * @throws IOException исключение.
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        res.sendRedirect(String.format("%s/list.jsp", req.getContextPath()));
    }

    /**
     * Этот метод - для изменения существующего пользователя. Переход с update.jsp на list.jsp.
     * Также для удаления существующего пользователя на этой же странице list.jsp.
     * @param req запрос.
     * @param res ответ.
     * @throws IOException исключение.
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        super.doPost(req, res);
        res.sendRedirect(String.format("%s/list.jsp", req.getContextPath()));
    }
}