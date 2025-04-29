package vehicleInterface;

import database.DatabaseConnector;
import vehicles.Car;
import vehicles.Motorcycle;
import vehicles.Truck;
import vehicles.VehicleManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListInterface extends JFrame {
    public ListInterface(VehicleManager vm) {
        setTitle("IST Vehicle Care Solutions");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel vehicleList = new JPanel();

        JLabel listOfVehiclesLabel = new JLabel("Vehicles:");
        vehicleList.add(listOfVehiclesLabel, BorderLayout.NORTH);

        ArrayList<Car> cars = vm.getCars();
        ArrayList<Motorcycle> motorcycles = vm.getMotorcycles();
        ArrayList<Truck> trucks = vm.getTrucks();

        int index = 1;
        for (Car car : cars) {
            vehicleList.add(new JLabel("Vehicle " + index + ": " + car.displayMaintenanceDetails()), BorderLayout.AFTER_LAST_LINE);
            index++;
        }
        for (Motorcycle motorcycle : motorcycles) {
            vehicleList.add(new JLabel("Vehicle " + index + ": " + motorcycle.displayMaintenanceDetails()), BorderLayout.AFTER_LAST_LINE);
            index++;
        }
        for (Truck truck : trucks) {
            vehicleList.add(new JLabel("Vehicle " + index + ": " + truck.displayMaintenanceDetails()), BorderLayout.AFTER_LAST_LINE);
            index++;
        }

        add(vehicleList);

        show();
    }
}
