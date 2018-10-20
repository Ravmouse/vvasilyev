package ru.job4j.h1structclientservapp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Rav, date: 02.10.2018
 * @version 1.0
 */
public class EchoServlet extends HttpServlet {
    /**
     * @param req req.
     * @param res res.
     * @throws IOException exc.
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        String tmp = req.getParameter("action");
        PrintWriter writer = new PrintWriter(res.getOutputStream());
        writer.append(tmp);
        writer.flush();
    }
}