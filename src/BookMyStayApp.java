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
}

// Shared Booking System (Thread-safe)
class ConcurrentBookingSystem {

    private Queue<Reservation> bookingQueue;
    private Map<String, Integer> inventory;

    public ConcurrentBookingSystem() {
        bookingQueue = new LinkedList<>();

        inventory = new HashMap<>();
        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);
    }

    // Add request (synchronized)
    public synchronized void addRequest(Reservation r) {
        bookingQueue.offer(r);
        System.out.println(Thread.currentThread().getName() +
                " added request for " + r.getGuestName());
    }

    // Process request (critical section)
    public synchronized void processRequest() {
        if (bookingQueue.isEmpty()) return;

        Reservation r = bookingQueue.poll();

        String type = r.getRoomType();

        if (inventory.getOrDefault(type, 0) > 0) {
            inventory.put(type, inventory.get(type) - 1);

            System.out.println(Thread.currentThread().getName() +
                    " BOOKED " + type + " for " + r.getGuestName());
        } else {
            System.out.println(Thread.currentThread().getName() +
                    " FAILED for " + r.getGuestName() +
                    " (No " + type + " rooms)");
        }
    }

    // Show inventory
    public void displayInventory() {
        System.out.println("\nFinal Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " -> " + inventory.get(type));
        }
    }
}

// Thread class
class BookingThread extends Thread {

    private ConcurrentBookingSystem system;

    public BookingThread(ConcurrentBookingSystem system) {
        this.system = system;
    }

    public void run() {
        system.processRequest();
    }
}

// Main class
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        ConcurrentBookingSystem system = new ConcurrentBookingSystem();

        // Simulate multiple users adding requests
        system.addRequest(new Reservation("Alice", "Single"));
        system.addRequest(new Reservation("Bob", "Double"));
        system.addRequest(new Reservation("Charlie", "Single"));
        system.addRequest(new Reservation("Diana", "Suite"));
        system.addRequest(new Reservation("Eve", "Single"));

        // Create multiple threads (simultaneous processing)
        Thread t1 = new BookingThread(system);
        Thread t2 = new BookingThread(system);
        Thread t3 = new BookingThread(system);
        Thread t4 = new BookingThread(system);
        Thread t5 = new BookingThread(system);

        // Start threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        // Wait for all threads
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        system.displayInventory();
    }
}