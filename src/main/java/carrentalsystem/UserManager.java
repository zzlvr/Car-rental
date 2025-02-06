package carrentalsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    // Метод для аутентификации пользователя
    public User authenticateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseHandler.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (SQLException e) {
            System.err.println("Error authenticating user: " + e.getMessage());
        }
        return null;
    }

    // Метод для добавления нового пользователя
    public void addUser(String username, String password, String role) {
        String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseHandler.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            preparedStatement.executeUpdate();
            System.out.println("User added: " + username);
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
        }
    }
}
