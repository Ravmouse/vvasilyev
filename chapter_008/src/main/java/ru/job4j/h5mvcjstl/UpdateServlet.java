package ru.job4j.h5mvcjstl;

import ru.job4j.h2http.UserServlet;
import ru.job4j.h2http.ValidateService;
import ru.job4j.h6filter.Role;
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
     * Этот метод - для входа в экран изменения существующего пользователя. Переход с adminList.jsp на update.jsp.
     * @param req запрос.
     * @param res ответ.
     * @throws IOException исключение.
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        final Role loginRole = (Role) req.getSession().getAttribute("role"); //Получить атрибут из сессии.
        req.setAttribute("loginRole", loginRole); //Установить атрибут в запросе.
        req.setAttribute("user", ValidateService.getInstance().findById(Integer.parseInt(req.getParameter("id"))));
        req.setAttribute("roles", ValidateService.getInstance().findAllRoles());
        try {
            req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, res);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}