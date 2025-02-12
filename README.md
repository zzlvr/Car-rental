Car Rental System

Project Description

The Car Rental System is a Java-based application that allows users to rent and return cars. The system integrates with a PostgreSQL database for data management and supports user authentication with different roles (admin and customer). The project demonstrates key object-oriented programming concepts, exception handling, SOLID principles, and design patterns.

Main Classes and Their Roles

1. Car (Abstract Class)

Represents a generic car.

Implements encapsulation through private fields and getter/setter methods.

Demonstrates inheritance through child classes (Sedan, SUV, Truck).

Supports polymorphism via method overriding.

2. Sedan, SUV, Truck (Concrete Classes)

Extend Car and provide specific implementations for the getCarType() method.

3. RentalSystem

Manages the inventory of cars.

Connects to the PostgreSQL database.

Implements data management methods (addCar(), getAvailableCars(), updateCarAvailability(), removeCar()).

4. User

Encapsulates user data such as ID, username, password, and role.

5. UserManager

Handles user authentication.

Provides methods for adding and removing users from the database.

6. DatabaseHandler

Establishes a connection to the PostgreSQL database.

7. Main

Entry point of the program.

Handles user interaction and menu navigation.

8. AdminPanel

Provides functionalities for administrators to manage users and cars.

Object-Oriented Programming Concepts Demonstrated

1. Classes and Objects

Car myCar = new Sedan("Toyota", "Camry");
System.out.println(myCar.getCarInfo());

2. Encapsulation

public class User {
    private String username;
    private String password;
    
    public String getUsername() {
        return username;
    }
    
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}

3. Inheritance

public class Sedan extends Car {
    public Sedan(String brand, String model) {
        super(brand, model);
    }
}

4. Polymorphism (Method Overriding)

@Override
public String getCarType() {
    return "SUV";
}

5. Method Overloading

public void addUser(String username, String password) {
    addUser(username, password, "customer");
}

6. Exception Handling

public void rent() throws CarNotAvailableException {
    if (!isAvailable) {
        throw new CarNotAvailableException("Car is not available.");
    }
    isAvailable = false;
}

7. SOLID Principles

Single Responsibility Principle (SRP): Each class has a single responsibility (e.g., UserManager handles user-related tasks, RentalSystem handles cars).

Open/Closed Principle (OCP): The Car class allows extension (new car types) without modifying existing code.

Liskov Substitution Principle (LSP): A Sedan, SUV, or Truck object can replace a Car object without breaking the system.

Interface Segregation Principle (ISP): Not applied explicitly but could be improved.

Dependency Inversion Principle (DIP): RentalSystem depends on abstractions (Car and DatabaseHandler).

8. Design Patterns

Factory Pattern (Used for creating car objects dynamically)

public class CarFactory {
    public static Car createCar(String type, String brand, String model) {
        switch (type.toLowerCase()) {
            case "sedan": return new Sedan(brand, model);
            case "suv": return new SUV(brand, model);
            case "truck": return new Truck(brand, model);
            default: return null;
        }
    }
}

Data Management

The system uses PostgreSQL to store and manage data.

Car table stores car details (brand, model, car_type, is_available).

User table stores user credentials (id, username, password, role).

Methods like getAvailableCars(), updateCarAvailability(), and authenticateUser() interact with the database.

Example Query

SELECT * FROM cars WHERE is_available = TRUE;

Admin Panel (Optional Feature)

The admin panel provides:

Car management (add, remove cars).

User management (add, remove users).

Conclusion

This project effectively demonstrates key OOP principles, exception handling, database integration, SOLID principles, and a design pattern. Future improvements could include a GUI and advanced reporting features.
