package ru.job4j.h2http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Сервлет.
 */
public class UserServlet extends HttpServlet { //Presentation
    /**
     * Ссылка на класс ValidateService, где данные проверяются.
     */
    /*private*/ protected final ValidateService logic = ValidateService.getInstance();
    /**
     * Хэш-отображение с экземплярами классов, реализующих интерфейс Consumer, которые получаются после
     * выполнения соответствующих методов.
     */
    /*private*/ final Map<String, Consumer<HttpServletRequest>> actionFunc = new HashMap<>();

    /**
     * @param servletConfig servletConfig.
     */
    @Override
    public void init(ServletConfig servletConfig) {
        actionFunc.put("add", addUser());
        actionFunc.put("update", updateUser());
        actionFunc.put("delete", deleteUser());
    }

    /**
     * @param req запрос.
     * @param res ответ.
     * @throws IOException исключение.
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter writer = new PrintWriter(res.getOutputStream());
        if (req.getAttribute("text") == null) {
            writer.append(Arrays.toString(logic.findAll()));
        } else {
            writer.append(req.getAttribute("text").toString());
        }
        writer.flush();
    }

    /**
     * @param req запрос.
     * @param res ответ.
     * @throws IOException исключение.
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        final String text;
        res.setContentType("text/html");
        final String key = req.getParameter("action");
        try {
            actionFunc.get(key).accept(req);
        } catch (VersionUserException | NotExistedUserException e) { //Пробрасывается исключение.
            text = e.getMessage();                                   //Получается сообщение из исключения.
            req.setAttribute("text", text);                          //Устанавливается аттрибут в запросе.
            doGet(req, res);                                         //Вызывается doGet(), чтобы отправить ответ клиенту
            return;                                                  //с сообщением исключения.
        }
        text = Arrays.toString(logic.findAll()); //Если искл. не было, то находим всех User'ов.
        req.setAttribute("text", text);          //Устанавливаем аттрибут в запросе,
//        doGet(req, res);                         //который направляем в doGet().
    }

    /**
     * Добавление нового User'а.
     * Все параметры запроса направляются в Логику, где создается новый User.
     * @return экземпляр анонимного класса, реализующего функц.интерфейс Consumer с методом accept().
     */
    private Consumer<HttpServletRequest> addUser() {
        return new Consumer<HttpServletRequest>() {
            @Override
            public void accept(HttpServletRequest request) {
                final List<String> list = parseRequest(request);
                logic.add(list.get(0), list.get(1), list.get(2), list.get(3));
            }
        };
    }

    /**
     * Обновление существующего User'а.
     * Все параметры запроса направляются в Логику, где обновляется существующий User.
     * @return экземпляр анонимного класса, реализующего функц.интерфейс Consumer с методом accept().
     * @throws VersionUserException если 1-ый поток уже обновил значение User.
     * @throws NotExistedUserException если User'а с таким id не существует.
     */
    private Consumer<HttpServletRequest> updateUser() throws VersionUserException, NotExistedUserException {
        return request -> {
            int id = Integer.parseInt(request.getParameter("id"));
            logic.update(id, parseRequest(request));
        };
    }

    /**
     * Удаление существующего User'а. Из запроса достаточно получить только id User'а.
     * @return экземпляр анонимного класса, реализующего функц.интерфейс Consumer с методом accept().
     * @throws VersionUserException если 1-ый поток уже обновил значение User.
     * @throws NotExistedUserException если User'а с таким id не существует.
     */
    private Consumer<HttpServletRequest> deleteUser() throws VersionUserException, NotExistedUserException {
        return request -> logic.delete(Integer.parseInt(request.getParameter("id")));
    }

    /**
     * @param request запрос.
     * @return список параметров из запроса.
     */
    private List<String> parseRequest(HttpServletRequest request) {
        List<String> list = new ArrayList<>();
        String decodedString = null;
        try {
            //Декодирование, чтобы был символ @, а не %40.
            decodedString = URLDecoder.decode(request.getParameter("email"), "UTF-8");
        } catch (IOException io) {
            io.printStackTrace();
        }
        list.add(request.getParameter("name"));
        list.add(request.getParameter("login"));
        list.add(decodedString);
        list.add(request.getParameter("createDate"));
        return list;
    }
}