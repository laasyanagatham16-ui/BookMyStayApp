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

// Booking + Allocation Service
class BookingService {

    private Queue<Reservation> requestQueue;

    // Inventory (room type → count)
    private Map<String, Integer> inventory;

    // Track allocated room IDs (uniqueness)
    private Set<String> allocatedRoomIds;

    // Map room type → assigned room IDs
    private Map<String, Set<String>> roomAllocationMap;

    // Room ID counter
    private int roomCounter = 1;

    public BookingService() {
        requestQueue = new LinkedList<>();

        inventory = new HashMap<>();
        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);
        inventory.put("Deluxe", 1);

        allocatedRoomIds = new HashSet<>();
        roomAllocationMap = new HashMap<>();
    }

    // Add request (UC5)
    public void addBookingRequest(Reservation r) {
        requestQueue.offer(r);
        System.out.println("Request added for: " + r.getGuestName());
    }

    // Generate unique Room ID
    private String generateRoomId(String type) {
        String roomId;
        do {
            roomId = type.substring(0, 1).toUpperCase() + roomCounter++;
        } while (allocatedRoomIds.contains(roomId));

        return roomId;
    }

    // UC6: Process + Allocate
    public void processBookings() {

        System.out.println("\nProcessing Booking Requests:");

        while (!requestQueue.isEmpty()) {
            Reservation r = requestQueue.poll();
            String type = r.getRoomType();

            if (inventory.containsKey(type) && inventory.get(type) > 0) {

                // Generate unique room ID
                String roomId = generateRoomId(type);

                // Add to allocated set
                allocatedRoomIds.add(roomId);

                // Map room type → room IDs
                roomAllocationMap.putIfAbsent(type, new HashSet<>());
                roomAllocationMap.get(type).add(roomId);

                // Update inventory
                inventory.put(type, inventory.get(type) - 1);

                // Confirm booking
                System.out.println("CONFIRMED: " + r.getGuestName() +
                        " | Room Type: " + type +
                        " | Room ID: " + roomId);

            } else {
                System.out.println("FAILED: " + r.getGuestName() +
                        " | " + type + " not available");
            }
        }
    }

    // Display allocations
    public void displayAllocations() {
        System.out.println("\nRoom Allocation Details:");
        for (String type : roomAllocationMap.keySet()) {
            System.out.println(type + " -> " + roomAllocationMap.get(type));
        }
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("\nRemaining Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " -> " + inventory.get(type));
        }
    }
}

// Main class
public class UseCase6RoomAllocationService {
    public static void main(String[] args) {

        BookingService service = new BookingService();

        // Sample requests
        service.addBookingRequest(new Reservation("Alice", "Single"));
        service.addBookingRequest(new Reservation("Bob", "Double"));
        service.addBookingRequest(new Reservation("Charlie", "Single"));
        service.addBookingRequest(new Reservation("Diana", "Single")); // fail
        service.addBookingRequest(new Reservation("Eve", "Suite"));

        // Process bookings
        service.processBookings();

        // Display results
        service.displayAllocations();
        service.displayInventory();
    }
}