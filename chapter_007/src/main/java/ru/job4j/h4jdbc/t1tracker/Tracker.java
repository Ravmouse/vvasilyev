package ru.job4j.h4jdbc.t1tracker;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
     * Statement.
     */
    private Statement statement;
    /**
     * Результат возврата запроса.
     */
    private ResultSet resultSet;
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
     * Создает Connection с транзакцией, Statement и выполняет подготовку структуры БД.
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
            conn = getConnection(initList.get(0), initList.get(1), initList.get(2));
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//            conn.setAutoCommit(false);
            statement = conn.createStatement();
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
     * @param idName имя.
     * @param description описание.
     * @param comments комментарий.
     * @throws SQLException исключение.
     */
    public void insert(final String idName, final String description, final String comments) throws SQLException {
        final String str = String.format("INSERT INTO %s (name, description, create_date, comments)\n"
                + "VALUES ('%s', '%s', '%s'::DATE, '%s');", dbName, idName, description,
                new Date(System.currentTimeMillis()), comments);
        statement.executeUpdate(str);
    }

    /**
     * @param columnName имя столбца.
     * @param newValue новое значение.
     * @param oldValue старое значение.
     * @throws SQLException исключение.
     */
    public void update(final String columnName, final String newValue, final String oldValue) throws SQLException {
        final String str = String.format("UPDATE %s SET %s = '%s' WHERE %s = '%s'", dbName, columnName, newValue,
                columnName, oldValue);
        statement.executeUpdate(str);
    }

    /**
     * @param columnName имя столбца.
     * @param value значение.
     * @throws SQLException исключение.
     */
    public void delete(final String columnName, final String value) throws SQLException {
        final String str = String.format("DELETE FROM %s WHERE %s = '%s'", dbName, columnName, value);
        statement.executeUpdate(str);
    }

    /**
     * Запрашивает из таблицы все данные и выводит их на печать.
     * @throws SQLException исключение.
     */
    public void findAll() throws SQLException {
        resultSet = statement.executeQuery(String.format("SELECT * FROM %s", dbName));
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id"));
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("description"));
            System.out.println(resultSet.getString("create_date"));
            System.out.println(resultSet.getString("comments"));
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
            t.update("name", "Peter", "Mike");
            t.findAll();
            System.out.println("====================");
            t.delete("name", "Chris");
            t.findAll();
        }
    }

    /**
     * @throws Exception исключение.
     */
    @Override
    public void close() throws Exception {
        resultSet.close();
        statement.close();
        conn.close();
    }
}