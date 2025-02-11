package carrentalsystem;

public class CarFactory {
    public static Car createCar(String carType, String brand, String model) {
        switch (carType) {
            case "Sedan":
                return new Sedan(brand, model);
            case "SUV":
                return new SUV(brand, model);
            case "Truck":
                return new Truck(brand, model);
            default:
                throw new IllegalArgumentException("Unknown car type: " + carType);
        }
    }
}
