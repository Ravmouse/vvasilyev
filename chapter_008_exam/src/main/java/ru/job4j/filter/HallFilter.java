package ru.job4j.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Vitaly Vasilyev, date: 29.01.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class HallFilter implements Filter {

    /**
     * @param request запрос.
     * @param response ответ.
     * @param chain цепь.
     * @throws IOException искл.
     * @throws ServletException искл.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (httpRequest.getMethod().equals("GET")) {
            httpResponse.setContentType("text/html");
        }
        chain.doFilter(request, response);
    }
}