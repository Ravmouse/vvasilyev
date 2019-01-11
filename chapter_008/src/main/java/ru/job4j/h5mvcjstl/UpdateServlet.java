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
 * Сервлет обновления существующих полей в БД.
 */
public class UpdateServlet extends UserServlet {
    /**
     * Логгер.
     */
    public static final Logger LOGGER = Logger.getLogger(Utils.getNameOfTheClass());

    /**
     * При вводе http://localhost:8080/chapter_008/update попадаем на экран с невозможностью обновлять что-либо.
     * @param req запрос.
     * @param res ответ.
     * @throws IOException исключение.
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.getRequestDispatcher("/WEB-INF/views/notupdate.jsp").forward(req, res);
    }

    /**
     * Этот метод - для входа в экран изменения существующего пользователя. Переход с adminList.jsp на update.jsp.
     * @param req запрос.
     * @param res ответ.
     * @throws IOException исключение.
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        final Role loginRole = (Role) req.getSession().getAttribute("role"); //Получить атрибут из сессии.
        req.setAttribute("loginRole", loginRole); //Установить атрибут в запросе.
        req.setAttribute("user", ValidateService.getInstance().findById(Integer.parseInt(req.getParameter("id"))));
        req.setAttribute("roles", ValidateService.getInstance().findAllRoles());
        req.getRequestDispatcher("/WEB-INF/views/updBootstrap.jsp").forward(req, res);
    }
}