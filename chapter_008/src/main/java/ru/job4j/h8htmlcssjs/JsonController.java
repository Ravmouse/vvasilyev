package ru.job4j.h8htmlcssjs;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.utils.Utils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Vitaly Vasilyev, date: 28.12.2018, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class JsonController extends HttpServlet {
    /**
     * Карта для хранения экземпляров класса Person.
     */
    private final ConcurrentHashMap<Integer, Person> persons = new ConcurrentHashMap<>();

    /**
     * Если в адресной строке вбить .../json, то произойдет перенаправление на страницу index.html.
     * А так этот метод выполняется из метода doPost().
     * ObjectMapper создает JSON-объект как строку из данных экз.класса Person.
     * Устанавливается тип направляемых данных как JSON.
     * @param req запрос.
     * @param res ответ.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        final ObjectMapper mapper = new ObjectMapper();
        Integer id = (Integer) req.getAttribute("personId");
        if (id == null) {
            req.getRequestDispatcher("/index.html").forward(req, res);
        } else {
            String strResponse = mapper.writeValueAsString(persons.get(id));
            res.setContentType("text/json");
            PrintWriter writer = new PrintWriter(res.getOutputStream());
            writer.append(strResponse);
            writer.flush();
        }
    }

    /**
     * Метод из класса Utils разбирает request, где создается StringBuilder в виде JSON-объекта.
     * Далее при помощи ObjectMapper создается экз.класса Person.
     * Этот экз. помещается в ConcurrentHashMap.
     * В атрибут кладется id экз.класса Person, чтобы его получить в doGet().
     * @param req запрос.
     * @param res ответ.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        final ObjectMapper mapper = new ObjectMapper();
        String strRequest = Utils.getRequestBody(req).toString();
        Person person = mapper.readValue(strRequest, Person.class);
        persons.put(person.getId(), person);
        req.setAttribute("personId", person.getId());
        doGet(req, res);
    }
}