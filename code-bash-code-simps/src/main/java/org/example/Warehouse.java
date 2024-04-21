package org.example;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Warehouse {
    private ArrayList<Vehicle> vehicles;
    private int width;
    private int height;
    private int depth;
    private ArrayList<GridItem> grid;
    private ArrayList<InputLocation> inputLocations;
    private ArrayList<OutputLocation> outputLocations;
    private ArrayList<Order> outPutOrders;
    private ArrayList<Order> orders;

    private int currentTick = 0;

    public Warehouse(int width, int height, int depth, ArrayList<GridItem> grid, ArrayList<InputLocation> inputLocations, ArrayList<OutputLocation> outputLocations, ArrayList<Order> outPutOrders, ArrayList<Vehicle> vehicles) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.grid = grid;
        this.inputLocations = inputLocations;
        this.outputLocations = outputLocations;
        this.orders = new ArrayList<>();
        this.outPutOrders = outPutOrders;
        this.vehicles = vehicles;
    }

    public void doTick() {
        // Check if there are any orders to be processed
        for (InputLocation inputLocation : inputLocations) {
            if (!inputLocation.getOrders().isEmpty()) {
                Order order = inputLocation.getOrders().get(0);

                Vehicle vehicle = getFreeVehicle();
                if (vehicle != null) {
                    order.setVehicle(vehicle);
                    vehicle.setDestination(inputLocation.getCoordinate());
                    vehicle.setPickingUpOrder(true);

                    if (vehicle.getCurrentPosition() != inputLocation.getCoordinate()) {
                        vehicle.getHistory().add(inputLocation.getCoordinate()[0] + "," + inputLocation.getCoordinate()[1]);
                    }
                }

                orders.add(order);
                inputLocation.getOrders().remove(0);
            }
        }

        for (Order order : outPutOrders) {
            Vehicle vehicle = getFreeVehicle();
            if (vehicle != null) {
                order.setVehicle(vehicle);

                GridItem gridWithItem = findItem(order.getItem());

                vehicle.setDestination(gridWithItem.getCoordinate());
                vehicle.setPickingUpItem(true);

                if (vehicle.getCurrentPosition() != gridWithItem.getCoordinate()) {
                    vehicle.getHistory().add(gridWithItem.getCoordinate()[0] + "," + gridWithItem.getCoordinate()[1]);
                }
            }

            orders.add(order);
        }

        for (Order order : orders) {
            if (order.getVehicle() != null) {
                Vehicle vehicle = order.getVehicle();

                if (vehicle.isPickingUpOrder() && order.getTick() >= currentTick) {
                    if (vehicle.getTimer() == 2) {
                        GridItem destination = getFreeDestination();

                        vehicle.setDestination(destination.getCoordinate());
                        vehicle.setPickingUpOrder(false);
                        vehicle.setDroppingOff(true);
                        vehicle.setTimer(0);
                        vehicle.addHistory("P");
                        vehicle.addHistory(order.getCoordinate()[0] + "," + order.getCoordinate()[1]);
                    }
                } else if (order.getType() == 'I' && vehicle.isDroppingOff() && vehicle.getTimer() != 0) {
                    System.out.println(grid.size());
                    GridItem gridItem = grid.stream().filter(item -> item.getCoordinate()[0] == order.getCoordinate()[0] && item.getCoordinate()[1] == order.getCoordinate()[1]).findFirst().orElseThrow(() -> new NoSuchElementException("No matching grid item found for order"));
                    gridItem.setOccupied(true);

                    if (gridItem.isOccupied()) {
                        vehicle.addHistory("_");
                        vehicle.setTimer(0);
                    } else {
                        int totalTimeNeeded = depth - gridItem.getItems().length * 2;
                        if (vehicle.getTimer() == totalTimeNeeded) {
                            vehicle.addHistory("D");
                            vehicle.setTimer(0);
                            vehicle.setDroppingOff(false);
                            order.setVehicle(null);
                        }
                        if (gridItem != null) {
                            gridItem.setOccupied(false);
                        }
                    }
                } else if (vehicle.isPickingUpItem() && vehicle.getTimer() != 0) {
                    GridItem gridItem = grid.stream().filter(item -> item.getCoordinate()[0] == vehicle.getCurrentPosition()[0] && item.getCoordinate()[1] == vehicle.getCurrentPosition()[1]).findFirst().orElseThrow(() -> new NoSuchElementException("No matching grid item found for order"));

                    if (gridItem.isOccupied()) {
                        vehicle.addHistory("_");
                        vehicle.setTimer(0);
                    } else {
                        int totalTimeNeeded = depth - gridItem.getItems().length * 2;
                        if (vehicle.getTimer() == totalTimeNeeded) {
                            vehicle.addHistory("P");
                            vehicle.setTimer(0);
                            vehicle.setDestination(order.getCoordinate());
                            vehicle.setPickingUpItem(false);
                            vehicle.setDroppingOff(true);
                        }
                        if (gridItem != null) {
                            gridItem.setOccupied(false);
                        }
                    }
                } else if (order.getType() == 'O' && vehicle.isDroppingOff() && vehicle.getTimer() == 2) {
                    vehicle.setTimer(0);
                    vehicle.setDroppingOff(false);
                    vehicle.addHistory("D");
                    order.setVehicle(null);
                }

                vehicle.move();
            }
        }



        if (orders.isEmpty()) {
            return;
        }



//        for (Vehicle vehicle : vehicles) {
//            if (vehicle.isBusy()) {
//                vehicle.isDoneMoving();
//            } else {
//                // Find the next order for the vehicle
//                Order nextOrder = findNextOrder(vehicle);
//                if (nextOrder != null) {
//                    // Calculate the path to the order
//                    int[] destination = nextOrder.getCoordinate();
//                    vehicle.setDestination(destination);
//                    vehicle.setBusy(true);
//                    vehicle.addHistory("Moving to order at " + destination[0] + ", " + destination[1]);
//                }
//            }
//        }

        currentTick++;
    }

    public Vehicle getFreeVehicle() {
        for (Vehicle vehicle : vehicles) {
            if (!vehicle.isDroppingOff() && !vehicle.isPickingUpOrder() && !vehicle.isPickingUpItem()) {
                return vehicle;
            }
        }
        return null;
    }

    public GridItem getFreeDestination() {
        return grid.stream().filter(gridItem -> gridItem.isOccupied() && gridItem.getItems().length < depth).findFirst().orElse(null);
    }

    public GridItem findItem(String item) {
        //shit
        return grid.stream().filter(gridItem -> gridItem.getItems()[0].equals(item)).findFirst().orElse(null);
    }
}
