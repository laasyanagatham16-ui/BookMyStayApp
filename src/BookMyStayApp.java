import java.util.*;

// Custom Exception for Invalid Booking
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

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

// Validator Class
class BookingValidator {

    private Set<String> validRoomTypes;

    public BookingValidator() {
        validRoomTypes = new HashSet<>(Arrays.asList("Single", "Double", "Suite", "Deluxe"));
    }

    // Validate booking
    public void validate(Reservation r, Map<String, Integer> inventory)
            throws InvalidBookingException {

        // Check null / empty
        if (r.getGuestName() == null || r.getGuestName().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }

        // Validate room type
        if (!validRoomTypes.contains(r.getRoomType())) {
            throw new InvalidBookingException("Invalid room type: " + r.getRoomType());
        }

        // Check inventory existence
        if (!inventory.containsKey(r.getRoomType())) {
            throw new InvalidBookingException("Room type not found in inventory");
        }

        // Prevent negative inventory
        if (inventory.get(r.getRoomType()) <= 0) {
            throw new InvalidBookingException("No rooms available for " + r.getRoomType());
        }
    }
}

// Booking Service with Validation
class BookingService {

    private Map<String, Integer> inventory;

    public BookingService() {
        inventory = new HashMap<>();
        inventory.put("Single", 1);
        inventory.put("Double", 1);
        inventory.put("Suite", 0); // intentionally 0
        inventory.put("Deluxe", 1);
    }

    public void processBooking(Reservation r) {

        BookingValidator validator = new BookingValidator();

        try {
            // Validate before processing
            validator.validate(r, inventory);

            // Allocation logic (safe)
            inventory.put(r.getRoomType(), inventory.get(r.getRoomType()) - 1);

            System.out.println("Booking SUCCESS for " + r.getGuestName()
                    + " (" + r.getRoomType() + ")");

        } catch (InvalidBookingException e) {
            // Graceful failure
            System.out.println("Booking FAILED: " + e.getMessage());
        }
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " -> " + inventory.get(type));
        }
    }
}

// Main class
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        BookingService service = new BookingService();

        // Valid booking
        service.processBooking(new Reservation("Alice", "Single"));

        // Invalid room type
        service.processBooking(new Reservation("Bob", "Premium"));

        // No availability
        service.processBooking(new Reservation("Charlie", "Suite"));

        // Empty name
        service.processBooking(new Reservation("", "Double"));

        // Valid again
        service.processBooking(new Reservation("David", "Double"));

        service.displayInventory();
    }
}