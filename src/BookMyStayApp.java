/**
 * BookMyStayApp
 * Hotel Booking Management System
 * @author Laasya
 * @version 4.0
 */

import java.util.HashMap;

// UC2: Abstract Room class
abstract class Room {

    String roomType;
    int beds;
    double price;

    Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    void displayRoomDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Price     : $" + price);
    }
}

// UC2: Single Room
class SingleRoom extends Room {

    SingleRoom() {
        super("Single Room", 1, 100);
    }
}

// UC2: Double Room
class DoubleRoom extends Room {

    DoubleRoom() {
        super("Double Room", 2, 180);
    }
}

// UC2: Suite Room
class SuiteRoom extends Room {

    SuiteRoom() {
        super("Suite Room", 3, 350);
    }
}

public class BookMyStayApp {

    // UC3: Room inventory using HashMap
    static HashMap<String, Integer> roomInventory = new HashMap<>();

    // UC4: Booking method
    static void bookRoom(String roomType) {

        if (roomInventory.containsKey(roomType) && roomInventory.get(roomType) > 0) {

            roomInventory.put(roomType, roomInventory.get(roomType) - 1);

            System.out.println(roomType + " booked successfully!");
            System.out.println("Remaining Rooms: " + roomInventory.get(roomType));
        } else {

            System.out.println(roomType + " is not available!");
        }
    }

    public static void main(String[] args) {

        // UC1: Welcome message
        System.out.println("=================================");
        System.out.println(" Welcome to Book My Stay App ");
        System.out.println(" Hotel Booking System v4.0 ");
        System.out.println("=================================");

        // UC2: Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // UC3: Initialize inventory
        roomInventory.put("Single Room", 5);
        roomInventory.put("Double Room", 3);
        roomInventory.put("Suite Room", 2);

        System.out.println("\n--- Room Details ---");

        single.displayRoomDetails();
        System.out.println("Available: " + roomInventory.get("Single Room") + "\n");

        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + roomInventory.get("Double Room") + "\n");

        suite.displayRoomDetails();
        System.out.println("Available: " + roomInventory.get("Suite Room") + "\n");

        // UC4: Booking rooms
        System.out.println("---- Booking Rooms ----");

        bookRoom("Single Room");
        bookRoom("Double Room");
        bookRoom("Suite Room");
    }
}