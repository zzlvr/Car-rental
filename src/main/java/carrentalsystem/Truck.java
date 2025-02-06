package carrentalsystem;

public class Truck extends Car {
    public Truck(String brand, String model) {
        super(brand, model);
    }

    @Override
    public void rent() throws CarNotAvailableException {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Truck " + brand + " " + model + " successfully rented.");
        } else {
            throw new CarNotAvailableException("Truck " + brand + " " + model + " is not available.");
        }
    }

    @Override
    public String getCarType() {
        return "Truck";
    }
}
