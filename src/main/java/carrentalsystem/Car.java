package carrentalsystem;

public abstract class Car {
    protected String brand;
    protected String model;
    protected boolean isAvailable = true;
    protected boolean wasRented = false;  // Новое поле

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Метод для изменения доступности автомобиля
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    // Новый метод для отслеживания, был ли автомобиль арендован
    public boolean wasRented() {
        return wasRented;
    }

    // Новый метод для изменения статуса аренды
    public void setWasRented(boolean wasRented) {
        this.wasRented = wasRented;
    }

    public abstract String getCarType();

    public String getCarInfo() {
        return getCarType() + " | " + brand + " | " + model;
    }

    public abstract void rent() throws CarNotAvailableException;

    public void returnCar() {
        isAvailable = true;
    }

    public static class CarNotAvailableException extends Exception {
        public CarNotAvailableException(String message) {
            super(message);
        }
    }
}