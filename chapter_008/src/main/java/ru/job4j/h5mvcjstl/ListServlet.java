package ru.job4j.h5mvcjstl;

import org.apache.log4j.Logger;
import ru.job4j.h2http.UserServlet;
import ru.job4j.h2http.ValidateService;
import ru.job4j.h6filter.Role;
import ru.job4j.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет добавления и отображения существующих полей в БД.
 */
public class ListServlet extends UserServlet {
    /**
     * Логгер.
     */
    public static final Logger LOGGER = Logger.getLogger(Utils.getNameOfTheClass());

    /**
     * Этот метод - для захода на страницу просмотра всех пользователей, введя http://localhost:8080/chapter_008/.
     * @param req запрос.
     * @param res ответ.
     * @throws IOException исключение.
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setAttribute("users", ValidateService.getInstance().findAll());
        final Role role = (Role) req.getSession().getAttribute("role");
        if (role.getName().equals("User")) {
            req.setAttribute("login", req.getSession().getAttribute("login"));
            req.getRequestDispatcher("/WEB-INF/views/userList.jsp").forward(req, res);
        } else {
            req.setAttribute("roles", ValidateService.getInstance().findAllRoles());
            req.getRequestDispatcher("/WEB-INF/views/adminList.jsp").forward(req, res);
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
            LOGGER.warn("Exception in ListServlet class", e);
        }
    }
}