package org.example;

import java.util.ArrayList;

public class InputLocation {
    private int[] coordinate;
    private ArrayList<Order> orders;
    private boolean isOccupied;

    private boolean botOnRoute;

    public InputLocation(int[] coordinate, ArrayList<Order> orders) {
        this.coordinate = coordinate;
        this.orders = orders;
        this.botOnRoute = false;
    }

    public int[] getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int[] coordinate) {
        this.coordinate = coordinate;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
