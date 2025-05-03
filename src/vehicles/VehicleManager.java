package vehicles;

import database.DatabaseManager;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * VehicleManager is responsible for going through all of the vehicle related functions. It
 * includes adding, updating and deleting the vehicles, while also being able to calculate
 * the maintenance costs.
 */
public class VehicleManager {
    private DatabaseManager dbmanager;
    private Connection connection;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Car> cars;
    private ArrayList<Motorcycle> motorcycles;

    private ArrayList<Truck> trucks;
    public VehicleManager(Connection connection) {
        this.connection = connection;
        dbmanager = new DatabaseManager(this.connection);

        vehicles = dbmanager.getVehicles();
        cars = dbmanager.getCars();
        motorcycles = dbmanager.getMotorcycles();
        trucks = dbmanager.getTrucks();
    }

    public void updateManager() {
        vehicles = dbmanager.getVehicles();
        cars = dbmanager.getCars();
        motorcycles = dbmanager.getMotorcycles();
        trucks = dbmanager.getTrucks();
    }
    public void addCar(String vin, String make, String model, String year, String type, String vehicleType,
                       String costEstimate, String numberOfDoors, String oilChangeCost) {
        dbmanager.addCar(vin, make, model, year, type, vehicleType, costEstimate, numberOfDoors, oilChangeCost);
    }

    public void addMotorcycle(String vin, String make, String model, String year, String type, String vehicleType,
                       String costEstimate, String chainCondition, String chainReplacementCost) {
        dbmanager.addMotorcycle(vin, make, model, year, type, vehicleType, costEstimate, chainCondition, chainReplacementCost);
    }

    public void addTruck(String vin, String make, String model, String year, String type, String vehicleType,
                              String costEstimate, String maxLoad, String cargoInspectionCost) {
        dbmanager.addTruck(vin, make, model, year, type, vehicleType, costEstimate, maxLoad, cargoInspectionCost);
    }

    /**
     * Deletes a vehicle based off of its VIN
     */
    public void deleteVehicle(String vin) {
        updateManager();
        String type = "";
        boolean valid = false;

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin().equals(vin)) {
                type = vehicle.getType();
                valid = true;
            }
        }

        if (valid) {
            for (int i = 0; i < vehicles.size(); i++) {
                if (vehicles.get(i).getVin().equals(vin)) {
                    vehicles.remove(i);
                }
            }

            if (type.equals("Car")) {
                for (int i = 0; i < cars.size(); i++) {
                    if (cars.get(i).getVin().equals(vin)) {
                        cars.remove(i);
                    }
                }
            }

            if (type.equals("Motorcycle")) {
                for (int i = 0; i < motorcycles.size(); i++) {
                    if (motorcycles.get(i).getVin().equals(vin)) {
                        motorcycles.remove(i);
                    }
                }
            }

            if (type.equals("Truck")) {
                for (int i = 0; i < trucks.size(); i++) {
                    if (trucks.get(i).getVin().equals(vin)) {
                        trucks.remove(i);
                    }
                }
            }

            dbmanager.deleteVehicle(vin, type);
        }
    }

    public Vehicle findVehicle(String vin) {
        updateManager();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin().equals(vin)) {
                return vehicle;
            }
        }
        return null;
    }

    public Car findCar(String vin) {
        updateManager();
        for (Car car : cars) {
            if (car.getVin().equals(vin)) {
                return car;
            }
        }
        return null;
    }

    public Motorcycle findMotorcycle(String vin) {
        updateManager();
        for (Motorcycle motorcycle : motorcycles) {
            if (motorcycle.getVin().equals(vin)) {
                return motorcycle;
            }
        }
        return null;
    }

    public Truck findTruck(String vin) {
        updateManager();
        for (Truck truck : trucks) {
            if (truck.getVin().equals(vin)) {
                return truck;
            }
        }
        return null;
    }

    public ArrayList<Vehicle> getVehicles() {
        updateManager();
        return vehicles;
    }

    public ArrayList<Car> getCars() {
        updateManager();
        return cars;
    }

    public ArrayList<Motorcycle> getMotorcycles() {
        updateManager();
        return motorcycles;
    }

    public ArrayList<Truck> getTrucks() {
        updateManager();
        return trucks;
    }

    public boolean vehicleExists(String vin) {
        updateManager();
        boolean doesExist = false;

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin().equals(vin)) {
                doesExist = true;
            }
        }

        return doesExist;
    }
}
