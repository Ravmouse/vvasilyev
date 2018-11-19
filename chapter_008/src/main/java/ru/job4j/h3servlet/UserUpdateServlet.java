package ru.job4j.h3servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.job4j.h2http.User;
import ru.job4j.h2http.UserServlet;

/**
 * Вкладка /edit.
 * @author Vitaly Vasilyev, date: 28.10.2018, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class UserUpdateServlet extends UserServlet {
    /**
     * Из request получается параметр id, который при нажатии на кнопку Update, передается в doPost() страницы /list,
     * а далее - в updateUser() класса UserServlet.
     * @param req запрос.
     * @param res ответ.
     * @throws IOException исключение.
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        final PrintWriter pw = new PrintWriter(res.getOutputStream());
        final String id = req.getParameter("id");
        final User user = LOGIC.findById(Integer.parseInt(id));
        pw.append("<!DOCTYPE html>"
                    + "<html lang=\"en\">"
                        + "<head>"
                            + "<meta charset='UTF-8'>" + "<title>Update the user</title>"
                        + "</head>"
                        + "<body>"
                            + "<h2 align='center'>Update the user</h2>"
                            + "<table align='center'>"
                                + "<form action='" + req.getContextPath() + "/list?action=update&id=" + id + "' method='POST'>"
                                    + "<tr>"
                                        + "<td>1. Name: </td>"
                                        + "<td>"
                                            + "<input type='text' name='name' value='" + user.getName() + "'>"
                                        + "</td>"
                                    + "</tr>"
                                    + "<tr>"
                                        + "<td>2. Login: </td>"
                                        + "<td>"
                                            + "<input type='text' name='login' value='" + user.getLogin() + "'>"
                                        + "</td>"
                                    + "</tr>"
                                    + "<tr>"
                                        + "<td>3. E-mail: </td>"
                                        + "<td>"
                                            + "<input type='text' name='email' value='" + user.getEmail() + "'>"
                                        + "</td>"
                                    + "</tr>"
                                    + "<tr>"
                                        + "<td>4. Date of creation: </td>"
                                        + "<td>"
                                            + "<input type='text' name='createDate' value='" + user.getCreateDate() + "'>"
                                        + "</td>"
                                    + "</tr>"
                                    + "<tr>"
                                        + "<td></td>"
                                        + "<td align='center'>"
                                            + "<input type='submit' value='Update'>"
                                        + "</td>"
                                    + "</tr>"
                                + "</form>"
                            + "</table>"
                        + "</body>"
                    + "</html>");
        pw.flush();
    }

    /**
     * @param req запрос.
     * @param res ответ.
     * @throws IOException исключение.
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doGet(req, res);
    }
}