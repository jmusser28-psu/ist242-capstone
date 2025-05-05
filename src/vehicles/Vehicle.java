package vehicles;

/**
 * Represents a general Vehicle with shared attributes.
 * Serves as the superclass for Car, Truck, and Motorcycle.
 */
public class Vehicle {

    // Fields for general vehicle information contained in the database
    private String vin;
    private String make;
    private String model;
    private String year;
    private String type;
    private String vehicle_type;
    private String costEstimate;


    /**
     * Constructs a Vehicle with basic vehicle information.
     */

    public Vehicle(String vin, String make, String model, String year, String type, String vehicle_type, String costEstimate) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.type = type;
        this.vehicle_type = vehicle_type;
        this.costEstimate = costEstimate;
        this.year = year;
    }

    // Getters for vehicle attributes
    public String getVin(){
        return vin;
    }
    public String getMake(){
        return make;
    }

    public String getModel(){
        return model;
    }
    public String getYear(){
        return year;
    }

    public String getType(){
        return type;
    }

    public String getVehicleType(){
        return vehicle_type;
    }

    public String getCostEstimate(){
        return costEstimate;
    }

    /**
     * Displays general maintenance details for the vehicle.
     * This method is designed to be overridden by subclasses.
     */
    public String displayMaintenanceDetails() {
        String maintenance = ("Vehicle: " + vehicle_type + " " + model +
                " | VIN: " + vin +
                " | Maintenance Cost: $" + costEstimate);

        return maintenance;
    }

}
