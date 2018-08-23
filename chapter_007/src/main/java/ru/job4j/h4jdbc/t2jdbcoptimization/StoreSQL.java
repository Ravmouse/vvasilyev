package ru.job4j.h4jdbc.t2jdbcoptimization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static java.sql.DriverManager.getConnection;

/**
 * Осуществляется считывание данных с TXT файла config, подключение к БД и отправка запроса на добавление данных в БД.
 */
public class StoreSQL implements AutoCloseable {
    /**
     * Connection.
     */
    private Connection connection;

    /**
     * @throws SQLException в случае появления ошибки.
     */
    public StoreSQL() throws SQLException {
        String config = StoreSQL.class.getResource("config.txt").toExternalForm(); //Строковое представление URL.
        config = config.substring(config.indexOf("C"), config.length()); //Удаление из строки "file:/"
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException classNot) {
            classNot.printStackTrace();
        }
        String str;
        final String[] initData = new String[3];
        int index = 0;
        try (final BufferedReader br = new BufferedReader(new FileReader(config))) {
            while ((str = br.readLine()) != null) {
                initData[index++] = str;
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        connection = getConnection(initData[0]);
        try (final Statement st = connection.createStatement()) {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            st.execute(initData[1]);
            st.execute(initData[2]);
        }
    }

    /**
     * @param n количество строк генерируемых данных в БД.
     */
    public void generate(int n) {
        final Random rnd = new Random();
        String str;
        for (int i = 0; i < n; i++) {
            str = "INSERT INTO entry (field) VALUES (?)";
            try (final PreparedStatement insertStatement = connection.prepareStatement(str)) {
                connection.setAutoCommit(false);
                insertStatement.setInt(1, rnd.nextInt(100));
                insertStatement.executeUpdate();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }

    /**
     * Весь ResultSet, который получается в результате запроса, добавляется в список Integer.
     * @return список Integer.
     * @throws SQLException в случае появления ошибки.
     */
    public List<Integer> getAllDataFromDB() throws SQLException {
        final List<Integer> result = new ArrayList<>();
        try (final Statement st = connection.createStatement()) {
            try (final ResultSet resultSet = st.executeQuery("SELECT * FROM entry")) {
                while (resultSet.next()) {
                    result.add(resultSet.getInt("field"));
                }
            }
        }
        return result;
    }

    /**
     * @throws SQLException в случае появления ошибки.
     */
    @Override
    public void close() throws SQLException {
        connection.close();
    }
}