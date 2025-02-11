package carrentalsystem;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RentalSystem rentalSystem = new RentalSystem();
        UserManager userManager = new UserManager();  // Создаем объект UserManager
        AdminPanel adminPanel = new AdminPanel(rentalSystem, userManager);  // Передаем оба объекта в AdminPanel

        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        // Аутентификация пользователя
        User user = userManager.authenticateUser(username, password);
        if (user == null) {
            System.out.println("Authentication failed.");
            return;
        }

        System.out.println("Welcome, " + user.getUsername() + " (" + user.getRole() + ")");

        if (user.getRole().equals("admin")) {
            adminPanel.showMenu();  // Переход в меню администратора
        } else if (user.getRole().equals("customer")) {
            showCustomerMenu(rentalSystem, scanner);
        }
    }

    private static void showCustomerMenu(RentalSystem rentalSystem, Scanner scanner) {
        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Display available cars");
            System.out.println("2. Rent a car");
            System.out.println("3. Return a car");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    displayAvailableCars(rentalSystem);
                    break;
                case 2:
                    rentCar(rentalSystem, scanner);
                    break;
                case 3:
                    returnCar(rentalSystem, scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void displayAvailableCars(RentalSystem rentalSystem) {
        List<Car> cars = rentalSystem.getAvailableCars();
        if (cars.isEmpty()) {
            System.out.println("No cars available.");
        } else {
            System.out.println("Available cars:");
            for (Car car : cars) {
                System.out.println(car.getCarInfo());
            }
        }
    }

    private static void rentCar(RentalSystem rentalSystem, Scanner scanner) {
        System.out.println("Enter car model to rent:");
        String modelToRent = scanner.nextLine();
        for (Car car : rentalSystem.getAvailableCars()) {
            if (car.getModel().equalsIgnoreCase(modelToRent) && car.isAvailable()) {
                rentalSystem.updateCarAvailability(car, false); // Обновляем доступность в базе данных
                System.out.println(car.getCarInfo() + " successfully rented.");
                return;
            }
        }
        System.out.println("Car not available for rent.");
    }

    private static void returnCar(RentalSystem rentalSystem, Scanner scanner) {
        System.out.println("Enter car model to return:");
        String modelToReturn = scanner.nextLine();

        Car car = rentalSystem.getCarByModel(modelToReturn);
        if (car == null) {
            System.out.println("Car not found.");
            return;
        }

        if (car.isAvailable()) {
            System.out.println("This car has not been rented and cannot be returned.");
        } else {
            rentalSystem.updateCarAvailability(car, true); // Обновляем доступность в базе данных
            System.out.println(car.getCarInfo() + " successfully returned.");
        }
    }

}