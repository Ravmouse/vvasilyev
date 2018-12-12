package ru.job4j.h4jsp;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import ru.job4j.h2http.Store;
import ru.job4j.h2http.User;
import ru.job4j.h6filter.Role;
import ru.job4j.utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * В классе используется Connection pool dbcp2. В каждом методе происходит взятие коннекта из пула или создание нового.
 * @author Vitaly Vasilyev, date: 14.11.2018, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class DBStore implements AutoCloseable, Store {
    /**
     * Пул коннектов.
     */
    private static final BasicDataSource SOURCE = new BasicDataSource();
    /**
     * Ссылка на экземпляр этого класса.
     */
    private static final DBStore INSTANCE = new DBStore();
    /**
     * SQL-запрос на добавление данных.
     */
    private static final String ADD_SQL
            = "INSERT INTO users (name, login, email, comments, password, role) VALUES (?, ?, ?, ?, ?, ?)";
    /**
     * SQL-запрос на обновление данных.
     */
    private static final String UPDATE_SQL
            = "UPDATE users SET name = ?, login = ?, email = ?, comments = ?, password = ?, role = ? WHERE id = ?";
    /**
     * SQL-запрос на удаление данных.
     */
    private static final String DELETE_SQL = "DELETE from users WHERE id = ?";
    /**
     * SQL-запрос на выборку всех данных.
     */
    private static final String SELECT_ALL_SQL = "SELECT u.id, u.name, u.login, u.email, u.createdate,"
    + "u.comments, u.password, r.role_name FROM users u LEFT OUTER JOIN roles r ON u.role = r.id ORDER BY u.id";
    /**
     * SQL-запрос на получение данных для одного юзера.
     */
    private static final String SELECT_BY_ID_SQL = "SELECT u.id, u.name, u.login, u.email, u.createdate,"
    + "u.comments, u.password, r.role_name FROM users u LEFT OUTER JOIN roles r ON u.role = r.id WHERE u.id = ? ORDER BY u.id";
    /**
     * SQL-запрос на выборку всех ролей.
     */
    private static final String SELECT_ALL_ROLES = "SELECT * FROM roles ORDER BY role_name";
    /**
     * SQL-запрос на нахождение строки в БД по логину и паролю и получению роли.
     */
    private static final String FIND_ROLE_BY_LOGIN_AND_PASSWORD =
            "SELECT r.role_name FROM users u LEFT OUTER JOIN roles r ON u.role = r.id WHERE u.login = ? AND u.password = ?";
    /**
     * Логгер.
     */
    public static final Logger LOGGER = Logger.getLogger(Utils.getNameOfTheClass());

    /**
     * Приватный конструктор.
     */
    private DBStore() {
        BasicConfigurator.configure();
        final Properties properties = Utils.createAndLoadProp("ru/job4j/h4jsp/app.properties");
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl(properties.getProperty("jdbc.url"));
        SOURCE.setUsername(properties.getProperty("user"));
        SOURCE.setPassword(properties.getProperty("password"));
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    /**
     * @return ссылку на экземпляр этого класса.
     */
    public static DBStore getInstance() {
        return INSTANCE;
    }

    /**
     * @param list   список строк для добавления данных в хранилище.
     * @param number номер для таблицы users, которая ссылается на таблицу roles.
     */
    @Override
    public void add(final List<String> list, int number) {
        try (final Connection conn = SOURCE.getConnection(); final PreparedStatement statement = conn.prepareStatement(ADD_SQL)) {
            int i = 1;
            for (String s : list) {
                statement.setString(i++, s);
            }
            statement.setInt(i, number);
            statement.executeUpdate();
        } catch (SQLException sql) {
            LOGGER.warn("Exception in add() method", sql);
        }
    }

    /**
     * @param id     номер юзера.
     * @param list   список строк для изменения данных в хранилище.
     * @param number номер для таблицы users, которая ссылается на таблицу roles.
     */
    @Override
    public void update(int id, final List<String> list, int number) {
        try (final Connection conn = SOURCE.getConnection(); final PreparedStatement st = conn.prepareStatement(UPDATE_SQL)) {
            int i = 1;
            for (String s : list) {
                st.setString(i++, s);
            }
            st.setInt(6, number);
            st.setInt(7, id);
            st.executeUpdate();
        } catch (SQLException sql) {
            LOGGER.warn("Exception in update() method", sql);
        }
    }

    /**
     * @param id номер, по которому удаляется строка из хранилища.
     */
    @Override
    public void delete(int id) {
        try (final Connection conn = SOURCE.getConnection(); final PreparedStatement st = conn.prepareStatement(DELETE_SQL)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException sql) {
            LOGGER.warn("Exception in delete() method", sql);
        }
    }

    /**
     * @return список юзеров.
     */
    @Override
    public List<User> findAll() {
        final List<User> result = new ArrayList<>();
        try (final Connection conn = SOURCE.getConnection();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(SELECT_ALL_SQL)) {
            while (rs.next()) {
                result.add(new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("email"),
                        rs.getString("createDate"),
                        rs.getString("comments"),
                        rs.getString("password"),
                        rs.getString("role_name")
                ));
            }
        } catch (SQLException sql) {
            LOGGER.warn("Exception in findAll() method", sql);
        }
        return result;
    }

    /**
     * @param id номер юзера для нахождения в хранилище.
     * @return юзера по его номеру.
     */
    @Override
    public User findById(int id) {
        User result = null;
        try (final Connection conn = SOURCE.getConnection();
             final PreparedStatement st = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            st.setInt(1, id);
            try (final ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    result = new User(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("login"),
                            rs.getString("email"),
                            rs.getString("createDate"),
                            rs.getString("comments"),
                            rs.getString("password"),
                            rs.getString("role_name")
                    );
                }
            }
        } catch (SQLException sql) {
            LOGGER.warn("Exception in findById() method", sql);
        }
        return result;
    }

    /**
     * @param login    логин юзера.
     * @param password пароль юзера.
     * @return роль юзера.
     */
    @Override
    public Role findRoleByLoginPassword(final String login, final String password) {
        Role result = null;
        try (final Connection conn = SOURCE.getConnection();
             final PreparedStatement st = conn.prepareStatement(FIND_ROLE_BY_LOGIN_AND_PASSWORD)) {
            st.setString(1, login);
            st.setString(2, password);
            try (final ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    result = new Role(rs.getString("role_name"));
                }
            }
        } catch (SQLException sql) {
            LOGGER.warn("Exception in findRoleByLoginPassword() method", sql);
        }
        return result;
    }

    /**
     * @return список ролей.
     */
    @Override
    public List<Role> findAllRoles() {
        final List<Role> result = new ArrayList<>();
        try (final Connection conn = SOURCE.getConnection();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(SELECT_ALL_ROLES)) {
            while (rs.next()) {
                result.add(new Role(rs.getString("role_name")));
            }
        } catch (SQLException sql) {
            LOGGER.warn("Exception in findAllRoles() method", sql);
        }
        return result;
    }

    /**
     * @throws SQLException - исключение.
     */
    @Override
    public void close() throws SQLException {
        SOURCE.close();
    }
}