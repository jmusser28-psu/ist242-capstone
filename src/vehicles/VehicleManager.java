package vehicles;

import database.DatabaseManager;
import util.InputValidation;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * VehicleManager is responsible for going through all of the vehicle related functions. It
 * includes adding, updating and deleting the vehicles, while also being able to calculate
 * the maintenance costs.
 */
public class VehicleManager {
    private final InputValidation valide = new InputValidation();
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

    /**
     * Prints the maintenance details for all of the vehicles.
     */
    public void printVehicleSummaries() {
        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).displayMaintenanceDetails();
        }
        for (int i = 0; i < motorcycles.size(); i++) {
            motorcycles.get(i).displayMaintenanceDetails();
        }
        for (int i = 0; i < trucks.size(); i++) {
            trucks.get(i).displayMaintenanceDetails();
        }
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
    public boolean deleteVehicle(String vin) {
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
                vehicles.remove(i);
            }

            if (type.equals("Car")) {
                for (int i = 0; i < cars.size(); i++) {
                    cars.remove(i);
                }
            }

            if (type.equals("Motorcycle")) {
                for (int i = 0; i < motorcycles.size(); i++) {
                    motorcycles.remove(i);
                }
            }

            if (type.equals("Truck")) {
                for (int i = 0; i < trucks.size(); i++) {
                    trucks.remove(i);
                }
            }

            dbmanager.deleteVehicle(vin, type);
        }

        return valid;
    }


    /**
     * Prompts the user to update the field and returns the updated information to subclasses.
     * @return
     */
    public String[] updateVehicle(String type) {
        String vin = "";
        String make = "";
        String model = "";
        String year = "";
        String vehicleType = "";
        String costEstimate = "";

        // Determines what type the vehicle is and displays relevant VINs.
        if (type.equalsIgnoreCase("Car")) {
            for (int i = 0; i < cars.size(); i++) {
                System.out.printf("VIN: %s, Make: %s, Type: %s\n", cars.get(i).getVin(),
                        cars.get(i).getMake(), cars.get(i).getType());
            }
        }

        if (type.equalsIgnoreCase("Motorcycle")) {
            for (int i = 0; i < motorcycles.size(); i++) {
                System.out.printf("VIN: %s, Make: %s, Type: %s\n", motorcycles.get(i).getVin(),
                        motorcycles.get(i).getMake(), motorcycles.get(i).getType());
            }
        }

        if (type.equalsIgnoreCase("Truck")) {
            for (int i = 0; i < trucks.size(); i++) {
                System.out.printf("VIN: %s, Make: %s, Type: %s\n", trucks.get(i).getVin(),
                        trucks.get(i).getMake(), trucks.get(i).getType());
            }
        }

        boolean typeValid = false;
        String userVin = "";

        // Ensures that the user enters a valid VIN based on the vehicle type
        while (!typeValid) {
            System.out.printf("Please enter the VIN of the %s you would like to modify: ", type);
            userVin = valide.line();

            if (type.equalsIgnoreCase("Car")) {
                for (Car car : cars) {
                    if (car.getVin().equalsIgnoreCase(userVin)) {
                        typeValid = true;
                    }
                }
            }

            if (type.equalsIgnoreCase("Motorcycle")) {
                for (Motorcycle motorcycle : motorcycles) {
                    if (motorcycle.getVin().equalsIgnoreCase(userVin)) {
                        typeValid = true;
                    }
                }
            }

            if (type.equalsIgnoreCase("Truck")) {
                for (Truck truck : trucks) {
                    if (truck.getVin().equalsIgnoreCase(userVin)) {
                        typeValid = true;
                    }
                }
            }

            if (!typeValid) {
                System.out.printf("Please enter a valid %s VIN\n", type);
            }
        }

        // Gets the information from the vehicles array about the specific vehicle based on VIN
        // It iterates through all vehicles and updates the variables on a match
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getVin().equalsIgnoreCase(userVin)) {
                vin = vehicles.get(i).getVin();
                make = vehicles.get(i).getMake();
                model = vehicles.get(i).getModel();
                year = vehicles.get(i).getYear();
                type = vehicles.get(i).getType();
                vehicleType = vehicles.get(i).getVehicleType();
                costEstimate = vehicles.get(i).getCostEstimate();
            }
        }

        dbmanager.deleteVehicle(vin, type);

        // Updates most information of a vehicle, however assumes the type and VIN will be constant.
        boolean loopDone = false;
        byte choice = 0;
        while (!loopDone) {
            System.out.println("Modify Vehicle Information");
            System.out.println("0.) Exit");
            System.out.println("1.) Make");
            System.out.println("2.) Model");
            System.out.println("3.) Year");
            System.out.println("4.) Vehicle Brand Type");
            System.out.println("5.) Cost Estimate");
            System.out.print("What would you like to modify (0-5): ");
            choice = valide.validateByte();

            if (choice == 0) {
                loopDone = true;
            }
            else if (choice == 1) {
                System.out.print("Please enter a new make: ");
                make = valide.line();
            }
            else if (choice == 2) {
                System.out.print("Please enter a new model: ");
                model = valide.line();
            }
            else if (choice == 3) {
                System.out.print("Please enter a new year: ");
                year = valide.line();
            }
            else if (choice == 4) {
                System.out.print("Please enter a new brand type: ");
                vehicleType = valide.line();
            }
            else if (choice == 5) {
                System.out.print("Please enter a new cost estimate: ");
                costEstimate = valide.line();
            }
        }

        String[] info = {vin, make, model, year, vehicleType, costEstimate};

        return info;
    }

    // Updates the car entry and vehicle entries by calling updateVehicle method
    // Does so by removing the vehicle entry and thus the entry for the subclass and then re-adds it
    // This way there are no SQL errors when attempting to update an entry
    public void updateCar() {
        // Sets a vehicle type and then gets the info from the updateVehicle class
        String type = "Car";
        String[] info = updateVehicle(type);

        // Sets the vehicle info to strings
        String vin = info[0];
        String make = info[1];
        String model = info[2];
        String year = info[3];
        String vehicleType = info[4];
        String costEstimate = info[5];

        boolean loopDone = false;
        byte choice = 0;

        String numberOfDoors = "";
        String oilChangeCost = "";

        // Adds vehicle type specific variables
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getVin().equals(vin)) {
                numberOfDoors = cars.get(i).getNumDoors();
                oilChangeCost = cars.get(i).getOilChangeCost();
            }
        }

        // Allows the user to customize the vehicle specific variables until the user chooses to exit
        while (!loopDone) {
            System.out.println("Modify Car Information");
            System.out.println("0.) Exit");
            System.out.println("1.) Number of Doors");
            System.out.println("2.) Oil Change Cost");
            System.out.print("What would you like to modify (0-2): ");
            choice = valide.validateByte();

            if (choice == 0) {
                loopDone = true;
            }
            else if (choice == 1) {
                System.out.print("Please enter a new number of doors: ");
                numberOfDoors = valide.line();
            }
            else if (choice == 2) {
                System.out.print("Please enter a new oil change cost: ");
                oilChangeCost = valide.line();
            }
            else {
                System.out.printf("Invalid input %d", choice);
            }

        }

        // Adds a new specific vehicle type and as consequence, a vehicle too.
        // This method is mostly the same for the other two specific vehicle methods
        // Those just have different vehicle specific variables
        dbmanager.addCar(vin, make, model, year, type, vehicleType, costEstimate, numberOfDoors, oilChangeCost);

    }


    // Updates the car entry and vehicle entries by calling updateVehicle method
    // Does so by removing the vehicle entry and thus the entry for the subclass and then re-adds it
    // This way there are no SQL errors when attempting to update an entry
    public void updateMotorcycle() {
        String type = "Motorcycle";
        String[] info = updateVehicle(type);

        String vin = info[0];
        String make = info[1];
        String model = info[2];
        String year = info[3];
        String vehicleType = info[4];
        String costEstimate = info[5];

        boolean loopDone = false;
        byte choice = 0;

        String chainCondition = "";
        String chainReplacementCost = "";

        for (int i = 0; i < motorcycles.size(); i++) {
            if (motorcycles.get(i).getVin().equals(vin)) {
                chainCondition = motorcycles.get(i).getChainCondition();
                chainReplacementCost = motorcycles.get(i).getChainReplacementCost();
            }
        }

        while (!loopDone) {
            System.out.println("Modify Motorcycle Information");
            System.out.println("0.) Exit");
            System.out.println("1.) Chain Condition");
            System.out.println("2.) Chain Replacement Cost");
            System.out.print("What would you like to modify (0-3): ");
            choice = valide.validateByte();

            if (choice == 0) {
                loopDone = true;
            }
            else if (choice == 1) {
                System.out.println("What is the condition of your chain? ");
                System.out.println("1.) Poor");
                System.out.println("2.) Fair");
                System.out.println("3.) Good");
                System.out.println("4.) Excellent");

                boolean chainConditionRun = true;
                byte userChoice = 0;

                while (chainConditionRun) {
                    System.out.print("Please pick a choice (1-4): ");
                    userChoice = valide.validateByte();

                    if (userChoice == 1) {
                        chainCondition = "Poor";
                        chainConditionRun = false;
                    }
                    else if (userChoice == 2) {
                        chainCondition = "Fair";
                        chainConditionRun = false;
                    }
                    else if (userChoice == 3) {
                        chainCondition = "Good";
                        chainConditionRun = false;
                    }
                    else if (userChoice == 4) {
                        chainCondition = "Excellent";
                        chainConditionRun = false;
                    }
                    else {
                        System.out.printf("Invalid choice %d\n", userChoice);
                    }
                }
            }
            else if (choice == 2) {
                System.out.print("Please enter a new chain replacement cost: ");
                chainReplacementCost = valide.line();
            }
            else {
                System.out.printf("Invalid input %d", choice);
            }

        }

        dbmanager.addMotorcycle(vin, make, model, year, type, vehicleType, costEstimate, chainCondition, chainReplacementCost);

    }

    // Updates the car entry and vehicle entries by calling updateVehicle method
    // Does so by removing the vehicle entry and thus the entry for the subclass and then re-adds it
    // This way there are no SQL errors when attempting to update an entry
    public void updateTruck() {
        String type = "Truck";
        String[] info = updateVehicle(type);

        String vin = info[0];
        String make = info[1];
        String model = info[2];
        String year = info[3];
        String vehicleType = info[4];
        String costEstimate = info[5];

        boolean loopDone = false;
        byte choice = 0;

        String maxLoad = "";
        String cargoInspectionCost = "";

        for (int i = 0; i < trucks.size(); i++) {
            if (trucks.get(i).getVin().equals(vin)) {
                maxLoad = trucks.get(i).getMaxLoad();
                cargoInspectionCost = trucks.get(i).getCargoInspectionCost();
            }
        }

        while (!loopDone) {
            System.out.println("Modify Car Information");
            System.out.println("0.) Exit");
            System.out.println("1.) Max Load");
            System.out.println("2.) Cargo Inspection Cost");
            System.out.print("What would you like to modify (0-3): ");
            choice = valide.validateByte();

            if (choice == 0) {
                loopDone = true;
            }
            else if (choice == 1) {
                System.out.print("Please enter a new max load capacity: ");
                maxLoad = valide.line();
            }
            else if (choice == 2) {
                System.out.print("Please enter a new cargo inspection cost: ");
                cargoInspectionCost = valide.line();
            }
            else {
                System.out.printf("Invalid input %d", choice);
            }

        }

        dbmanager.addTruck(vin, make, model, year, type, vehicleType, costEstimate, maxLoad, cargoInspectionCost);

    }

    // Calculates the average maintenance for all vehicles
    public double maintenanceAverageVehicle() {
        double average = 0.0;
        for (Vehicle vehicle : vehicles) {
            average += Double.parseDouble(vehicle.getCostEstimate());
        }
        average /= vehicles.size();

        return average;
    }

    // Calculates the average maintenance for cars
    public double maintenanceAverageCar() {
        double average = 0.0;
        for (Car car : cars) {
            average += Double.parseDouble(car.getCostEstimate());
        }
        average /= cars.size();

        return average;
    }

    // Calculates the average maintenance for motorcycles
    public double maintenanceAverageMotorcycle() {
        double average = 0.0;
        for (Motorcycle motorcycle : motorcycles) {
            average += Double.parseDouble(motorcycle.getCostEstimate());
        }
        average /= motorcycles.size();

        return average;
    }

    // Calculates the average maintenance for trucks
    public double maintenanceAverageTruck() {
        double average = 0.0;
        for (Truck truck : trucks) {
            average += Double.parseDouble(truck.getCostEstimate());
        }
        average /= trucks.size();

        return average;
    }

    public Vehicle findVehicle(String vin) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin().equals(vin)) {
                return vehicle;
            }
        }
        return null;
    }

    public Car findCar(String vin) {
        for (Car car : cars) {
            if (car.getVin().equals(vin)) {
                return car;
            }
        }
        return null;
    }

    public Motorcycle findMotorcycle(String vin) {
        for (Motorcycle motorcycle : motorcycles) {
            if (motorcycle.getVin().equals(vin)) {
                return motorcycle;
            }
        }
        return null;
    }

    public Truck findTruck(String vin) {
        for (Truck truck : trucks) {
            if (truck.getVin().equals(vin)) {
                return truck;
            }
        }
        return null;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public ArrayList<Motorcycle> getMotorcycles() {
        return motorcycles;
    }

    public ArrayList<Truck> getTrucks() {
        return trucks;
    }
}
