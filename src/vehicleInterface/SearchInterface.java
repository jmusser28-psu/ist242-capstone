package vehicleInterface;

import database.DatabaseConnector;
import util.InputValidation;
import vehicles.Vehicle;
import vehicles.VehicleManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class SearchInterface extends JFrame {
    public SearchInterface(VehicleManager vm) {
        setTitle("IST Vehicle Care Solutions: Vehicle Search");
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel defaultView = new JPanel(new GridLayout(8, 1, 8, 8));

        JLabel promptVinLabel = new JLabel("Enter a Vehicle ID to search: ");
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
                    result.setText("Found: VIN " + vin + ".");
                }

            }
        });

        defaultView.add(promptVinLabel);
        defaultView.add(promptVin);
        defaultView.add(result);

        add(defaultView);


        show();
    }

}
