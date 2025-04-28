package vehicleInterface;

import vehicles.Vehicle;
import vehicles.VehicleManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RemoveVehicle extends JFrame {
    public RemoveVehicle(VehicleManager vm) {
        setTitle("IST Vehicle Care Solutions");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout());

        JPanel removeVehicleView = new JPanel(new GridLayout(32, 32, 4, 4));
        JPanel listPanel = listVehicles(vm);

        JLabel removeVehiclePrompt = new JLabel("Enter the vin of the vehicle you want removed");
        JTextField removeVehicleText = new JTextField(16);
        JButton removeConfirmation = new JButton("Remove Vehicle");
        JLabel wasSuccess = new JLabel("");


        removeConfirmation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = vm.deleteVehicle(removeVehicleText.getText());
                if (valid) {
                    wasSuccess.setText("Successfully deleted vehicle with VIN: " + removeVehicleText.getText());
                    remove(listPanel);
                }
                if (!valid) {
                    wasSuccess.setText("Unable to deleted vehicle with VIN: " + removeVehicleText.getText());
                }

            }
        });

        removeVehicleView.add(removeVehiclePrompt);
        removeVehicleView.add(removeVehicleText);
        removeVehicleView.add(removeConfirmation);
        removeVehicleView.add(wasSuccess);

        add(removeVehicleView, BorderLayout.NORTH);
        add(listPanel, BorderLayout.EAST);

        show();
    }
    private JPanel listVehicles(VehicleManager vm) {
        ArrayList<Vehicle> vehicles = vm.getVehicles();

        JPanel listPanel = new JPanel(new GridLayout(32, 32, 4, 4));

        JLabel vehicleLabel = new JLabel("Vehicles:");
        listPanel.add(vehicleLabel, BorderLayout.NORTH);

        for (int i = 0; i < vehicles.size(); i++) {
            String vin = vehicles.get(i).getVin();
            String make = vehicles.get(i).getMake();
            String model = vehicles.get(i).getModel();
            String year = vehicles.get(i).getYear();
            String type = vehicles.get(i).getType();

            listPanel.add(new JLabel("VIN: " + vin +
                    ", Make: " + make +
                    ", Model " + model +
                    ", Year " + year +
                    ", Type " + type), BorderLayout.AFTER_LAST_LINE);
        }
        return listPanel;
    }

}
