package vehicleInterface;

import vehicles.Vehicle;
import vehicles.VehicleManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class RemoveVehicle extends JFrame {
    public RemoveVehicle(VehicleManager vm) {
        setTitle("IST Vehicle Care Solutions");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout());

        JPanel defaultView = new JPanel(new GridLayout(16, 16, 4, 4));

        JLabel selectionLabel = new JLabel();
        JComboBox vehicleComboBox = new JComboBox(getVehicles(vm));

        vehicleComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println(e.getItem());
            }
        });

        defaultView.add(vehicleComboBox);
        defaultView.add(selectionLabel);
        add(defaultView);

        show();
    }

    public String[] getVehicles(VehicleManager vm) {
        ArrayList<Vehicle> vehicles = vm.getVehicles();
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