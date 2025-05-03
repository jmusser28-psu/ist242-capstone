package vehicleInterface;

import vehicles.VehicleManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddInterface extends JFrame {
    public AddInterface(VehicleManager vm) {
        setTitle("IST Vehicle Car Solutions: Vehicle Add");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout());

        JPanel carPanel = new JPanel(new GridLayout(16, 16, 4, 4));
        JLabel numberOfDoorsLabel = new JLabel("Number of Doors");
        JTextField numberOfDoorsText = new JTextField();
        JLabel oilChangeCostLabel = new JLabel("Oil Change Cost");
        JTextField oilChangeCostText = new JTextField();
        JButton addCarButton = new JButton("Add Car");
        JLabel isSuccessCar = new JLabel("");

        carPanel.add(numberOfDoorsLabel);
        carPanel.add(numberOfDoorsText);
        carPanel.add(oilChangeCostLabel);
        carPanel.add(oilChangeCostText);
        carPanel.add(addCarButton);
        carPanel.add(isSuccessCar);

        JPanel motorcyclePanel = new JPanel(new GridLayout(16, 16, 4, 4));
        JLabel chainConditionLabel = new JLabel("Chain Condition");
        JTextField chainConditionText = new JTextField();
        JLabel chainReplacementCostLabel = new JLabel("Chain Replacement Cost");
        JTextField chainReplacementCostText = new JTextField();
        JButton addMotorcycleButton = new JButton("Add Motorcycle");
        JLabel isSuccessMotorcycle = new JLabel("");

        motorcyclePanel.add(chainConditionLabel);
        motorcyclePanel.add(chainConditionText);
        motorcyclePanel.add(chainReplacementCostLabel);
        motorcyclePanel.add(chainReplacementCostText);
        motorcyclePanel.add(addMotorcycleButton);
        motorcyclePanel.add(isSuccessMotorcycle);

        JPanel truckPanel = new JPanel(new GridLayout(16, 16, 4, 4));
        JLabel maxLoadLabel = new JLabel("Max Cargo Load");
        JTextField maxLoadText = new JTextField();
        JLabel cargoInspectionCostLabel = new JLabel("Cargo Inspection Cost");
        JTextField cargoInspectionCostText = new JTextField();
        JButton addTruckButton = new JButton("Add Truck");
        JLabel isSuccessTruck = new JLabel("");

        truckPanel.add(maxLoadLabel);
        truckPanel.add(maxLoadText);
        truckPanel.add(cargoInspectionCostLabel);
        truckPanel.add(cargoInspectionCostText);
        truckPanel.add(addTruckButton);
        truckPanel.add(isSuccessTruck);

        JLabel vinLabel = new JLabel("VIN");
        JTextField vinText = new JTextField();

        JLabel makeLabel = new JLabel("Make");
        JTextField makeText = new JTextField();

        JLabel modelLabel = new JLabel("Model");
        JTextField modelText = new JTextField();

        JLabel yearLabel = new JLabel("Year");
        JTextField yearText = new JTextField();

        // Different name for Vehicle_Type, we think it makes more sense when prompting the user
        JLabel brandLabel = new JLabel("Vehicle Brand");
        JTextField brandText = new JTextField();

        JLabel costEstimateLabel = new JLabel("Cost Estimate");
        JTextField costEstimateText = new JTextField();

        JPanel defaultView = new JPanel(new GridLayout(16, 16, 4, 4));

        JButton carButton = new JButton("Car");
        carButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(carPanel);
                remove(motorcyclePanel);
                remove(truckPanel);
                add(carPanel);
                revalidate();
                repaint();
            }
        });

        JButton motorcycleButton = new JButton("Motorcycle");
        motorcycleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(carPanel);
                remove(motorcyclePanel);
                remove(truckPanel);
                add(motorcyclePanel);
                revalidate();
                repaint();
            }
        });

        JButton truckButton = new JButton("Truck");
        truckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(carPanel);
                remove(motorcyclePanel);
                remove(truckPanel);
                add(truckPanel);
                revalidate();
                repaint();
            }
        });

        addCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vin = vinText.getText();
                String make = makeText.getText();
                String model = modelText.getText();
                String year = yearText.getText();
                String vehicleType = brandText.getText();
                String costEstimate = costEstimateText.getText();
                String type = "Car";

                String numberOfDoors = numberOfDoorsText.getText();
                String oilChangeCost = oilChangeCostText.getText();

                if (!vin.isBlank() && !make.isBlank() && !model.isBlank() && !year.isBlank() && !vehicleType.isBlank() &&
                !costEstimate.isBlank() && !numberOfDoors.isBlank() && !oilChangeCost.isBlank() && !(vm.vehicleExists(vin))) {
                    vm.addCar(vin, make, model, year, type, vehicleType, costEstimate, numberOfDoors, oilChangeCost);
                    isSuccessCar.setText("Success");
                }
                else {
                    isSuccessCar.setText("Error, ensure all fields are entered, and the VIN does not exist");
                }

            }
        });

        addMotorcycleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vin = vinText.getText();
                String make = makeText.getText();
                String model = modelText.getText();
                String year = yearText.getText();
                String vehicleType = brandText.getText();
                String costEstimate = costEstimateText.getText();
                String type = "Motorcycle";

                String chainCondition = chainConditionText.getText();
                String chainReplacementCost = chainReplacementCostText.getText();

                if (!vin.isBlank() && !make.isBlank() && !model.isBlank() && !year.isBlank() && !vehicleType.isBlank() &&
                        !costEstimate.isBlank() && !chainCondition.isBlank() && !chainReplacementCost.isBlank() && !(vm.vehicleExists(vin))) {
                    vm.addMotorcycle(vin, make, model, year, type, vehicleType, costEstimate, chainCondition, chainReplacementCost);
                    isSuccessMotorcycle.setText("Success");
                }
                else {
                    isSuccessMotorcycle.setText("Error, ensure all fields are entered, and the VIN does not exist");
                }

            }
        });

        addTruckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vin = vinText.getText();
                String make = makeText.getText();
                String model = modelText.getText();
                String year = yearText.getText();
                String vehicleType = brandText.getText();
                String costEstimate = costEstimateText.getText();
                String type = "Truck";

                String maxLoad = maxLoadText.getText();
                String cargoInspectionCost = cargoInspectionCostText.getText();

                if (!vin.isBlank() && !make.isBlank() && !model.isBlank() && !year.isBlank() && !vehicleType.isBlank() &&
                        !costEstimate.isBlank() && !maxLoad.isBlank() && !cargoInspectionCost.isBlank() && !(vm.vehicleExists(vin))) {
                    vm.addTruck(vin, make, model, year, type, vehicleType, costEstimate, maxLoad, cargoInspectionCost);
                    isSuccessTruck.setText("Success");
                }
                else {
                    isSuccessTruck.setText("Error, ensure all fields are entered, and the VIN does not exist");
                }

            }
        });

        defaultView.add(vinLabel);
        defaultView.add(vinText);

        defaultView.add(makeLabel);
        defaultView.add(makeText);

        defaultView.add(modelLabel);
        defaultView.add(modelText);

        defaultView.add(yearLabel);
        defaultView.add(yearText);

        defaultView.add(brandLabel);
        defaultView.add(brandText);

        defaultView.add(costEstimateLabel);
        defaultView.add(costEstimateText);


        defaultView.add(carButton);
        defaultView.add(motorcycleButton);
        defaultView.add(truckButton);

        add(defaultView);

        show();
    }
}
