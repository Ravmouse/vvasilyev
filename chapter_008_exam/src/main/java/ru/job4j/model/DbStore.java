package ru.job4j.model;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
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
 * @author Vitaly Vasilyev, date: 18.01.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class DbStore implements AutoCloseable, Store {
    /**
     * Ссылка содержит экземпляр данного класса.
     */
    private static final DbStore INSTANCE = new DbStore();
    /**
     * Ссылка на BasicDataSource.
     */
    private final BasicDataSource SOURCE = new BasicDataSource();
    /**
     * SQL-запрос на добавление аккаунта в БД.
     */
    private static final String ADD_ACCOUNT = "INSERT INTO accounts (name, surname, patron, mobile, seat) VALUES (?, ?, ?, ?, ?)";
    /**
     * SQL-запрос на выборку всех зрительских мест в зале из БД.
     */
    private static final String SELECT_ALL_SEATS = "SELECT * FROM seats ORDER BY id";
    /**
     * SQL-запрос на обновление информации о месте в зале в БД.
     */
    private static final String UPDATE_SEATS = "UPDATE seats SET status = 1 WHERE number = ?";
    /**
     * Логгер.
     */
    private static final Logger LOGGER = Logger.getLogger(Utils.getNameOfTheClass());

    /**
     * Конструктор.
     * setDefaultAutoCommit(false) = у каждого соединения, получаемого из пула, будет выставлен autoCommit в false.
     * Это важно для метода add(Account account), где используются два PreparedStatement.
     */
    private DbStore() {
        final Properties properties = Utils.createAndLoadProp("application.properties");
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl(properties.getProperty("jdbc.url"));
        SOURCE.setUsername(properties.getProperty("user"));
        SOURCE.setPassword(properties.getProperty("password"));
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        SOURCE.setDefaultAutoCommit(false);
    }

    /**
     * @return ссылку на экземпляр данного класса.
     */
    public static DbStore getInstance() {
        return INSTANCE;
    }

    /**
     * @param account зритель в к/т.
     */
    @Override
    public void add(Account account) {
        int number = account.getSeat().getNumber();
        Connection conn = null;
        PreparedStatement insertSt = null, updateSt = null;
        try {
            conn = SOURCE.getConnection();
            insertSt = conn.prepareStatement(ADD_ACCOUNT);
            insertSt.setString(1, account.getName());
            insertSt.setString(2, account.getSurname());
            insertSt.setString(3, account.getPatron());
            insertSt.setString(4, account.getMobile());
            insertSt.setInt(5, number);
            insertSt.executeUpdate();

            updateSt = conn.prepareStatement(UPDATE_SEATS);
            updateSt.setInt(1, number);
            updateSt.executeUpdate();

            conn.commit();
        } catch (SQLException sql) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                LOGGER.warn("Exception is caught during rolling back of the transaction", e);
            }
            LOGGER.warn("Exception is caught during writing the Account elements", sql);
        } finally {
            try {
                if (insertSt != null) {
                    insertSt.close();
                }
                if (updateSt != null) {
                    updateSt.close();
                }
                conn.close();
            } catch (SQLException e) {
                LOGGER.warn("Exception is caught during closing the resources", e);
            }
        }
    }

    /**
     * @return список всех зрительских мест в зале.
     */
    @Override
    public List<Seat> selectAll() {
        final List<Seat> rsl = new ArrayList<>();
        try (final Connection connection = SOURCE.getConnection();
             final Statement statement = connection.createStatement();
             final ResultSet rs = statement.executeQuery(SELECT_ALL_SEATS)) {
            while (rs.next()) {
                rsl.add(new Seat(rs.getInt("number"), rs.getInt("price"), rs.getInt("status")));
            }
        } catch (SQLException sql) {
            LOGGER.warn("Exception is caught during selecting all the elements", sql);
        }
        return rsl;
    }

    /**
     * @throws Exception искл.
     */
    @Override
    public void close() throws Exception {
        SOURCE.close();
    }
}