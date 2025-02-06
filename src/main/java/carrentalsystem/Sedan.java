package carrentalsystem;

public class Sedan extends Car {
    public Sedan(String brand, String model) {
        super(brand, model);
    }

    @Override
    public void rent() throws CarNotAvailableException {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Sedan " + brand + " " + model + " successfully rented.");
        } else {
            throw new CarNotAvailableException("Sedan " + brand + " " + model + " is not available.");
        }
    }

    @Override
    public String getCarType() {
        return "Sedan";
    }
}
