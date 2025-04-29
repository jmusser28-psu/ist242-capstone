package vehicleInterface;

import database.DatabaseConnector;
import util.InputValidation;
import vehicles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchInterface extends JFrame {
    public SearchInterface(VehicleManager vm) {
        setTitle("IST Vehicle Care Solutions: Vehicle Search");
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel defaultView = new JPanel(new GridLayout(8, 1, 8, 8));

        JLabel promptVinLabel = new JLabel("Enter a Vehicle ID to search:");
        JLabel confirmationLabel = new JLabel("Press enter once VIN is entered");
        JTextField promptVin = new JTextField(16);

        JLabel result = new JLabel("");

        promptVin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vin = promptVin.getText();
                Vehicle vehicle = vm.findVehicle(vin);
                if (vehicle == null) {
                    result.setText("Error: VIN " + vin + " not found.");
                }
                else {
                    if (vehicle.getType().equals("Car")) {
                        Car car = vm.findCar(vin);
                        result.setText(car.displayMaintenanceDetails());
                    }
                    if (vehicle.getType().equals("Motorcycle")) {
                        Motorcycle motorcycle = vm.findMotorcycle(vin);
                        result.setText(motorcycle.displayMaintenanceDetails());
                    }
                    if (vehicle.getType().equals("Truck")) {
                        Truck truck = vm.findTruck(vin);
                        result.setText(truck.displayMaintenanceDetails());
                    }
                }

            }
        });

        defaultView.add(promptVinLabel);
        defaultView.add(confirmationLabel);
        defaultView.add(promptVin);
        defaultView.add(result);

        add(defaultView);


        show();
    }

}
