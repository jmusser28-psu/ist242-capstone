package vehicleInterface;

import vehicles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdateInterface extends JFrame {
    String vin;
    String type;
    public UpdateInterface(VehicleManager vm) {
        setTitle("IST Vehicle Care Solutions");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout());

        JPanel defaultView = new JPanel(new GridLayout(16, 16, 4, 4));
        JPanel updateView = new JPanel(new GridLayout(16, 16, 4, 4));

        JComboBox vehicleComboBox = new JComboBox(getVehicles(vm.getVehicles()));
        JButton getInformation = new JButton("Retrieve Vehicle Info");

        defaultView.add(vehicleComboBox);
        defaultView.add(getInformation);

        JLabel vinLabel = new JLabel();

        JLabel makeLabel = new JLabel();
        JTextField makeText = new JTextField();

        JLabel modelLabel = new JLabel();
        JTextField modelText = new JTextField();

        JLabel yearLabel = new JLabel();
        JTextField yearText = new JTextField();

        JLabel typeLabel = new JLabel();
        JTextField typeText = new JTextField();

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

        getInformation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(updateView);
                updateView.removeAll();

                int index = vehicleComboBox.getSelectedIndex();
                Vehicle vehicle = null;

                ArrayList<Vehicle> vehicles = vm.getVehicles();
                for (int i = 0; i < vehicles.size(); i++) {
                    if (i == index) {
                        vehicle = vehicles.get(i);
                    }
                }

                vin = vehicle.getVin();
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

                vinLabel.setText(vin);
                makeLabel.setText(make);
                modelLabel.setText(model);
                yearLabel.setText(year);
                typeLabel.setText(type);
                brandLabel.setText(brand);
                costEstimateLabel.setText(costEstimate);

                updateView.add(vinLabel);

                updateView.add(makeLabel);
                updateView.add(makeText);

                updateView.add(modelLabel);
                updateView.add(modelText);

                updateView.add(yearLabel);
                updateView.add(yearText);

                updateView.add(typeLabel);
                updateView.add(typeText);

                updateView.add(brandLabel);
                updateView.add(brandText);

                updateView.add(costEstimateLabel);
                updateView.add(costEstimateText);


                if (type.equals("Car")) {
                    ArrayList<Car> cars = vm.getCars();
                    for (int i = 0; i < cars.size(); i++) {
                        if (cars.get(i).getVin().equals(vin)) {
                            numberOfDoors = cars.get(i).getNumDoors();
                            oilChangeCost = cars.get(i).getOilChangeCost();

                            numberOfDoorsLabel.setText(numberOfDoors);
                            oilChangeCostLabel.setText(oilChangeCost);

                            updateView.add(numberOfDoorsLabel);
                            updateView.add(numberOfDoorsText);

                            updateView.add(oilChangeCostLabel);
                            updateView.add(oilChangeCostText);
                        }
                    }
                }

                if (type.equals("Motorcycle")) {
                    ArrayList<Motorcycle> motorcycles = vm.getMotorcycles();
                    for (int i = 0; i < motorcycles.size(); i++) {
                        if (motorcycles.get(i).getVin().equals(vin)) {
                            chainCondition = motorcycles.get(i).getChainCondition();
                            chainReplacementCost = motorcycles.get(i).getChainReplacementCost();

                            chainConditionLabel.setText(chainCondition);
                            chainReplacementCostLabel.setText(chainReplacementCost);

                            updateView.add(chainConditionLabel);
                            updateView.add(chainConditionText);

                            updateView.add(chainReplacementCostLabel);
                            updateView.add(chainReplacementCostText);
                        }
                    }
                }

                if (type.equals("Truck")) {
                    ArrayList<Truck> trucks = vm.getTrucks();
                    for (int i = 0; i < trucks.size(); i++) {
                        if (trucks.get(i).getVin().equals(vin)) {
                            maxLoad = trucks.get(i).getMaxLoad();
                            cargoInspectionCost = trucks.get(i).getCargoInspectionCost();

                            maxLoadLabel.setText(maxLoad);
                            cargoInspectionCostLabel.setText(cargoInspectionCost);

                            updateView.add(maxLoadLabel);
                            updateView.add(maxLoadText);

                            updateView.add(cargoInspectionCostLabel);
                            updateView.add(cargoInspectionCostText);
                        }
                    }
                }

                updateView.add(updateVehicle);
                updateVehicle.add(isSuccess);

                add(updateView);
                revalidate();
                repaint();
            }
        });

        updateVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                vm.deleteVehicle(vin);
                if (type.equals("Car")) {
                    vm.addCar(vin, make, model, year, type, brand, costEstimate, numberOfDoors, oilChangeCost);
                    isSuccess.setText("Success");
                }
                if (type.equals("Motorcycle")) {
                    vm.addMotorcycle(vin, make, model, year, type, brand, costEstimate, chainCondition, chainReplacementCost);
                    isSuccess.setText("Success");
                }
                if (type.equals("Truck")) {
                    vm.addTruck(vin, make, model, year, type, brand, costEstimate, maxLoad, cargoInspectionCost);
                    isSuccess.setText("Success");
                }

            }
        });

        add(defaultView);

        show();
    }
    public String[] getVehicles(ArrayList<Vehicle> vehicles) {
        ArrayList<String> vehicleInformationArrayList = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            vehicleInformationArrayList.add("VIN " + vehicle.getVin());
        }

        String[] vehicleInformationArray = new String[vehicleInformationArrayList.size()];

        for (int i = 0; i < vehicleInformationArray.length; i++) {
            vehicleInformationArray[i] = vehicleInformationArrayList.get(i);
        }

        return vehicleInformationArray;
    }
}
