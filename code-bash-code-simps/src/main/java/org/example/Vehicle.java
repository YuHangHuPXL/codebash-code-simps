package org.example;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private int[] currentPosition;
    private int[] destination;
    private int estimatedTimeOfArrival;
    private boolean isBusy;
    private List<String> history;

    public Vehicle(int[] startPosition) {
        this.currentPosition = startPosition;
        this.destination = null; // No destination initially
        this.estimatedTimeOfArrival = 0; // 0 indicates not moving initially
        this.isBusy = false;
        this.history = new ArrayList<>();
    }

    // Getters and setters for the properties
    public int[] getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int[] currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int[] getDestination() {
        return destination;
    }

    public void setDestination(int[] destination) {
        this.destination = destination;
    }

    public int getEstimatedTimeOfArrival() {
        return estimatedTimeOfArrival;
    }

    public void setEstimatedTimeOfArrival(int estimatedTimeOfArrival) {
        this.estimatedTimeOfArrival = estimatedTimeOfArrival;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public List<String> getHistory() {
        return history;
    }

    public void addHistory(String event) {
        history.add(event);
    }

    // Additional methods to update the vehicle's status, if necessary
}

