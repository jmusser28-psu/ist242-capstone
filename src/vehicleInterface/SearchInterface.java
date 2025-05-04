package vehicleInterface;

import vehicles.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Class for searching for a vehicle by the VIN from the VehicleManager
public class SearchInterface extends JFrame {
    public SearchInterface(VehicleManager vm) {
        //Sets up the window
        setTitle("IST Vehicle Care Solutions: Vehicle Search");
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Creates the main panel with a vertical layout
        JPanel defaultView = new JPanel(new GridLayout(8, 1, 8, 8));

        //Creates input label and text field for VIN search
        JLabel promptVinLabel = new JLabel("Enter a Vehicle ID to search:");
        JTextField promptVin = new JTextField(16);
        //Creates the button to trigger the search
        JButton findVehicleButton = new JButton("Find Vehicle");

        //Label to display the result of the search
        JLabel result = new JLabel("");

        //The search buttons functionality
        findVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vin = promptVin.getText(); //Gets the VIN the user entered
                Vehicle vehicle = vm.findVehicle(vin); // General search for a vehicle in the list
                //If the vehicle is not found
                if (vehicle == null) {
                    result.setText("Error: VIN " + vin + " not found.");
                }
                //If the vehicle is found, determines the type and will display it in detail
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

        //Adds the components to the panel
        defaultView.add(promptVinLabel);
        defaultView.add(promptVin);
        defaultView.add(findVehicleButton);
        defaultView.add(result);

        //Adds the panel to the frame
        add(defaultView);

        //Display the window
        show();
    }

}
