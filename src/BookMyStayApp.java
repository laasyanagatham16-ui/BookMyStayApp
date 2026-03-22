<<<<<<< HEAD
import java.util.*;

// Class representing a booking request (Reservation)
class Reservation {
    private String guestName;
    private String roomType;

    // Constructor
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    // Getter methods
    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    // Display method
    public void displayReservation() {
        System.out.println("Guest Name: " + guestName + ", Room Type: " + roomType);
    }
}

// Class representing the Booking Request Queue system
class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    // Constructor initializes queue
    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Method to accept booking request
    public void addBookingRequest(Reservation reservation) {
        requestQueue.offer(reservation); // FIFO insertion
        System.out.println("Booking request added for: " + reservation.getGuestName());
    }

    // Method to display all queued requests (NO removal)
    public void displayQueue() {
        if (requestQueue.isEmpty()) {
            System.out.println("No booking requests in queue.");
            return;
        }

        System.out.println("\nBooking Requests in Queue (First-Come-First-Served Order):");
        for (Reservation r : requestQueue) {
            r.displayReservation();
        }
    }

    // IMPORTANT: No processing/removal here (as per UC5)
}

// Main class
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        // Create Booking Queue System
        BookingRequestQueue bookingSystem = new BookingRequestQueue();
=======
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
>>>>>>> d39461c9f2439995c4f07241d52e011f3c8121b2

        // Simulating multiple guest requests
        Reservation r1 = new Reservation("Alice", "Single");
        Reservation r2 = new Reservation("Bob", "Double");
        Reservation r3 = new Reservation("Charlie", "Suite");
        Reservation r4 = new Reservation("Diana", "Deluxe");

        // Step 1: Accept booking requests
        bookingSystem.addBookingRequest(r1);
        bookingSystem.addBookingRequest(r2);
        bookingSystem.addBookingRequest(r3);
        bookingSystem.addBookingRequest(r4);

        // Step 2: Display queue (order preserved, no processing)
        bookingSystem.displayQueue();

        // NOTE:
        // No allocation / no removal / no processing here (as per Use Case 5)
    }
}