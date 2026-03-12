/**
 * BookMyStayApp
 * Hotel Booking Management System
 * @author Laasya
 * @version 3.0
 */

import java.util.HashMap;

abstract class Room {

    String roomType;
    int beds;
    double price;

    Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    void displayRoom() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Price: $" + price);
    }
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room",1,100);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room",2,180);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room",3,350);
    }
}

public class BookMyStayApp {

    static HashMap<String,Integer> roomInventory = new HashMap<>();

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println(" Welcome to Book My Stay App ");
        System.out.println(" Hotel Booking System v3.0 ");
        System.out.println("================================");

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        roomInventory.put("Single Room",5);
        roomInventory.put("Double Room",3);
        roomInventory.put("Suite Room",2);

        System.out.println("\n--- Room Details ---");

        single.displayRoom();
        System.out.println("Available: " + roomInventory.get("Single Room") + "\n");

        doubleRoom.displayRoom();
        System.out.println("Available: " + roomInventory.get("Double Room") + "\n");

        suite.displayRoom();
        System.out.println("Available: " + roomInventory.get("Suite Room") + "\n");
    }
}