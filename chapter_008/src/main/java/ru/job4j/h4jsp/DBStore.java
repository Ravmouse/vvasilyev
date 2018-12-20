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
 * @version 1.2
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
     * Здесь юзер без id, поскольку id будет сгенерирован БД.
     * @param user юзер, чьи данные нужно добавить в БД.
     */
    @Override
    public void add(final User user) {
        try (final Connection conn = SOURCE.getConnection(); final PreparedStatement statement = conn.prepareStatement(ADD_SQL)) {
            setStatementValues(statement, user);
            statement.executeUpdate();
        } catch (SQLException sql) {
            LOGGER.warn("Exception in add() method", sql);
        }
    }

    /**
     * Здесь юзер с id, т.к. id нужен для поиска юзера в БД среди существующих.
     * @param user юзер, чьи данные нужно обновить в БД.
     */
    @Override
    public void update(final User user) {
        try (final Connection conn = SOURCE.getConnection(); final PreparedStatement statement = conn.prepareStatement(UPDATE_SQL)) {
            setStatementValues(statement, user);
            statement.setInt(7, user.getId());
            statement.executeUpdate();
        } catch (SQLException sql) {
            LOGGER.warn("Exception in update() method", sql);
        }
    }

    /**
     * В прекомпилированном запросе устанавливаются параметры.
     * @param statement ссылка на прекомпилированный запрос.
     * @param user юзер.
     * @throws SQLException искл.
     */
    private void setStatementValues(final PreparedStatement statement, final User user) throws SQLException {
        statement.setString(1, user.getName());
        statement.setString(2, user.getLogin());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getComments());
        statement.setString(5, user.getPassword());
        statement.setInt(6, user.getRole().getId());
    }

    /**
     * Здесь юзер только с id и ничем более.
     * @param user юзер, чьи данные нужно удалить из БД.
     */
    @Override
    public void delete(final User user) {
        try (final Connection conn = SOURCE.getConnection(); final PreparedStatement st = conn.prepareStatement(DELETE_SQL)) {
            st.setInt(1, user.getId());
            st.executeUpdate();
        } catch (SQLException sql) {
            LOGGER.warn("Exception in delete() method", sql);
        }
    }

    /**
     * Здесь юзеры создаются со всеми полями своего класса, в т.ч. с createDate.
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
                        new Role(rs.getString("role_name"))));
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
                            new Role(rs.getString("role_name")));
                }
            }
        } catch (SQLException sql) {
            LOGGER.warn("Exception in findById() method", sql);
        }
        return result;
    }

    /**
     * Сюда юзер передается только с логином и паролем.
     * @param user юзер, чью роль нужно найти.
     * @return роль юзера.
     */
    @Override
    public Role findRole(final User user) {
        Role result = null;
        try (final Connection conn = SOURCE.getConnection();
             final PreparedStatement st = conn.prepareStatement(FIND_ROLE_BY_LOGIN_AND_PASSWORD)) {
            st.setString(1, user.getLogin());
            st.setString(2, user.getPassword());
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
     * Все роли загружаются из БД в список ролей.
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