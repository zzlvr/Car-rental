package carrentalsystem;

public class SUV extends Car {
    public SUV(String brand, String model) {
        super(brand, model);
    }

    @Override
    public void rent() throws CarNotAvailableException {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("SUV " + brand + " " + model + " successfully rented.");
        } else {
            throw new CarNotAvailableException("SUV " + brand + " " + model + " is not available.");
        }
    }

    @Override
    public String getCarType() {
        return "SUV";
    }
}
