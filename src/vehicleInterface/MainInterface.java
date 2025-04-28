package vehicleInterface;

import database.DatabaseConnector;
import vehicles.VehicleManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class MainInterface extends JFrame {
    public MainInterface() {
        DatabaseConnector dbc = new DatabaseConnector();
        Connection connection = dbc.getConnection();
        VehicleManager vm = new VehicleManager(connection);

        setTitle("IST Vehicle Care Solutions");
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel defaultView = new JPanel(new GridLayout(8, 8, 8, 8));

        JLabel interactions = new JLabel("Interactions:");

        JButton listVehicle = new JButton("List vehicles");
        listVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListInterface(vm);
            }
        });


        JButton addVehicle = new JButton("Add a vehicle");


        JButton removeVehicle = new JButton("Remove a vehicle");
        removeVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveVehicle(vm);
            }
        });

        JButton updateVehicle = new JButton("Update a vehicle");



        JButton searchVehicle = new JButton("Search for a vehicle");
        searchVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchInterface(vm);
            }
        });

        defaultView.add(interactions);
        defaultView.add(listVehicle);
        defaultView.add(addVehicle);
        defaultView.add(removeVehicle);
        defaultView.add(updateVehicle);
        defaultView.add(searchVehicle);

        add(defaultView);

        show();
    }

}
