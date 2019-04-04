//package ru.job4j.h4jsp;
//
//import ru.job4j.h2http.UserServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// *
// */
//public class CreateServlet extends UserServlet {
//    /**
//     * Этот метод - для захода на страницу добавления пользователя, введя http://localhost:8080/chapter_008/create.
//     * Перенаправляет на create.jsp.
//     * @param req запрос.
//     * @param res ответ.
//     * @throws IOException исключение.
//     */
//    @Override
//    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        res.setContentType("text/html");
//        res.sendRedirect(String.format("%s/create.jsp", req.getContextPath()));
//    }
//
//    /**
//     * Этот метод - для добавления нового пользователя на странице create.jsp. Переход с adminList.jsp на create.jsp.
//     * После дабавления нового пользователя остаемся на этой же странице create.jsp.
//     * @param req запрос.
//     * @param res ответ.
//     * @throws IOException исключение.
//     */
//    @Override
//    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        super.doPost(req, res);
//        res.sendRedirect(String.format("%s/create.jsp", req.getContextPath()));
//    }
//}