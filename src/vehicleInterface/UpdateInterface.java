package vehicleInterface;

import vehicles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// GUI class for updating vehicle details in the system
public class UpdateInterface extends JFrame {
    String vin; // Stores selected vehicle's VIN
    String type; // Stores selected vehicle's type
    public UpdateInterface(VehicleManager vm) {
        // Set up the window
        setTitle("IST Vehicle Care Solutions: Vehicle Update");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout());

        // Main panels
        JPanel defaultView = new JPanel(new GridLayout(16, 1, 4, 4));
        JPanel updateView = new JPanel(new GridLayout(24, 1, 4, 4));

        // Dropdown to select a vehicle by VIN
        JLabel selectVehicle = new JLabel("Select the VIN for the Vehicle to Update:");
        JComboBox vehicleComboBox = new JComboBox(getVehicles(vm.getVehicles()));
        JButton getInformation = new JButton("Retrieve Vehicle Info");

        defaultView.add(selectVehicle);
        defaultView.add(vehicleComboBox);
        defaultView.add(getInformation);

        // Labels and fields for vehicle attributes
        JLabel vinLabel = new JLabel();

        JLabel makeLabel = new JLabel();
        JTextField makeText = new JTextField();

        JLabel modelLabel = new JLabel();
        JTextField modelText = new JTextField();

        JLabel yearLabel = new JLabel();
        JTextField yearText = new JTextField();

        JLabel typeLabel = new JLabel();

        JLabel brandLabel = new JLabel();
        JTextField brandText = new JTextField();

        JLabel costEstimateLabel = new JLabel();
        JTextField costEstimateText = new JTextField();

        JLabel numberOfDoorsLabel = new JLabel();
        JTextField numberOfDoorsText = new JTextField();

        JLabel oilChangeCostLabel = new JLabel();
        JTextField oilChangeCostText = new JTextField();

        JLabel chainConditionLabel = new JLabel();
        JTextField chainConditionText = new JTextField();

        JLabel chainReplacementCostLabel = new JLabel();
        JTextField chainReplacementCostText = new JTextField();

        JLabel maxLoadLabel = new JLabel();
        JTextField maxLoadText = new JTextField();

        JLabel cargoInspectionCostLabel = new JLabel();
        JTextField cargoInspectionCostText = new JTextField();

        JButton updateVehicle = new JButton("Update Vehicle");
        JLabel isSuccess = new JLabel();

        // Button to retrieve existing vehicle information
        getInformation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(updateView);  // Remove previous update panel
                updateView.removeAll(); // Clear contents

                Vehicle vehicle = null;
                String selection = vehicleComboBox.getSelectedItem().toString();
                int stringIndex = selection.indexOf(" ");
                vin = selection.substring(stringIndex + 1);

                ArrayList<Vehicle> vehicles = vm.getVehicles();
                for (int i = 0; i < vehicles.size(); i++) {
                    if (vehicles.get(i).getVin().equals(vin)) {
                        vehicle = vehicles.get(i);
                    }
                }

                // Retrieve common vehicle attributes
                String make = vehicle.getMake();
                String model = vehicle.getModel();
                String year = vehicle.getYear();
                type = vehicle.getType();
                String brand = vehicle.getVehicleType();
                String costEstimate = vehicle.getCostEstimate();

                String numberOfDoors = null;
                String oilChangeCost = null;

                String chainCondition = null;
                String chainReplacementCost = null;

                String maxLoad = null;
                String cargoInspectionCost = null;

                // Set labels with current values
                vinLabel.setText("VIN: " + vin);
                makeLabel.setText("Current Make: " + make + ", enter a new Make:");
                modelLabel.setText("Current Model: " + model + ", enter a new Model:");
                yearLabel.setText("Current Year: " + year + ", enter a new Year:");
                typeLabel.setText("Type: " + type);
                brandLabel.setText("Current Brand: " + brand + ", enter a new Brand:");
                costEstimateLabel.setText("Current Cost Estimate: $" + costEstimate + ", enter a new Cost Estimate:");

                // Add fields to the update panel
                updateView.add(vinLabel);
                updateView.add(typeLabel);

                updateView.add(makeLabel);
                updateView.add(makeText);

                updateView.add(modelLabel);
                updateView.add(modelText);

                updateView.add(yearLabel);
                updateView.add(yearText);

                updateView.add(typeLabel);

                updateView.add(brandLabel);
                updateView.add(brandText);

                updateView.add(costEstimateLabel);
                updateView.add(costEstimateText);


                // If the vehicle type matches "Car", adds the labels and button required for modifying a car to the updateView
                if (type.equals("Car")) {
                    ArrayList<Car> cars = vm.getCars();
                    for (int i = 0; i < cars.size(); i++) {
                        if (cars.get(i).getVin().equals(vin)) {
                            // Gets the numberOfDoors and oilChangeCost from the car
                            numberOfDoors = cars.get(i).getNumDoors();
                            oilChangeCost = cars.get(i).getOilChangeCost();

                            // Updates the labels to include the current information and a prompt for new information
                            numberOfDoorsLabel.setText("Current Number of Doors: " + numberOfDoors + ", enter a new Number of Doors:");
                            oilChangeCostLabel.setText("Current Oil Change Cost: $" + oilChangeCost + ", enter a new Oil Change Cost:");

                            // Adds the car labels to the updateView panel
                            updateView.add(numberOfDoorsLabel);
                            updateView.add(numberOfDoorsText);

                            updateView.add(oilChangeCostLabel);
                            updateView.add(oilChangeCostText);
                        }
                    }
                }

