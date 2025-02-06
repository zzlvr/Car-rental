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
}