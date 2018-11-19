package ru.job4j.h5mvcjstl;

import ru.job4j.h2http.User;
import ru.job4j.h2http.UserServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет обновления существующих полей в БД.
 */
public class UpdateServlet extends UserServlet {
    /**
     * При вводе http://localhost:8080/chapter_008/update попадаем на экран с невозможностью обновлять что-либо.
     * @param req запрос.
     * @param res ответ.
     * @throws IOException исключение.
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            req.getRequestDispatcher("/WEB-INF/views/notupdate.jsp").forward(req, res);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * Этот метод - для входа в экран изменения существующего пользователя. Переход с list.jsp на update.jsp.
     * @param req запрос.
     * @param res ответ.
     * @throws IOException исключение.
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        final User user = LOGIC.findById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("user", user);
        try {
            req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, res);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}