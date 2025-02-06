package carrentalsystem;

import java.util.Random;

// Класс CarFactory реализует паттерн "Фабрика"
public class CarFactory {
    public static Car createRandomCar() {
        Random random = new Random();
        int carType = random.nextInt(3);

        switch (carType) {
            case 0:
                return new Sedan("Toyota", "Camry");
            case 1:
                return new SUV("Ford", "Explorer");
            case 2:
                return new Truck("Chevrolet", "Silverado");
            default:
                throw new IllegalArgumentException("Invalid car type");
        }
    }

    // Добавим метод createCar
    public static Car createCar(String carType, String brand, String model) {
        switch (carType) {
            case "Sedan":
                return new Sedan(brand, model);
            case "SUV":
                return new SUV(brand, model);
            case "Truck":
                return new Truck(brand, model);
            default:
                return null;  // Если тип машины не подходит
        }
    }
}
