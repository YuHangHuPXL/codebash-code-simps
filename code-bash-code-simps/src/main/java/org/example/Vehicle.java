package org.example;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private int[] currentPosition;
    private int[] destination;
    private int estimatedArrivalTick;
    private ArrayList<String> history;
    private boolean isPickingUpOrder;
    private boolean isPickingUpItem;
    private int pickupTime;
    private boolean isDroppingOff;

    private int timer = 0;

    public Vehicle(int[] currentPosition) {
        this.currentPosition = currentPosition;
        this.destination = null;
        this.estimatedArrivalTick = 0;
        this.history = new ArrayList<>();
        this.isPickingUpOrder = false;
        this.isDroppingOff = false;
        this.isPickingUpItem = false;
    }

    // Getters and setters for the properties
    public int[] getCurrentPosition() {
        return currentPosition;
    }

//    public void isDoneMoving() {
//        if (currentPosition[0] == destination[0] && currentPosition[1] == destination[1]) {
//
//            destination = null;
//            estimatedArrivalTick = 0;
//        }
//    }

    public void setCurrentPosition(int[] currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int[] getDestination() {
        return destination;
    }

    public void setDestination(int[] destination) {
        this.destination = destination;
    }

//    public boolean isBusy() {
//        return isBusy;
//    }
//
//    public void setBusy(boolean busy) {
//        isBusy = busy;
//    }

    public boolean isPickingUpItem() {
        return isPickingUpItem;
    }

    public void setPickingUpItem(boolean pickingUpItem) {
        isPickingUpItem = pickingUpItem;
    }

    public int getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(int pickupTime) {
        this.pickupTime = pickupTime;
    }

    public void move() {
        if (currentPosition[0] < destination[0]) {
            System.out.println("here1");
            currentPosition[0]++;
        } else if (currentPosition[0] > destination[0]) {
            System.out.println("here2");
            currentPosition[0]--;
        } else if (currentPosition[1] < destination[1]) {
            System.out.println("here3");
            currentPosition[1]++;
        } else if (currentPosition[1] > destination[1]) {
            System.out.println("here4");
            currentPosition[1]--;
        } else {
            timer++;
//        lse if (isPickingUpOrder) {
//            if (timer == 0) {
//                history.add("P");
//            }
//            timer++;
//        } else if (isDroppingOff) {
//            if (timer == 0) {
//                history.add("D");
//            }
//            timer++;
        }

        if (isPickingUpOrder) {
            if (currentPosition[0] == destination[0] && currentPosition[1] == destination[1]) {
                isPickingUpOrder = false;
                isDroppingOff = true;
            }
        } else if (isDroppingOff) {
            if (currentPosition[0] == destination[0] && currentPosition[1] == destination[1]) {
                isDroppingOff = false;
            }
        }


    }

    public ArrayList<String> getHistory() {
        return history;
    }

    public void addHistory(String event) {
        history.add(event);
    }

    public int getEstimatedArrivalTick() {
        return estimatedArrivalTick;
    }

    public void setEstimatedArrivalTick(int estimatedArrivalTick) {
        this.estimatedArrivalTick = estimatedArrivalTick;
    }

    public void setHistory(ArrayList<String> history) {
        this.history = history;
    }

    public boolean isPickingUpOrder() {
        return isPickingUpOrder;
    }

    public void setPickingUpOrder(boolean pickingUpOrder) {
        isPickingUpOrder = pickingUpOrder;
    }

    public boolean isDroppingOff() {
        return isDroppingOff;
    }

    public void setDroppingOff(boolean droppingOff) {
        isDroppingOff = droppingOff;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    // Additional methods to update the vehicle's status, if necessary
}

