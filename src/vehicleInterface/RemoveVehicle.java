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
        setTitle("IST Vehicle Care Solutions: Vehicle Remove");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout());

        JPanel defaultView = new JPanel(new GridLayout(16, 16, 4, 4));

        JLabel selectVehicle = new JLabel("Select the VIN for the Vehicle to Remove:");

        JComboBox vehicleComboBox = new JComboBox(getVehicles(vm.getVehicles()));

        JButton deleteButton = new JButton("Delete Vehicle");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = vehicleComboBox.getSelectedIndex();

                String selection = vehicleComboBox.getSelectedItem().toString();
                int stringIndex = selection.indexOf(" ");
                String vin = selection.substring(stringIndex + 1);

                vm.deleteVehicle(vin);
                vehicleComboBox.removeItemAt(index);
            }
        });

        defaultView.add(selectVehicle);
        defaultView.add(vehicleComboBox);
        defaultView.add(deleteButton);
        add(defaultView);

        show();
    }

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