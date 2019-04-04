//package ru.job4j.h3servlet;
//
//import ru.job4j.h2http.User;
//import ru.job4j.h2http.UserServlet;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * Вкладка /list.
// * @author Vitaly Vasilyev, date: 23.10.2018, e-mail: rav.energ@rambler.ru
// * @version 1.0
// */
//public class UsersServlet extends UserServlet {
//    /**
//     * При нажатии на кноку Edit осуществляется переход на страницу /edit с id = id user'a и там вызывается метод doPost,
//     * из которого сразу вызывается метод doGet. Это - для того, чтобы в doGet() передать request и значение параметра id.
//     * При нажатии на кнопку Delete остаемся на этой же странице /list.
//     * @param req запрос.
//     * @param res ответ.
//     * @throws IOException исключение.
//     */
//    @Override
//    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        res.setContentType("text/html");
//        final PrintWriter writer = new PrintWriter(res.getOutputStream());
//        final StringBuilder sb = new StringBuilder();
//        for (User user : LOGIC.findAll()) {
//            sb.append("<tr>"
//                            + "<td>" + user.toString() + "</td>"
//                            + "<td>"
//                                + "<form action='" + req.getContextPath() + "/edit?id=" + user.getId() + "' method='POST'>"
//                                        + "<input type='submit' value='Edit'/>" + " "
//                                + "</form>"
//                            + "</td>"
//                            + "<td>"
//                                + "<form action='" + req.getContextPath() + "/list?action=delete&id=" + user.getId() + "' method='POST'>"
//                                        + "<input type='submit' value='Delete'/>"
//                                + "</form>"
//                            + "</td>"
//                        + "</tr>"
//            );
//        }
//        writer.append("<!DOCTYPE html>"
//                        + "<html lang=\"en\">"
//                            + "<head>"
//                                + "<meta charset='UTF-8'>" + "<title>All Users</title>"
//                            + "</head>"
//                            + "<body>"
//                                + "<h2 align='center'>All users</h2>"
//                                + "<table align='center'>"
//                                + sb.toString()
//                                + "<tr><td align='center'>"
//                                    + "<form action='" + req.getContextPath() + "/create'>"
//                                        + "<input type='submit' value='Create a new user'/>"
//                                    + "</form>"
//                                + "</td></tr>"
//                                + "</table>"
//                            + "</body>"
//                        + "</html>");
//        writer.flush();
//    }
//
//    /**
//     * @param req запрос.
//     * @param res ответ.
//     * @throws IOException исключение.
//     */
//    @Override
//    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//        super.doPost(req, res);
//        doGet(req, res);
//    }
//}