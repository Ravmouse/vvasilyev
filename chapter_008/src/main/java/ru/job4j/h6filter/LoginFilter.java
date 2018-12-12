package ru.job4j.h6filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Vitaly Vasilyev, date: 27.11.2018, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class LoginFilter implements Filter {
    /**
     * @param request запрос.
     * @param response ответ.
     * @param chain FilterChain.
     * @throws IOException исключение.
     * @throws ServletException исключение.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        if (req.getRequestURI().contains("/login")) { //Если в запросе содержится "/login", то дальше по цепочке.
            chain.doFilter(request, response);
        } else {
            final HttpSession session = req.getSession(); //Если нет, то получить сессию.
            if (session.getAttribute("login") == null) { //Если в карте ключ-значение не содержится ключ "login",
                res.sendRedirect(String.format("%s/login", req.getContextPath())); //то направить в сервлет LoginPassServlet
                return;                                                            //и выйти.
            }
            chain.doFilter(request, response); //Если в карте ключ-значение содержится ключ "login", то дальше по цепочке.
        }
    }
}