package ru.job4j.h7mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.h2http.UserServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.hamcrest.core.Is.is;

/**
 * @author Rav, date: 21.12.2018
 * @version 1.0
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DBStore.class)
public class DBStoreTest {
    /**
     * @param req запрос.
     * @param action действие.
     * @param name имя.
     * @param login логин.
     * @param email электронная почта.
     * @param comments комментарии.
     * @param password пароль.
     * @param role роль.
     */
    private void setWhenThenParams(final HttpServletRequest req, String action, String name, String login, String email,
    String country, String city, String comments, String password, String role) {
        when(req.getParameter("action")).thenReturn(action);
        when(req.getParameter("name")).thenReturn(name);
        when(req.getParameter("login")).thenReturn(login);
        when(req.getParameter("email")).thenReturn(email);
        when(req.getParameter("country")).thenReturn(country);
        when(req.getParameter("city")).thenReturn(city);
        when(req.getParameter("comments")).thenReturn(comments);
        when(req.getParameter("password")).thenReturn(password);
        when(req.getParameter("role")).thenReturn(role);
    }

    /**
     * Добавление юзера; проверка, есть ли юзер юзер с таким именем.
     * @throws IOException искл.
     * @throws ServletException искл.
     */
    @Test
    public void whenRequestAddsUserThenUserExists() throws IOException, ServletException {
        DBStore store = new DbStoreStub();
        mockStatic(DBStore.class);
        when(DBStore.getInstance()).thenReturn(store);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        setWhenThenParams(req, "add", "Vit", "test", "v@v.com", "Rus", "MSK", "comm", "pass", "Administrator");
        UserServlet servlet = new UserServlet();
        servlet.init(servlet.getServletConfig());
        servlet.doPost(req, res);

        assertThat(store.findAll().get(0).getName(), is("Vit"));
        assertThat(store.findAll().get(0).getLogin(), is("test"));
        assertThat(store.findAll().get(0).getEmail(), is("v@v.com"));
        assertThat(store.findAll().get(0).getCountry(), is("Rus"));
        assertThat(store.findAll().get(0).getCity(), is("MSK"));
        assertThat(store.findAll().get(0).getRole().getName(), is("Administrator"));
    }

    /**
     * Сначала добавление юзера, затем обновление; проверка, есть ли юзер юзер с таким именем.
     * @throws IOException искл.
     * @throws ServletException искл.
     */
//    @Test
//    public void whenRequestUpdatesUserThenUserIsUpdated() throws IOException, ServletException {
//        DBStore store = new DbStoreStub();
//        mockStatic(DBStore.class);
//        when(DBStore.getInstance()).thenReturn(store);
//
//        HttpServletRequest req = mock(HttpServletRequest.class);
//        HttpServletResponse res = mock(HttpServletResponse.class);
//        setWhenThenParams(req, "add", "Vit", "test", "v@v.com", "Rus", "MSK", "comm", "pass", "Administrator");
//        UserServlet servlet = new UserServlet();
//        servlet.init(servlet.getServletConfig());
//        servlet.doPost(req, res);
//
//        when(req.getParameter("id")).thenReturn("0");
//        setWhenThenParams(req, "update", "Mike", "test123", "m@m.com", "Rus", "MSK", "c", "p", "User");
//        servlet.doPost(req, res);
//
//        assertThat(store.findAll().get(0).getName(), is("Mike"));
//        assertThat(store.findAll().get(0).getLogin(), is("test123"));
//        assertThat(store.findAll().get(0).getEmail(), is("m@m.com"));
//        assertThat(store.findAll().get(0).getRole().getName(), is("User"));
//    }

    /**
     * Сначала добавление юзера, затем удаление; проверка, пусто ли отображение.
     * @throws IOException искл.
     * @throws ServletException искл.
     */
//    @Test
//    public void whenRequestDeletesUserThenUserIsDeleted() throws IOException, ServletException {
//        DBStore store = new DbStoreStub();
//        mockStatic(DBStore.class);
//        when(DBStore.getInstance()).thenReturn(store);
//
//        HttpServletRequest req = mock(HttpServletRequest.class);
//        HttpServletResponse res = mock(HttpServletResponse.class);
//        setWhenThenParams(req, "add", "Vit", "test", "v@v.com", "Rus", "MSK", "comm", "pass", "Administrator");
//
//        UserServlet servlet = new UserServlet();
//        servlet.init(servlet.getServletConfig());
//        servlet.doPost(req, res);
//        assertThat(store.findAll().get(0).getName(), is("Vit"));
//
//        when(req.getParameter("action")).thenReturn("delete");
//        when(req.getParameter("id")).thenReturn("0");
//        servlet.doPost(req, res);
//        assertThat(store.findAll().size(), is(0));
//    }

    /**
     * Сначала добавление юзера, затем получение юзера по номеру.
     * @throws IOException искл.
     * @throws ServletException искл.
     */
//    @Test
//    public void whenRequestGetsUserByIdThenUserIsReturned() throws IOException, ServletException {
//        DBStore store = new DbStoreStub();
//        mockStatic(DBStore.class);
//        when(DBStore.getInstance()).thenReturn(store);
//
//        HttpServletRequest req = mock(HttpServletRequest.class);
//        HttpServletResponse res = mock(HttpServletResponse.class);
//        setWhenThenParams(req, "add", "Vit", "test", "v@v.com", "Rus", "MSK", "comm", "pass", "Administrator");
//
//        UserServlet servlet = new UserServlet();
//        servlet.init(servlet.getServletConfig());
//        servlet.doPost(req, res);
//        assertThat(store.findAll().get(0).getName(), is("Vit"));
//
//        assertThat(store.findById(0), is(UserServlet.LOGIC.findById(0)));
//    }
}