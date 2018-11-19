package ru.job4j.h2http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
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
    /*private*/ public static final Validate LOGIC = ValidateService.getInstance();
    /**
     * Хэш-отображение с экземплярами классов, реализующими интерфейс Consumer, которые получаются после
     * выполнения соответствующих методов.
     */
    private final Map<String, Consumer<HttpServletRequest>> actionFunc = new HashMap<>();

    /**
     * Добавление в хэш-отображение ключей и значений.
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
        }
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
                LOGIC.add(parseRequest(request));
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
            LOGIC.update(id, parseRequest(request));
        };
    }

    /**
     * Удаление существующего User'а. Из запроса достаточно получить только id User'а.
     * @return экземпляр анонимного класса, реализующего функц.интерфейс Consumer с методом accept().
     * @throws VersionUserException если 1-ый поток уже обновил значение User.
     * @throws NotExistedUserException если User'а с таким id не существует.
     */
    private Consumer<HttpServletRequest> deleteUser() throws VersionUserException, NotExistedUserException {
        return request -> LOGIC.delete(Integer.parseInt(request.getParameter("id")));
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
        list.add(request.getParameter("comments"));
        return list;
    }
}