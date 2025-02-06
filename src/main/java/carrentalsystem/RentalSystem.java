package carrentalsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentalSystem {
    private DatabaseHandler databaseHandler;

    public RentalSystem() {
        databaseHandler = new DatabaseHandler();
    }

    // Метод для добавления нового автомобиля в базу данных
    public void addCar(Car car) {
        try {
            String insertQuery = "INSERT INTO cars (brand, model, car_type, is_available) VALUES (?, ?, ?, ?)";
            try (Connection connection = DatabaseHandler.connect();
                 PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

                preparedStatement.setString(1, car.getBrand());
                preparedStatement.setString(2, car.getModel());
                preparedStatement.setString(3, car.getCarType());
                preparedStatement.setBoolean(4, car.isAvailable());

                preparedStatement.executeUpdate();
                System.out.println("Car added: " + car.getCarInfo());
            }
        } catch (SQLException e) {
            System.err.println("Error adding car to the database: " + e.getMessage());
        }
    }

    // Метод для получения всех доступных автомобилей из базы данных
    public List<Car> getAvailableCars() {
        List<Car> availableCars = new ArrayList<>();
        try (Connection connection = DatabaseHandler.connect()) {
            String query = "SELECT * FROM cars WHERE is_available = TRUE";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String brand = resultSet.getString("brand");
                    String model = resultSet.getString("model");
                    String carType = resultSet.getString("car_type");

                    Car car = createCar(carType, brand, model);
                    if (car != null) {
                        availableCars.add(car);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching available cars: " + e.getMessage());
        }
        return availableCars;
    }

    // Метод для создания объекта автомобиля в зависимости от типа
    private Car createCar(String carType, String brand, String model) {
        switch (carType) {
            case "Sedan":
                return new Sedan(brand, model);
            case "SUV":
                return new SUV(brand, model);
            case "Truck":
                return new Truck(brand, model);
            default:
                return null;
        }
    }

    // Метод для обновления доступности автомобиля в базе данных
    public void updateCarAvailability(Car car, boolean isAvailable) {
        String query = "UPDATE cars SET is_available = ? WHERE brand = ? AND model = ?";
        try (Connection connection = DatabaseHandler.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setBoolean(1, isAvailable);
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.executeUpdate();
            System.out.println("Car availability updated.");
            car.setAvailable(isAvailable);  // Обновляем доступность автомобиля в объекте
        } catch (SQLException e) {
            System.err.println("Error updating car availability: " + e.getMessage());
        }
    }
}
