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

Object-Oriented Programming Concepts Used

Classes and Objects

The Car class is an abstract class, and Sedan, SUV, and Truck are objects of subclasses.

Encapsulation

Data fields in classes (e.g., brand, model, isAvailable) are private or protected.

Getter and setter methods control access to these fields (getBrand(), isAvailable(), etc.).

Inheritance

The Car class is a parent class, and Sedan, SUV, and Truck inherit from it.

They override methods such as rent() and getCarType().

Polymorphism

The getCarType() method is overridden in subclasses to return different car types.

The CarFactory class (not shown in code but assumed) uses polymorphism to instantiate different car types.

Method Overloading

The addUser method in UserManager is overloaded:

public void addUser(String username, String password, String role) { ... }
public void addUser(String username, String password) { ... }

Method Overriding

The rent() method is overridden in subclasses of Car to provide specific rental behavior.

@Override
public void rent() throws CarNotAvailableException { ... }

Exception Handling

Custom exception CarNotAvailableException is used to handle rental errors.

SQL exceptions are handled properly in DatabaseHandler and UserManager.

SOLID Principles

Single Responsibility Principle (SRP)

Each class has a single responsibility, e.g., UserManager only handles user-related tasks, RentalSystem only manages rental operations.

Open/Closed Principle (OCP)

The system is open for extension but closed for modification. New car types can be added by creating new subclasses of Car without modifying existing code.

Liskov Substitution Principle (LSP)

Subclasses (Sedan, SUV, Truck) correctly extend Car and do not break the expected behavior when used polymorphically.

Interface Segregation Principle (ISP)

The project follows ISP implicitly by avoiding large, unwieldy interfaces. Each class has a specific, clear role.

Dependency Inversion Principle (DIP)

RentalSystem depends on abstractions (Car) rather than specific implementations.

If a dependency injection mechanism were used, it would further adhere to this principle.

Design Patterns

Factory Pattern

A CarFactory (assumed but not provided) can be used to create car objects dynamically based on car type.

public class CarFactory {
    public static Car createCar(String type, String brand, String model) {
        return switch (type) {
            case "SUV" -> new SUV(brand, model);
            case "Sedan" -> new Sedan(brand, model);
            case "Truck" -> new Truck(brand, model);
            default -> null;
        };
    }
}

Data Management

Uses a PostgreSQL database to store cars and users.

Implements methods to fetch available cars, update car availability, add/remove users, and authenticate users.

Admin Panel (Optional Feature)

Allows admin users to manage cars and users.

Uses AdminPanel class to provide functionalities such as:

Adding and removing cars.

Adding and removing users.

Viewing rented cars.

Conclusion

This project effectively demonstrates key OOP principles, exception handling, database integration, SOLID principles, and a design pattern. Future improvements could include a GUI and advanced reporting features.
