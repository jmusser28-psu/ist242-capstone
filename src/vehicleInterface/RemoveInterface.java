package vehicleInterface;

import vehicles.Vehicle;
import vehicles.VehicleManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RemoveInterface extends JFrame {
    public RemoveInterface(VehicleManager vm) {
        // Sets the window title, window size, and applies a gridlayout to the frame
        setTitle("IST Vehicle Care Solutions: Vehicle Remove");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout());

        // Creates a new panel, defaultView, with a GridLayout
        JPanel defaultView = new JPanel(new GridLayout(16, 16, 4, 4));

        // Adds a selectVehicle JLabel to inform the user to pick the VIN of the vehicle they want removed
        JLabel selectVehicle = new JLabel("Select the VIN for the Vehicle to Remove:");

        // Creates a JComboBox, vehicleComboBox, which allows the user to pick the VIN from a list of Vehicle ID Numbers
        // Uses the getVehicles method to get an array of VINs
        JComboBox vehicleComboBox = new JComboBox(getVehicles(vm.getVehicles()));

        // Creates a JButton, deleteButton, which deletes the selected vehicle when the user clicks it
        JButton deleteButton = new JButton("Delete Vehicle");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gets the index of selected item of the vehicleComboBox
                int index = vehicleComboBox.getSelectedIndex();

                // Converts the selected item to a String which includes the identifier "VIN:" and the Vehicle ID Number
                // Then gets the index of the beginning of the VIN by identifying where the space between "VIN:" and the Vehicle ID Number occurs
                // Finally sets the String vin to the VIN based on the user selection
                String selection = vehicleComboBox.getSelectedItem().toString();
                int stringIndex = selection.indexOf(" ");
                String vin = selection.substring(stringIndex + 1);

                // Removes the vehicle
                vm.deleteVehicle(vin);
                // Removes the VIN option as the vehicle is removed
                vehicleComboBox.removeItemAt(index);
            }
        });

        // Adds the selectVehicle label to the defaultView panel
        defaultView.add(selectVehicle);
        // Adds the vehicleComboBox combobox to the defaultView panel
        defaultView.add(vehicleComboBox);
        // Adds the deleteButton button to the defaultView panel
        defaultView.add(deleteButton);

        // Adds the defaultView panel to the frame
        add(defaultView);

        // Shows the frame
        show();
    }

    // This method is for obtaining a String array of the VINs
    // This gets the vehicles arraylist from VehicleManager and adds the VIN to an arraylist of Strings called vehicleInformationArrayList
    // Then converts the arraylist to an array (vehicleInformationArray) so that it can be used with the JComboBox
    // Returns the vehicleInformationArray
    public String[] getVehicles(ArrayList<Vehicle> vehicles) {
        ArrayList<String> vehicleInformationArrayList = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            // Adds an identifier for the VIN alongside the VIN to the VehicleInformationArrayList
            vehicleInformationArrayList.add("VIN: " + vehicle.getVin());
        }

        // Creates an array of strings, vehicleInformationArray, based on the size of the vehicleInformationArrayList
        String[] vehicleInformationArray = new String[vehicleInformationArrayList.size()];

        // Adds all the contents in vehicleInformationArrayList to the vehicleInformationArray
        for (int i = 0; i < vehicleInformationArray.length; i++) {
            vehicleInformationArray[i] = vehicleInformationArrayList.get(i);
        }

        // Returns vehicleInformationArray so that it can be used by the vehicleComboBox
        return vehicleInformationArray;
    }
}