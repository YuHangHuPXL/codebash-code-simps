package org.example;

import java.io.*;
import java.util.*;

public class FileParser {
    private int T, V, S, O, P1, P2, X, Y, D;
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<int[]> inputLocations = new ArrayList<>();
    private List<int[]> outputLocations = new ArrayList<>();
    private List<GridItem> gridItems = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public void parseFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read the first line containing the simulation parameters
            String[] params = reader.readLine().trim().split("\\s+");
            T = Integer.parseInt(params[0]);
            V = Integer.parseInt(params[1]);
            S = Integer.parseInt(params[2]);
            O = Integer.parseInt(params[3]);
            P1 = Integer.parseInt(params[4]);
            P2 = Integer.parseInt(params[5]);
            X = Integer.parseInt(params[6]);
            Y = Integer.parseInt(params[7]);
            D = Integer.parseInt(params[8]);

            // Read the starting coordinates for vehicles
            String[] vehicleCoords = reader.readLine().trim().split("\\s+");
            for (String coord : vehicleCoords) {
                vehicles.add(new Vehicle(Arrays.stream(coord.split(",")).mapToInt(Integer::parseInt).toArray()));
            }

            // Read input locations
            String[] inputs = reader.readLine().trim().split("\\s+");
            for (String input : inputs) {
                inputLocations.add(Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray());
            }

            // Read output locations
            String[] outputs = reader.readLine().trim().split("\\s+");
            for (String output : outputs) {
                outputLocations.add(Arrays.stream(output.split(",")).mapToInt(Integer::parseInt).toArray());
            }

            // Read grid items
            for (int i = 0; i < S; i++) {
                String[] itemInfo = reader.readLine().trim().split("\\s+");
                gridItems.add(new GridItem(
                        Arrays.stream(itemInfo[0].split(",")).mapToInt(Integer::parseInt).toArray(),
                        Arrays.copyOfRange(itemInfo, 1, itemInfo.length)
                ));
            }

            // Read orders
            for (int i = 0; i < O; i++) {
                String[] orderInfo = reader.readLine().trim().split("\\s+");
                orders.add(new Order(
                        orderInfo[0].charAt(0),
                        Integer.parseInt(orderInfo[1]),
                        Arrays.stream(orderInfo[2].split(",")).mapToInt(Integer::parseInt).toArray(),
                        orderInfo[3]
                ));
            }
        }
    }

    // Getters for the parsed data
    public int getT() { return T; }
    public int getV() { return V; }
    public int getS() { return S; }
    public int getO() { return O; }
    public int getP1() { return P1; }
    public int getP2() { return P2; }
    public int getX() { return X; }
    public int getY() { return Y; }
    public int getD() { return D; }
    public List<Vehicle> getVehicles() { return vehicles; }
    public List<int[]> getInputLocations() { return inputLocations; }
    public List<int[]> getOutputLocations() { return outputLocations; }
    public List<GridItem> getGridItems() { return gridItems; }
    public List<Order> getOrders() { return orders; }


}
