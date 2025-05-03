package vehicleInterface;

import vehicles.Car;
import vehicles.Motorcycle;
import vehicles.Truck;
import vehicles.VehicleManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListInterface extends JFrame {
    public ListInterface(VehicleManager vm) {
        setTitle("IST Vehicle Care Solutions: Vehicle List");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel defaultView = new JPanel(new GridLayout(32, 1, 4, 4));

        JLabel listOfVehiclesLabel = new JLabel("Vehicles:");
        defaultView.add(listOfVehiclesLabel);

        ArrayList<Car> cars = vm.getCars();
        ArrayList<Motorcycle> motorcycles = vm.getMotorcycles();
        ArrayList<Truck> trucks = vm.getTrucks();

        int index = 1;
        for (Car car : cars) {
            defaultView.add(new JLabel("Vehicle " + index + ": " + car.displayMaintenanceDetails()));
            index++;
        }
        for (Motorcycle motorcycle : motorcycles) {
            defaultView.add(new JLabel("Vehicle " + index + ": " + motorcycle.displayMaintenanceDetails()));
            index++;
        }
        for (Truck truck : trucks) {
            defaultView.add(new JLabel("Vehicle " + index + ": " + truck.displayMaintenanceDetails()));
            index++;
        }

        add(defaultView);

        show();
    }
}
