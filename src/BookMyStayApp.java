/**
 * BookMyStayApp
 * Hotel Booking Management System
 * @author Laasya
 * @version 2.1
 */

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
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds : " + beds);
        System.out.println("Price : $" + price);
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

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println(" Welcome to Book My Stay App ");
        System.out.println(" Hotel Booking System v2.1 ");
        System.out.println("================================");

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        System.out.println("\n--- Room Details ---");

        single.displayRoom();
        System.out.println("Available Rooms : " + singleAvailability + "\n");

        doubleRoom.displayRoom();
        System.out.println("Available Rooms : " + doubleAvailability + "\n");

        suite.displayRoom();
        System.out.println("Available Rooms : " + suiteAvailability + "\n");

    }
}