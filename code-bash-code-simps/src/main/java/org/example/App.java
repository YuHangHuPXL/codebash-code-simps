package org.example;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        FileParser parser = new FileParser(); // Has vehicles, inputLocations, outputLocations, gridItems, orders, and simulation parameters
        try {
            parser.parseFile("A_Sample.txt"); // Replace with the actual path to the input file
            // Here you could implement further logic, e.g., waiting for all orders to be processed

            Warehouse warehouse = new Warehouse(parser.getX(), parser.getY(), parser.getD(), parser.getGridItems(), parser.getInputLocations(), parser.getOutputLocations(), parser.getOrders(), parser.getVehicles());
            for (int i = 0; i < parser.getT(); i++) {
                warehouse.doTick();
            }

            System.out.println("Simulation completed successfully!");


            parser.getVehicles().forEach(vehicle -> {
                System.out.println(vehicle.getHistory());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



//    public static void printParseResult(FileParser parser) {
//        System.out.println("File parsed successfully!\n");
//        System.out.println("T: " + parser.getT());
//        System.out.println("V: " + parser.getV());
//        System.out.println("S: " + parser.getS());
//        System.out.println("O: " + parser.getO());
//        System.out.println("P1: " + parser.getP1());
//        System.out.println("P2: " + parser.getP2());
//        System.out.println("X: " + parser.getX());
//        System.out.println("Y: " + parser.getY());
//
//        System.out.println("\nVehicle starting positions:");
//        for (Vehicle vehicle : parser.getVehicles()) {
//            System.out.println("\t Vehicle at " + vehicle.getCurrentPosition()[0] + "," + vehicle.getCurrentPosition()[1]);
//        }
//
////        System.out.println("\nInput locations:");
////        for (int[] inputLocation : parser.getInputLocations()) {
////            System.out.println("\t Input location at " + inputLocation[0] + "," + inputLocation[1]);
////        }
////
////        System.out.println("\nOutput locations:");
////        for (int[] outputLocation : parser.getOutputLocations()) {
////            System.out.println("\t Output location at " + outputLocation[0] + "," + outputLocation[1]);
////        }
//
//        System.out.println("\nGrid items:");
//        for (GridItem gridItem : parser.getGridItems()) {
//            System.out.println("\t Grid item at " + gridItem.getCoordinate()[0] + "," + gridItem.getCoordinate()[1] + " with items: " + String.join(", ", gridItem.getItems()));
//        }
//    }

}
