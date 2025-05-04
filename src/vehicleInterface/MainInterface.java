package vehicleInterface;

import database.DatabaseConnector;
import vehicles.VehicleManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

// Creates the class for the main menu frame of the project.
public class MainInterface extends JFrame {
    public MainInterface() {
        // Declares a DatabaseConnector to initiate a connection to the database
        DatabaseConnector dbc = new DatabaseConnector();
        // Gets the connection for the database
        Connection connection = dbc.getConnection();
        // Declares a VehicleManager to manage vehicles and interact with the database through the DatabaseManager class
        // It needs the Connection to work with the database
        VehicleManager vm = new VehicleManager(connection);

        // Sets the window title, window size, and close operation
        // We use 640x480 as it seems like a sane default
        // Additionally, we want the main menu to close the application entirely on exit
        setTitle("IST Vehicle Care Solutions");
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creates the defaultView (we use this name for the main panel of most classes) and create it with a GridLayout
        // so that it looks nicer
        JPanel defaultView = new JPanel(new GridLayout(8, 8, 8, 8));

        // Creates aJLabel to give context to the interactions available
        JLabel interactions = new JLabel("Interactions:");

        // Creates the listVehicle button, which opens a new ListInterface window when clicked
        JButton listVehicle = new JButton("List vehicles");
        listVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListInterface(vm);
            }
        });

        // Creates the addVehicle button, which opens a new AddInterface window when clicked
        JButton addVehicle = new JButton("Add a vehicle");
        addVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddInterface(vm);
            }
        });

        // Creates the removeVehicle button, which opens a new RemoveInterface window when clicked
        JButton removeVehicle = new JButton("Remove a vehicle");
        removeVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveInterface(vm);
            }
        });

        // Creates the updateVehicle button, which opens a new UpdateInterface window when clicked
        JButton updateVehicle = new JButton("Update a vehicle");
        updateVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateInterface(vm);
            }
        });

        // Creates the listVehicle button, which opens a new SearchInterface window when clicked
        JButton searchVehicle = new JButton("Search for a vehicle");
        searchVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchInterface(vm);
            }
        });

        // Adds the interactions label, and buttons to the defaultView panel
        defaultView.add(interactions);
        defaultView.add(listVehicle);
        defaultView.add(addVehicle);
        defaultView.add(removeVehicle);
        defaultView.add(updateVehicle);
        defaultView.add(searchVehicle);

        // Adds the defaultView panel to the frame
        add(defaultView);

        // Makes the frame visible
        show();
    }

}
