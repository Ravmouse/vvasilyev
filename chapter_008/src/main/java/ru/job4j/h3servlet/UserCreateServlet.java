//package ru.job4j.h3servlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import ru.job4j.h2http.UserServlet;
//
///**
// * Вкладка /create.
// * @author Vitaly Vasilyev, date: 23.10.2018, e-mail: rav.energ@rambler.ru
// * @version 1.0
// */
//public class UserCreateServlet extends UserServlet {
//    /**
//     * При нажатии на кнопку Add остаемся на этой же странице /create, но новый user формируется.
//     * @param req запрос.
//     * @param res ответ.
//     * @throws IOException исключение.
//     */
//    @Override
//    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        res.setContentType("text/html");
//        PrintWriter pw = new PrintWriter(res.getOutputStream());
//        pw.append("<!DOCTYPE html>"
//                        + "<html lang=\"en\">"
//                            + "<head>"
//                                + "<meta charset='UTF-8'>" + "<title>Create a new user</title>"
//                            + "</head>"
//                            + "<body>"
//                                + "<h2 align='center'>Create a new user</h2>"
//                                + "<table align='center'>"
//                                    + "<form action='" + req.getContextPath() + "/create?action=add' method='POST'>"
//                                        + "<tr>"
//                                            + "<td>1. Name: </td>"
//                                            + "<td>"
//                                                + "<input type='text' name='name'>"
//                                            + "</td>"
//                                        + "</tr>"
//                                        + "<tr>"
//                                            + "<td>2. Login: </td>"
//                                            + "<td>"
//                                                + "<input type='text' name='login'>"
//                                            + "</td>"
//                                        + "</tr>"
//                                        + "<tr>"
//                                            + "<td>3. E-mail: </td>"
//                                            + "<td>"
//                                                + "<input type='text' name='email'>"
//                                            + "</td>"
//                                        + "</tr>"
//                                        + "<tr>"
//                                            + "<td>4. Date of creation: </td>"
//                                            + "<td>"
//                                                + "<input type='text' name='createDate'>"
//                                            + "</td>"
//                                        + "</tr>"
//                                        + "<tr>"
//                                            + "<td></td>"
//                                            + "<td align='center'>"
//                                                + "<input type='submit' value='Add'>" //Добавить нового пользователя.
//                                            + "</td>"
//                                        + "</tr>"
//                                    + "</form>"
//                                    + "<form action='" + req.getContextPath() + "/list'>"
//                                        + "<tr>"
//                                            + "<td></td>"
//                                            + "<td align='center'>"
//                                                + "<input type='submit' value='Show all users'>" //Показать всех пользователей.
//                                            + "</td>"
//                                        + "</tr>"
//                                    + "</form>"
//                                + "</table>"
//                            + "</body>"
//                        + "</html>");
//        pw.flush();
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