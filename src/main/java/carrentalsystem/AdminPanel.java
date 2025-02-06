package carrentalsystem;

import java.util.Scanner;

public class AdminPanel {
    private RentalSystem rentalSystem;

    public AdminPanel(RentalSystem rentalSystem) {
        this.rentalSystem = rentalSystem;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nAdmin Panel:");
            System.out.println("1. Add car");
            System.out.println("2. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCarManually(scanner);
                    break;
                case 2:
                    System.out.println("Exiting admin panel...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void addCarManually(Scanner scanner) {
        System.out.println("Enter car type (SEDAN, SUV, TRUCK):");
        String carType = scanner.nextLine().toUpperCase();

        System.out.println("Enter car brand:");
        String brand = scanner.nextLine();

        System.out.println("Enter car model:");
        String model = scanner.nextLine();

        Car car = createCar(carType, brand, model);
        if (car != null) {
            rentalSystem.addCar(car); // Теперь метод работает
            System.out.println("Car added: " + car.getCarInfo());
        } else {
            System.out.println("Invalid car type. Please try again.");
        }
    }

    private Car createCar(String carType, String brand, String model) {
        switch (carType) {
            case "SEDAN":
                return new Sedan(brand, model);
            case "SUV":
                return new SUV(brand, model);
            case "TRUCK":
                return new Truck(brand, model);
            default:
                return null;
        }
    }
}
