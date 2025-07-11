package main;

import vehicleInterface.MainInterface;

/**
 * Entry point for the Vehicle Maintenance Management System.
 * Starts the Main interface GUI menu.
 * Also, please keep in mind that we are submitting the standard vehicle_maintenance.db file.
 * The database file will be updated when the user interacts with the program.
 */
public class Main {
    public static void main(String[] args) {
        new MainInterface();
    }
}