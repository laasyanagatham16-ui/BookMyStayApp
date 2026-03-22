import java.util.*;

// Reservation class
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayReservation() {
        System.out.println("Guest Name: " + guestName + ", Room Type: " + roomType);
    }
}

// Booking system with Queue + Allocation
class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    // Inventory: room type → available count
    private Map<String, Integer> roomInventory;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();

        // Initial room availability
        roomInventory = new HashMap<>();
        roomInventory.put("Single", 2);
        roomInventory.put("Double", 1);
        roomInventory.put("Suite", 1);
        roomInventory.put("Deluxe", 1);
    }

    // Add booking request (UC5)
    public void addBookingRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("Booking request added for: " + reservation.getGuestName());
    }

    // Display queue (UC5)
    public void displayQueue() {
        System.out.println("\nBooking Requests in Queue:");
        for (Reservation r : requestQueue) {
            r.displayReservation();
        }
    }

    // UC6: Process and allocate rooms
    public void processAndAllocate() {
        System.out.println("\nProcessing Booking Requests (FCFS Allocation):");

        while (!requestQueue.isEmpty()) {
            Reservation r = requestQueue.poll();
            String type = r.getRoomType();

            if (roomInventory.containsKey(type) && roomInventory.get(type) > 0) {
                // Allocate room
                roomInventory.put(type, roomInventory.get(type) - 1);
                System.out.println("Booking CONFIRMED for " + r.getGuestName() + " (" + type + ")");
            } else {
                // No rooms available
                System.out.println("Booking FAILED for " + r.getGuestName() + " (" + type + " - Not Available)");
            }
        }
    }

    // Show remaining rooms
    public void displayInventory() {
        System.out.println("\nRemaining Room Inventory:");
        for (String type : roomInventory.keySet()) {
            System.out.println(type + " -> " + roomInventory.get(type));
        }
    }
}

// Main class
public class UseCase6RoomAllocation {

    public static void main(String[] args) {

        BookingRequestQueue bookingSystem = new BookingRequestQueue();

        // Add booking requests
        bookingSystem.addBookingRequest(new Reservation("Alice", "Single"));
        bookingSystem.addBookingRequest(new Reservation("Bob", "Double"));
        bookingSystem.addBookingRequest(new Reservation("Charlie", "Single"));
        bookingSystem.addBookingRequest(new Reservation("Diana", "Single")); // will fail
        bookingSystem.addBookingRequest(new Reservation("Eve", "Suite"));

        // UC5 part
        bookingSystem.displayQueue();

        // UC6 part
        bookingSystem.processAndAllocate();

        // Show remaining rooms
        bookingSystem.displayInventory();
    }
}