import java.io.*;
import java.util.*;

// Reservation class (Serializable)
class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;

    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String toString() {
        return reservationId + " | " + guestName + " | " + roomType;
    }
}

// Wrapper class for persistence
class SystemState implements Serializable {
    private static final long serialVersionUID = 1L;

    Map<String, Integer> inventory;
    List<Reservation> bookings;

    public SystemState(Map<String, Integer> inventory, List<Reservation> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "system_state.ser";

    // Save data
    public void save(SystemState state) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(state);
            System.out.println("System state saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving system state: " + e.getMessage());
        }
    }

    // Load data
    public SystemState load() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            System.out.println("System state loaded successfully.");
            return (SystemState) ois.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("No saved state found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading state: " + e.getMessage());
        }

        return null;
    }
}

// Main class
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        PersistenceService service = new PersistenceService();

        // Try loading previous state
        SystemState state = service.load();

        Map<String, Integer> inventory;
        List<Reservation> bookings;

        if (state != null) {
            inventory = state.inventory;
            bookings = state.bookings;
        } else {
            // Fresh start
            inventory = new HashMap<>();
            inventory.put("Single", 2);
            inventory.put("Double", 1);
            inventory.put("Suite", 1);

            bookings = new ArrayList<>();
        }

        // Simulate booking
        Reservation r1 = new Reservation("R201", "Alice", "Single");
        Reservation r2 = new Reservation("R202", "Bob", "Double");

        if (inventory.get("Single") > 0) {
            bookings.add(r1);
            inventory.put("Single", inventory.get("Single") - 1);
        }

        if (inventory.get("Double") > 0) {
            bookings.add(r2);
            inventory.put("Double", inventory.get("Double") - 1);
        }

        // Display current state
        System.out.println("\nCurrent Bookings:");
        for (Reservation r : bookings) {
            System.out.println(r);
        }

        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " -> " + inventory.get(type));
        }

        // Save state before shutdown
        service.save(new SystemState(inventory, bookings));
    }
}