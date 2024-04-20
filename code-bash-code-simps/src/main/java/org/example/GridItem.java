package org.example;

public class GridItem {
    private int[] coordinate;
    private String[] items;

    public GridItem(int[] coordinate, String[] items) {
        this.coordinate = coordinate;
        this.items = items;
    }

    public int[] getCoordinate() { return coordinate; }
    public String[] getItems() { return items; }
}
