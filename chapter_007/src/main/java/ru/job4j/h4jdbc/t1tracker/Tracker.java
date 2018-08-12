package ru.job4j.h4jdbc.t1tracker;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static java.sql.DriverManager.getConnection;

/**
 * Класс, который можно использовать с try-with-resources.
 */
public class Tracker implements AutoCloseable {
    /**
     * Connection.
     */
    private Connection conn;
    /**
     * Имя таблицы в базе данных.
     */
    private String dbName;

    /**
     * @param config путь до файла настройки.
     */
    public Tracker(final String config) {
        try {
            init(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Считывает config.txt по строкам и заносит их в List; создает Properties со 2-ой и 3-ей строкой List'а;
     * создает Connection с БД на основе URL и Properties; создает Statement и подготавливает структуру БД.
     * @param config путь до файла настройки.
     * @throws Exception исключение.
     */
    private void init(final String config) throws Exception {
        final List<String> initList = new ArrayList<>();
        try (final BufferedReader br = new BufferedReader(new FileReader(config))) {
            String line;
            while ((line = br.readLine()) != null) {
                initList.add(line);
            }
        }
        final Properties prop = new Properties();
        prop.setProperty("user", initList.get(1));
        prop.setProperty("password", initList.get(2));
        conn = getConnection(initList.get(0), prop);
        try (final Statement statement = conn.createStatement()) {
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            final String tmp = initList.get(3);
            dbName = tmp.substring(tmp.lastIndexOf(" "), tmp.length() - 1); //В dbName кладется название таблицы.
            int index = 3;
            while (index < initList.size()) {
                statement.executeUpdate(initList.get(index++)); //Выполняются DROP TABLE и CREATE TABLE из config.txt.
            }
        }
    }

    /**
     * Вставляет в таблицу строку с данными.
     * Используется PreparedStatement, чтобы не допустить применения SQL Injection.
     * @param idName имя.
     * @param description описание.
     * @param comments комментарий.
     * @throws SQLException исключение.
     */
    public void insert(final String idName, final String description, final String comments) throws SQLException {
        final String str = "INSERT INTO " + dbName + " (name, description, create_date, comments) VALUES (?, ?, ?, ?)";
        try (final PreparedStatement insertStatement = conn.prepareStatement(str)) {
            insertStatement.setString(1, idName);
            insertStatement.setString(2, description);
            insertStatement.setDate(3, new Date(System.currentTimeMillis()));
            insertStatement.setString(4, comments);
            insertStatement.executeUpdate();
        }
    }

    /**
     * Заменяет в строке старое значение на новое.
     * Используется PreparedStatement, чтобы не допустить применения SQL Injection.
     * @param newValue новое значение.
     * @param oldValue старое значение.
     * @throws SQLException исключение.
     */
    public void update(final String newValue, final String oldValue) throws SQLException {
        final String str = "UPDATE " + dbName + " SET name = ? WHERE name = ?";
        try (final PreparedStatement updateStatement = conn.prepareStatement(str)) {
            updateStatement.setString(1, newValue);
            updateStatement.setString(2, oldValue);
            updateStatement.executeUpdate();
        }
    }

    /**
     * Используется PreparedStatement, чтобы не допустить применения SQL Injection.
     * @param deletedValue значение.
     * @throws SQLException исключение.
     */
    public void delete(final String deletedValue) throws SQLException {
        final String str = "DELETE FROM " + dbName + " WHERE name = ?";
        try (final PreparedStatement deleteStatement = conn.prepareStatement(str)) {
            deleteStatement.setString(1, deletedValue);
            deleteStatement.executeUpdate();
        }
    }

    /**
     * Запрашивает из таблицы все данные и выводит их на печать.
     * @throws SQLException исключение.
     */
    public void findAll() throws SQLException {
        try (final Statement statement = conn.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM items");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("description"));
                System.out.println(resultSet.getString("create_date"));
                System.out.println(resultSet.getString("comments"));
            }
        }
    }

    /**
     * @param args .
     * @throws Exception исключение.
     */
    public static void main(String[] args) throws Exception {
        final String config = "C:\\projects\\vvasilyev\\chapter_007\\src\\main\\java\\ru\\job4j\\h4jdbc"
                + "\\t1tracker\\config.txt";
        try (final Tracker t = new Tracker(config)) {
            t.insert("John", "java_junior", "likes_to_sleep_instead_of_work");
            t.insert("Chris", "front_end", "JS");
            t.insert("Mike", "administrator", "hates_doing_his_job");
            t.update("Peter", "Mike");
            t.findAll();
            System.out.println("====================");
            t.delete("Chris");
            t.findAll();
        }
    }

    /**
     * @throws Exception исключение.
     */
    @Override
    public void close() throws Exception {
        conn.close();
    }
}