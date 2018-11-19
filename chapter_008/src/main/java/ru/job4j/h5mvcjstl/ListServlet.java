package ru.job4j.h5mvcjstl;

import ru.job4j.h2http.UserServlet;
import ru.job4j.h2http.ValidateService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет добавления и отображения существующих полей в БД.
 */
public class ListServlet extends UserServlet {
    /**
     * Этот метод - для захода на страницу просмотра всех пользователей, введя http://localhost:8080/chapter_008/.
     * Перенаправляет на list.jsp.
     * @param req запрос.
     * @param res ответ.
     * @throws IOException исключение.
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        req.setAttribute("users", ValidateService.getInstance().findAll());
        try {
            req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, res);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * Этот метод для добавления новых данных в БД, удаления строки с данными из БД и возврата с update.jsp.
     * @param req запрос.
     * @param res ответ.
     * @throws IOException исключение.
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        super.doPost(req, res);
        try {
            if (req.getAttribute("text") == null) {
                doGet(req, res);
            } else if (req.getAttribute("text").equals("EmptyCreate")) {
                req.getRequestDispatcher("/WEB-INF/views/emptyCreate.jsp").forward(req, res);
            } else if (req.getAttribute("text").equals("EmptyUpdate")) {
                req.getRequestDispatcher("/WEB-INF/views/emptyUpdate.jsp").forward(req, res);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}