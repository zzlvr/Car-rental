package carrentalsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalSystem {
    private DatabaseHandler databaseHandler;

    public RentalSystem() {
        databaseHandler = new DatabaseHandler();
    }

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

                    Car car = CarFactory.createCar(carType, brand, model);
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

    public void removeCar(String model) {
        String query = "DELETE FROM cars WHERE model = ?";
        try (Connection connection = DatabaseHandler.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, model);
            preparedStatement.executeUpdate();
            System.out.println("Car " + model + " removed.");
        } catch (SQLException e) {
            System.err.println("Error removing car: " + e.getMessage());
        }
    }
    public Car getCarByModel(String model) {
        Car car = null;
        String query = "SELECT * FROM cars WHERE model = ?";
        try (Connection connection = DatabaseHandler.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, model);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String brand = resultSet.getString("brand");
                String carType = resultSet.getString("car_type");
                car = CarFactory.createCar(carType, brand, model);
                car.setAvailable(resultSet.getBoolean("is_available"));  // Устанавливаем статус доступности
            }
        } catch (SQLException e) {
            System.err.println("Error fetching car: " + e.getMessage());
        }
        return car;
    }

}