                // If the vehicle type matches "Motorcycle", adds the labels and button required for modifying a motorcycle to the updateView
                if (type.equals("Motorcycle")) {
                    ArrayList<Motorcycle> motorcycles = vm.getMotorcycles();
                    for (int i = 0; i < motorcycles.size(); i++) {
                        if (motorcycles.get(i).getVin().equals(vin)) {
                            // Gets the chainCondition and the chainReplacementCost from the motorcycle
                            chainCondition = motorcycles.get(i).getChainCondition();
                            chainReplacementCost = motorcycles.get(i).getChainReplacementCost();

                            // Updates the labels to include the current information and a prompt for new information
                            chainConditionLabel.setText("Current Chain Condition: " + chainCondition + ", enter a new Chain Condition:");
                            chainReplacementCostLabel.setText("Current Chain Replacement Cost $" + chainReplacementCost + ", enter a new Chain Replacement Cost:");

                            // Adds the motorcycle labels to the updateView panel
                            updateView.add(chainConditionLabel);
                            updateView.add(chainConditionText);

                            updateView.add(chainReplacementCostLabel);
                            updateView.add(chainReplacementCostText);
                        }
                    }
                }

                // If the vehicle type matches "Truck", adds the labels and button required for modifying a truck to the updateView
                if (type.equals("Truck")) {
                    ArrayList<Truck> trucks = vm.getTrucks();
                    for (int i = 0; i < trucks.size(); i++) {
                        if (trucks.get(i).getVin().equals(vin)) {
                            // Gets the maxLoad and cargoInspectionCost from the truck
                            maxLoad = trucks.get(i).getMaxLoad();
                            cargoInspectionCost = trucks.get(i).getCargoInspectionCost();

                            // Updates the labels to include the current information and a prompt for new information
                            maxLoadLabel.setText("Current Max Load: " + maxLoad + ", enter a new Max Load:");
                            cargoInspectionCostLabel.setText("Current Cargo Inspection Cost: $" + cargoInspectionCost + ", enter a new Cargo Inspection Cost:");

                            // Adds the truck labels to the updateView panel
                            updateView.add(maxLoadLabel);
                            updateView.add(maxLoadText);

                            updateView.add(cargoInspectionCostLabel);
                            updateView.add(cargoInspectionCostText);
                        }
                    }
                }

                // Add update button and status label
                updateView.add(updateVehicle);
                updateView.add(isSuccess);

                // Display the update view
                add(updateView);

                // Updates the interface
                revalidate();
                repaint();
            }
        });

        // Button to perform update by deleting and re-adding the vehicle with new values
        updateVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieves the user-entered vehicle information from the JTextFields
                // The information for other vehicle types will go unused
                String make = makeText.getText();
                String model = modelText.getText();
                String year = yearText.getText();
                String brand = brandText.getText();
                String costEstimate = costEstimateText.getText();

                String numberOfDoors = numberOfDoorsText.getText();
                String oilChangeCost = oilChangeCostText.getText();

                String chainCondition = chainConditionText.getText();
                String chainReplacementCost = chainReplacementCostText.getText();

                String maxLoad = maxLoadText.getText();
                String cargoInspectionCost = cargoInspectionCostText.getText();

                // Re-add updated vehicle based on type
                // Indicates to the user if the vehicle was successfully added or not
                if (type.equals("Car")) {
                    if (!make.isBlank() && !model.isBlank() && !year.isBlank() && !brand.isBlank() && !costEstimate.isBlank()
                    && !numberOfDoors.isBlank() && !oilChangeCost.isBlank()) {
                        // Remove old vehicle entry first before re-adding the vehicle
                        vm.deleteVehicle(vin);
                        vm.addCar(vin, make, model, year, type, brand, costEstimate, numberOfDoors, oilChangeCost);
                        isSuccess.setText("Success");
                    }
                    else {
                        isSuccess.setText("Error, ensure all fields are entered");
                    }

                }
                if (type.equals("Motorcycle")) {
                    if (!make.isBlank() && !model.isBlank() && !year.isBlank() && !brand.isBlank() && !costEstimate.isBlank()
                            && !chainCondition.isBlank() && !chainReplacementCost.isBlank()) {
                        // Remove old vehicle entry first before re-adding the vehicle
                        vm.deleteVehicle(vin);
                        vm.addMotorcycle(vin, make, model, year, type, brand, costEstimate, chainCondition, chainReplacementCost);
                        isSuccess.setText("Success");
                    }
                    else {
                        isSuccess.setText("Error, ensure all fields are entered");
                    }
                }
                if (type.equals("Truck")) {
                    if (!make.isBlank() && !model.isBlank() && !year.isBlank() && !brand.isBlank() && !costEstimate.isBlank()
                            && !maxLoad.isBlank() && !cargoInspectionCost.isBlank()) {
                        // Remove old vehicle entry first before re-adding the vehicle
                        vm.deleteVehicle(vin);
                        vm.addTruck(vin, make, model, year, type, brand, costEstimate, maxLoad, cargoInspectionCost);
                        isSuccess.setText("Success");
                    }
                    else {
                        isSuccess.setText("Error, ensure all fields are entered");
                    }
                }

            }
        });

        // Add initial panel to frame and display it
        add(defaultView);
        show();
    }

    // Utility method to convert a list of Vehicle objects into an array of VIN strings
    public String[] getVehicles(ArrayList<Vehicle> vehicles) {
        ArrayList<String> vehicleInformationArrayList = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            vehicleInformationArrayList.add("VIN: " + vehicle.getVin());
        }

        String[] vehicleInformationArray = new String[vehicleInformationArrayList.size()];

        for (int i = 0; i < vehicleInformationArray.length; i++) {
            vehicleInformationArray[i] = vehicleInformationArrayList.get(i);
        }

        return vehicleInformationArray;
    }
}
