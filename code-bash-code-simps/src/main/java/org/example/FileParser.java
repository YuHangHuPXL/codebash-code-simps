package org.example;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class FileParser {
    private int T, V, S, O, P1, P2, X, Y, D;
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayList<InputLocation> inputLocations = new ArrayList<>();
    private ArrayList<OutputLocation> outputLocations = new ArrayList<>();
    private ArrayList<GridItem> gridItems = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

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
                inputLocations.add(new InputLocation(
                        Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray(),
                        new ArrayList<Order>()
                ));
            }

            // Read output locations
            String[] outputs = reader.readLine().trim().split("\\s+");
            for (String output : outputs) {
                outputLocations.add(new OutputLocation(
                        Arrays.stream(output.split(",")).mapToInt(Integer::parseInt).toArray(),
                        new ArrayList<Order>()
                ));
            }

            for (int i = 0; i < X; i++) {
                for (int j = 0; j < Y; j++) {
                    gridItems.add(new GridItem(new int[]{i, j}, new String[0]));
                }
            }

            // Read grid items
            for (int i = 0; i < S; i++) {
                String[] itemInfo = reader.readLine().trim().split("\\s+");

                gridItems.stream().filter(gridItem -> gridItem.getCoordinate()[0] == Integer.parseInt(itemInfo[0].split(",")[0]) && gridItem.getCoordinate()[1] == Integer.parseInt(itemInfo[0].split(",")[1])).findFirst().;

                gridItems.add(new GridItem(
                        Arrays.stream(itemInfo[0].split(",")).mapToInt(Integer::parseInt).toArray(),
                        Arrays.copyOfRange(itemInfo, 1, itemInfo.length)
                ));
            }

//            for (int i = 0; i < S; i++) {
//                String[] itemInfo = reader.readLine().trim().split("\\s+");
//                gridItems.add(new GridItem(
//                        Arrays.stream(itemInfo[0].split(",")).mapToInt(Integer::parseInt).toArray(),
//                        itemInfo[1]
//                ));
//            }

            // Read orders
            for (Order order : orders.stream().sorted(Comparator.comparingInt(Order::getTick)).collect(Collectors.toList())) {
                if (order.getType() == 'I') {
                    inputLocations.stream()
                            .filter(inputLocation -> inputLocation.getCoordinate()[0] == order.getCoordinate()[0] && inputLocation.getCoordinate()[1] == order.getCoordinate()[1])
                            .findFirst().orElseThrow(() -> new NoSuchElementException("No matching input location found for order")).getOrders().add(order);
                }
            }

            orders = orders.stream().filter(order -> order.getType() == 'D').collect(Collectors.toCollection(ArrayList::new));
        }
    }

    public int getT() {
        return T;
    }

    public void setT(int t) {
        T = t;
    }

    public int getV() {
        return V;
    }

    public void setV(int v) {
        V = v;
    }

    public int getS() {
        return S;
    }

    public void setS(int s) {
        S = s;
    }

    public int getO() {
        return O;
    }

    public void setO(int o) {
        O = o;
    }

    public int getP1() {
        return P1;
    }

    public void setP1(int p1) {
        P1 = p1;
    }

    public int getP2() {
        return P2;
    }

    public void setP2(int p2) {
        P2 = p2;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getD() {
        return D;
    }

    public void setD(int d) {
        D = d;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public ArrayList<InputLocation> getInputLocations() {
        return inputLocations;
    }

    public void setInputLocations(ArrayList<InputLocation> inputLocations) {
        this.inputLocations = inputLocations;
    }

    public ArrayList<OutputLocation> getOutputLocations() {
        return outputLocations;
    }

    public void setOutputLocations(ArrayList<OutputLocation> outputLocations) {
        this.outputLocations = outputLocations;
    }

    public ArrayList<GridItem> getGridItems() {
        return gridItems;
    }

    public void setGridItems(ArrayList<GridItem> gridItems) {
        this.gridItems = gridItems;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
// Getters for the parsed data

}
