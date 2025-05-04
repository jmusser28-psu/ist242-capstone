package vehicleInterface;

import vehicles.Car;
import vehicles.Motorcycle;
import vehicles.Truck;
import vehicles.VehicleManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//Class that displays list of all vehicles currently managed by VehicleManager
public class ListInterface extends JFrame {
    public ListInterface(VehicleManager vm) {
        //Sets the window title and Dimensions
        setTitle("IST Vehicle Care Solutions: Vehicle List");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Only closes this window specifically, not the app

        //Creates the main panel with the vertical layout
        JPanel defaultView = new JPanel(new GridLayout(32, 1, 4, 4));

        //Adding the header label
        JLabel listOfVehiclesLabel = new JLabel("Vehicles:");
        defaultView.add(listOfVehiclesLabel);

        //Retrieves the vehicles from the VehicleManager
        ArrayList<Car> cars = vm.getCars();
        ArrayList<Motorcycle> motorcycles = vm.getMotorcycles();
        ArrayList<Truck> trucks = vm.getTrucks();

        //The index for listing vehicles in order
        int index = 1;

        //Adding the car details to the panel
        for (Car car : cars) {
            defaultView.add(new JLabel("Vehicle " + index + ": " + car.displayMaintenanceDetails()));
            index++;
        }
        //Adding the motorcycle details to the panel
        for (Motorcycle motorcycle : motorcycles) {
            defaultView.add(new JLabel("Vehicle " + index + ": " + motorcycle.displayMaintenanceDetails()));
            index++;
        }
        //Adding the truck details to the panel
        for (Truck truck : trucks) {
            defaultView.add(new JLabel("Vehicle " + index + ": " + truck.displayMaintenanceDetails()));
            index++;
        }
        //Adds the defaultView panel to the window
        add(defaultView);

        //Displays the window
        show();
    }
}
