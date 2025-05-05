package vehicles;

import database.DatabaseManager;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * VehicleManager is responsible for going through all the vehicle related functions. It
 * includes adding, updating and deleting the vehicles, while also being able to calculate
 * the maintenance costs.
 */
public class VehicleManager {
    // Creates fields for DatabaseManager, Connection, an ArrayList of type Vehicle, an ArrayList of type Car,
    // an ArrayList of type Motorcycle, and an ArrayList of type Truck.
    private DatabaseManager dbmanager;
    private Connection connection;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Car> cars;
    private ArrayList<Motorcycle> motorcycles;

    private ArrayList<Truck> trucks;
    public VehicleManager(Connection connection) {
        // Sets the connection field to the connection passed to the VehicleManager
        this.connection = connection;
        // Creates a DatabaseManager for interacting with the database
        dbmanager = new DatabaseManager(this.connection);

        // Gets all the vehicle types and adds them to their respective ArrayLists
        // This is done since there are private field variables for the specialized vehicle types, and we need to interact with them iteratively
        vehicles = dbmanager.getVehicles();
        cars = dbmanager.getCars();
        motorcycles = dbmanager.getMotorcycles();
        trucks = dbmanager.getTrucks();
    }

    public void updateManager() {
        // Updates the ArrayLists after a modification has occurred within the database, this ensures that no errors occur.
        vehicles = dbmanager.getVehicles();
        cars = dbmanager.getCars();
        motorcycles = dbmanager.getMotorcycles();
        trucks = dbmanager.getTrucks();
    }
    public void addCar(String vin, String make, String model, String year, String type, String vehicleType,
                       String costEstimate, String numberOfDoors, String oilChangeCost) {
        // Adds a car to the database
        dbmanager.addCar(vin, make, model, year, type, vehicleType, costEstimate, numberOfDoors, oilChangeCost);
        updateManager();
    }

    public void addMotorcycle(String vin, String make, String model, String year, String type, String vehicleType,
                       String costEstimate, String chainCondition, String chainReplacementCost) {
        // Adds a motorcycle to the database
        dbmanager.addMotorcycle(vin, make, model, year, type, vehicleType, costEstimate, chainCondition, chainReplacementCost);
        updateManager();
    }

    public void addTruck(String vin, String make, String model, String year, String type, String vehicleType,
                              String costEstimate, String maxLoad, String cargoInspectionCost) {
        // Adds a truck to the database
        dbmanager.addTruck(vin, make, model, year, type, vehicleType, costEstimate, maxLoad, cargoInspectionCost);
        updateManager();
    }

    /**
     * Deletes a vehicle based off of its VIN
     */
    public void deleteVehicle(String vin) {
        // Declares a variable for type, which we need to use to determine the type of the car
        String type = "";

        // Determines the type of the vehicle by iterating through the vehicles ArrayList and finding the type of vehicle associated
        // with the VIN
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin().equals(vin)) {
                type = vehicle.getType();
            }
        }

        // Deletes the vehicle in the database by passing through the vehicle identifier number and type
        dbmanager.deleteVehicle(vin, type);
        // Calls the updateManager method to update the ArrayLists
        updateManager();
    }

    public Vehicle findVehicle(String vin) {
       //Searches for a vehicle in the 'vehicle' by its VIN and returns it if it is found.
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin().equals(vin)) {
                return vehicle;
            }
        }
        return null;
    }

    public Car findCar(String vin) {
        //Searches for a car in the 'cars' list by its VIN and returns it if it is found.
        for (Car car : cars) {
            if (car.getVin().equals(vin)) {
                return car;
            }
        }
        return null;
    }

    public Motorcycle findMotorcycle(String vin) {
        //Searches for a motorcycle in the 'motorcycle' list by its VIN and returns it if it is found.
        for (Motorcycle motorcycle : motorcycles) {
            if (motorcycle.getVin().equals(vin)) {
                return motorcycle;
            }
        }
        return null;
    }

    public Truck findTruck(String vin) {
        //Searches for a truck in the 'truck' list by its VIN and returns if found.

        for (Truck truck : trucks) {
            if (truck.getVin().equals(vin)) {
                return truck;
            }
        }
        return null;
    }

    // Getter for the vehicles ArrayList
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    // Getter for the cars ArrayList
    public ArrayList<Car> getCars() {
        return cars;
    }

    // Getter for the motorcycles ArrayList
    public ArrayList<Motorcycle> getMotorcycles() {
        return motorcycles;
    }

    // Getter for the trucks ArrayList
    public ArrayList<Truck> getTrucks() {
        return trucks;
    }

    // Gets the average maintenance cost of cars.
    public double getCarAverageMaintenanceCost() {
        double average = 0.0;
        for (Car car : cars) {
            average += Double.parseDouble(car.getCostEstimate());
        }

        average /= cars.size();

        return average;
    }

    // Gets the average maintenance cost of motorcycles.
    public double getMotorcycleAverageMaintenanceCost() {
        double average = 0.0;
        for (Motorcycle motorcycle : motorcycles) {
            average += Double.parseDouble(motorcycle.getCostEstimate());
        }

        average /= motorcycles.size();

        return average;
    }

    // Gets the average maintenance cost of trucks.
    public double getTruckAverageMaintenanceCost() {
        double average = 0.0;
        for (Truck truck : trucks) {
            average += Double.parseDouble(truck.getCostEstimate());
        }

        average /= trucks.size();

        return average;
    }

    // Determines if the vehicle exists so that we do not mistakenly add a new vehicle with an existing VIN
    // This would cause an SQL if we did not check
    public boolean vehicleExists(String vin) {
        boolean doesExist = false;

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin().equals(vin)) {
                doesExist = true;
            }
        }

        return doesExist;
    }
}
