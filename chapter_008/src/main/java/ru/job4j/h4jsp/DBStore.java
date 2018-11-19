package ru.job4j.h4jsp;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.utils.Utils;
import ru.job4j.h2http.Store;
import ru.job4j.h2http.User;
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
    private static final String ADD_SQL = "INSERT INTO users (name, login, email, comments) VALUES (?, ?, ?, ?)";
    /**
     * SQL-запрос на обновление данных.
     */
    private static final String UPDATE_SQL = "UPDATE users SET name = ?, login = ?, email = ?, comments = ? WHERE id = ?";
    /**
     * SQL-запрос на удаление данных.
     */
    private static final String DELETE_SQL = "DELETE from users WHERE id = ?";
    /**
     * SQL-запрос на выборку всех данных.
     */
    private static final String SELECT_ALL_SQL = "SELECT * FROM users";
    /**
     * SQL-запрос на получение данных для одного юзера.
     */
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM users WHERE id = ?";

    /**
     * Приватный конструктор.
     */
    private DBStore() {
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
     * @param list список строк для добавления данных в хранилище.
     */
    @Override
    public void add(final List<String> list) {
        try (final Connection conn = SOURCE.getConnection()) {
            try (final PreparedStatement statement = conn.prepareStatement(ADD_SQL)) {
                int i = 1;
                for (String s : list) {
                    statement.setString(i++, s);
                }
                statement.executeUpdate();
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }

    /**
     * @param id   номер.
     * @param list список строк для изменения данных в хранилище.
     */
    @Override
    public void update(int id, final List<String> list) {
        try (final Connection conn = SOURCE.getConnection()) {
            try (final PreparedStatement st = conn.prepareStatement(UPDATE_SQL)) {
                int i = 1;
                for (String s : list) {
                    st.setString(i++, s);
                }
                st.setInt(5, id);
                st.executeUpdate();
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }

    /**
     * @param id номер, по которому удаляется строка в хранилище.
     */
    @Override
    public void delete(int id) {
        try (final Connection conn = SOURCE.getConnection()) {
            try (final PreparedStatement st = conn.prepareStatement(DELETE_SQL)) {
                st.setInt(1, id);
                st.executeUpdate();
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }

    /**
     * @return список юзеров.
     */
    @Override
    public List<User> findAll() {
        final List<User> result = new ArrayList<>();
        try (final Connection conn = SOURCE.getConnection()) {
            try (final Statement st = conn.createStatement()) {
                try (final ResultSet rs = st.executeQuery(SELECT_ALL_SQL)) {
                    while (rs.next()) {
                        result.add(new User(rs.getInt("id"),
                                            rs.getString("name"),
                                            rs.getString("login"),
                                            rs.getString("email"),
                                            rs.getString("createDate"),
                                            rs.getString("comments")
                        ));
                    }
                }
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return result;
    }

    /**
     * @param id номер.
     * @return юзера по его номеру.
     */
    @Override
    public User findById(int id) {
        User result = null;
        try (final Connection conn = SOURCE.getConnection()) {
            try (final PreparedStatement st = conn.prepareStatement(SELECT_BY_ID_SQL)) {
                st.setInt(1, id);
                try (final ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        result = new User(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("login"),
                                rs.getString("email"),
                                rs.getString("createDate"),
                                rs.getString("comments")
                        );
                    }
                }
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
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