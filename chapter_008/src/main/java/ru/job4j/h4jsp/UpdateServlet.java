//package ru.job4j.h4jsp;
//
//import org.apache.log4j.Logger;
//import ru.job4j.h2http.User;
//import ru.job4j.h2http.UserServlet;
//import ru.job4j.utils.Utils;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// *
// */
//public class UpdateServlet extends UserServlet {
//    /**
//     * Логгер.
//     */
//    public static final Logger LOGGER = Logger.getLogger(Utils.getNameOfTheClass());
//
//    /**
//     * При вводе http://localhost:8080/chapter_008/update попадаем на пустой экран, не происходит перенапрвления на
//     * update.jsp, т.к. обновлять пользователя можно только при наличии такого на странице adminList.jsp
//     * @param req запрос.
//     * @param res ответ.
//     * @throws IOException исключение.
//     */
//    @Override
//    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
//
//    }
//
//    /**
//     * Этот метод - для входа в экран изменения существующего пользователя. Переход с adminList.jsp на update.jsp.
//     * @param req запрос.
//     * @param res ответ.
//     * @throws IOException исключение.
//     */
//    @Override
//    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        res.setContentType("text/html");
//        final User user = LOGIC.findById(Integer.parseInt(req.getParameter("id")));
//        req.setAttribute("user", user);
//        try {
//            req.getRequestDispatcher("update.jsp").forward(req, res);
//        } catch (ServletException e) {
//            LOGGER.warn("Exception in UpdateServlet class", e);
//        }
//        res.sendRedirect(String.format("%s/update.jsp", req.getContextPath()));
//    }
//}