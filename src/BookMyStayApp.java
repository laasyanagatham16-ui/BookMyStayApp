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