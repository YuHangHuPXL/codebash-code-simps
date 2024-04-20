package org.example;

public class Order {
    private char type;
    private int tick;
    private int[] coordinate;
    private String item;

    public Order(char type, int tick, int[] coordinate, String item) {
        this.type = type;
        this.tick = tick;
        this.coordinate = coordinate;
        this.item = item;
    }

    public char getType() { return type; }
    public int getTick() { return tick; }
    public int[] getCoordinate() { return coordinate; }
    public String getItem() { return item; }
}
