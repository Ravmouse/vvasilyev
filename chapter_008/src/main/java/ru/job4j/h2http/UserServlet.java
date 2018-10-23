package ru.job4j.h2http;

import ru.job4j.h4waitnotifynotifyall.t3lock.SimpleLock;
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
    private final ValidateService logic = ValidateService.getInstance();
    /**
     * Хэш-отображение с экземплярами классов, реализующих интерфейс Consumer, которые получаются после
     * выполнения соответствующих методов.
     */
    private final Map<String, Consumer<HttpServletRequest>> actionFunc = new HashMap<>();
    /**
     * Строка для вывода клиенту в GET-запросе.
     */
    private String text;
    private SimpleLock lock = new SimpleLock();

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
        writer.append(text);
        writer.flush();
    }

    /**
     * @param req запрос.
     * @param res ответ.
     * @throws IOException исключение.
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        String key = req.getParameter("action");
        try {
            actionFunc.get(key).accept(req);
        } catch (VersionUserException | NotExistedUserException e) { //Пробрасывается исключение.
            try {
                lock.lock(); //Если поток дошел сюда первым, то он "захватывает" lock первым.
            } catch (InterruptedException exp) {
                exp.printStackTrace();
            }
            text = e.getMessage(); //Получается сообщение из исключения.
            doGet(req, res); //Вызывается doGet(), чтобы отправить ответ клиенту с сообщением исключения.
            text = Arrays.toString(logic.findAll()); //Если после POST-запроса клиент сделает GET-запрос, то нужно
            lock.unlock();                           //"Отпускает" lock.
            return;                                  //снова отобразить список User'ов.
        }
        try {
            lock.lock();                             //Если первый поток - это тот, который не выкидывает искл., то
        } catch (InterruptedException e) {           //он "захватывает" lock первым.
            e.printStackTrace();
        }
        text = Arrays.toString(logic.findAll());     //Если искл. не было, то сюда записываем всех User'ов и отображаем
        doGet(req, res);                             //в doGet().
        lock.unlock();                               //"Отпускает" lock.
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
                logic.add(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4));
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
        return request -> logic.update(parseRequest(request));
    }

    /**
     * Удаление существующего User'а. Из запроса достаточно получить только id User'а.
     * @return экземпляр анонимного класса, реализующего функц.интерфейс Consumer с методом accept().
     * @throws VersionUserException если 1-ый поток уже обновил значение User.
     * @throws NotExistedUserException если User'а с таким id не существует.
     */
    private Consumer<HttpServletRequest> deleteUser() throws VersionUserException, NotExistedUserException {
        return request -> logic.delete(request.getParameter("id"));
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
        list.add(request.getParameter("id"));
        list.add(request.getParameter("name"));
        list.add(request.getParameter("login"));
        list.add(decodedString);
        list.add(request.getParameter("createDate"));
        return list;
    }
}