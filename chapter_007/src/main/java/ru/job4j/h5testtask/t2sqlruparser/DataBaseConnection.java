package ru.job4j.h5testtask.t2sqlruparser;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import static java.sql.DriverManager.getConnection;

/**
 *
 */
public class DataBaseConnection implements AutoCloseable {
    /**
     * Соединение с БД.
     */
    private Connection connection;
    /**
     * Отображение вакансий типа "название_вакансии" - "время_публикации".
     */
    final Map<String, String> jobMap = new LinkedHashMap<>();
    /**
     * Логгер.
     */
    private static final Logger LOGGER = Logger.getLogger(Utils.getNameOfTheClass());
    /**
     * Состояние табл. в БД: false - не было запуска приложения, true - был запуск.
     */
    private boolean infoState;

    /**
     * @param prop ссылка на файл настроек.
     * @throws SQLException в случае возникновения исключения.
     */
    public DataBaseConnection(Properties prop) throws SQLException {
        BasicConfigurator.configure();
        PropertyConfigurator.configure(Utils.getResourcePath("log4j.properties"));
        connection = getConnection(prop.getProperty("jdbc.url"), prop);
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        infoState = retrieveBoolValue(); //При 1-ом запуске retrieveBoolValue() должен вернуть false, а после остальных-true.
    }

    /**
     * Если получили false, значит прил. запускается 1-ый раз, если true - 2-ой раз и т.д.
     * @return true или false.
     * @throws SQLException в случае возникновения исключения.
     */
    private boolean retrieveBoolValue() throws SQLException {
        boolean result = false;
        try (final Statement st = connection.createStatement()) {
            try (final ResultSet resultSet = st.executeQuery("SELECT * FROM info")) {
                while (resultSet.next()) {
                    result = resultSet.getBoolean("is_launched");
                }
            }
        }
        return result;
    }

    /**
     * После 1-го запуска приложения установить в табл. true, чтобы при след.запусках данные собирались только за день.
     * @throws SQLException в случае возникновения исключения.
     */
    private void changeBoolValue() throws SQLException {
        final String str = "UPDATE info SET is_launched = ?";
        try (final PreparedStatement st = connection.prepareStatement(str)) {
            st.setBoolean(1, true);
            st.executeUpdate();
        }
    }

    /**
     * Запись данных из Map в БД.
     * @throws SQLException в случае возникновения исключения.
     */
    public void sendDataToDB() throws SQLException {
        for (Map.Entry<String, String> me : jobMap.entrySet()) {
            final String str = "INSERT INTO job_offers (job_title, pub_date) VALUES (?, ?)";
            try (final PreparedStatement st = connection.prepareStatement(str)) {
                st.setString(1, me.getKey());
                st.setString(2, me.getValue());
                LOGGER.info(me.getKey() + " [" + me.getValue() + "]");
                st.executeUpdate();
            }
        }
        if (!infoState) { //Если infoState == false, значит это 1-ый запуск, - нужно вызвать changeBoolValue()
            changeBoolValue();    //и установить true в БД.
        }
    }

    /**
     * Получение данных из БД и запись их в Map.
     */
    public void requestAllData() {
        try (final Statement st = connection.createStatement()) {
            try (final ResultSet rs = st.executeQuery("SELECT * FROM job_offers")) {
                while (rs.next()) {
                    jobMap.put(rs.getString("job_title"), rs.getString("pub_date"));
                }
            }
            st.execute("DELETE FROM job_offers");
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }

    /**
     * @return true или false.
     */
    public boolean isInfoState() {
        return infoState;
    }

    /**
     * Закрывает соединение.
     */
    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}