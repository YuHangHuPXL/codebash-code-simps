package org.example;

public class GridItem {
    private int[] coordinate;
    private String[] items;
    private boolean isOccupied;

    public GridItem(int[] coordinate, String[] items) {
        this.coordinate = coordinate;
        this.items = items;
    }

    public int[] getCoordinate() { return coordinate; }
    public String[] getItems() { return items; }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}


