package carrentalsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
    private static final String URL = "jdbc:postgresql://localhost:5432/car_rental_system";
    private static final String USER = "postgres";
    private static final String PASSWORD = "achpuchimak";

    public static Connection connect() {
        Connection connection = null;
        try {
            // Загружаем драйвер (не обязательно для новых версий Java)
            Class.forName("org.postgresql.Driver");

            // Устанавливаем соединение
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Подключение к базе данных установлено.");
        } catch (ClassNotFoundException e) {
            System.err.println("Драйвер не найден: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Ошибка подключения: " + e.getMessage());
        }
        return connection;
    }
}