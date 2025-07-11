package vehicles;

public class Truck extends Vehicle {

    // Fields for truck information contained in the database
    private String maxLoad;
    private String cargoInspectionCost;

    /**
     * Constructs a Truck object with general vehicle info and truck-specific details.
     */
    public Truck(String vin, String make, String model, String year, String type, String vehicle_type, String costEstimate,
                 String maxLoad, String cargoInspectionCost) {
        super(vin, make, model, year, type, vehicle_type, costEstimate);
        this.maxLoad = maxLoad;
        this.cargoInspectionCost = cargoInspectionCost;
    }

    // Getters for maxLoad and cargoInspectionCost
    public String getMaxLoad() {
        return maxLoad;
    }

    public String getCargoInspectionCost() {
        return cargoInspectionCost;
    }

    /**
     * Overrides the Vehicle display method to show truck-specific maintenance details.
     */
    @Override
    public String displayMaintenanceDetails() {
        String maintenace = ("Truck: " + super.getMake() + " " + super.getModel() +
                " | VIN: " + super.getVin() +
                " | Maintenance Cost: $" + super.getCostEstimate() +
                " | Cargo Inspection Cost: $" + (cargoInspectionCost));

        return maintenace;
    }
}
