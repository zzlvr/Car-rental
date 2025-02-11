package carrentalsystem;

import java.util.Scanner;

public class AdminPanel {
    private RentalSystem rentalSystem;
    private UserManager userManager;

    public AdminPanel(RentalSystem rentalSystem, UserManager userManager) {
        this.rentalSystem = rentalSystem;
        this.userManager = userManager;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nAdmin Panel:");
            System.out.println("1. Add car");
            System.out.println("2. Remove car");
            System.out.println("3. Add user");
            System.out.println("4. Remove user");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addCar(scanner);
                    break;
                case 2:
                    removeCar(scanner);
                    break;
                case 3:
                    addUser(scanner);
                    break;
                case 4:
                    removeUser(scanner);
                    break;
                case 5:
                    System.out.println("Exiting admin panel...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void addCar(Scanner scanner) {
        System.out.println("Enter car type (Sedan, SUV, Truck):");
        String carType = scanner.nextLine();
        System.out.println("Enter car brand:");
        String brand = scanner.nextLine();
        System.out.println("Enter car model:");
        String model = scanner.nextLine();

        Car car = CarFactory.createCar(carType, brand, model);
        rentalSystem.addCar(car);
    }

    private void removeCar(Scanner scanner) {
        System.out.println("Enter car model to remove:");
        String modelToRemove = scanner.nextLine();
        rentalSystem.removeCar(modelToRemove);
    }

    private void addUser(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter role (admin, customer):");
        String role = scanner.nextLine();

        userManager.addUser(username, password, role);
    }

    private void removeUser(Scanner scanner) {
        System.out.println("Enter username to remove:");
        String usernameToRemove = scanner.nextLine();
        userManager.removeUser(usernameToRemove);
    }
}