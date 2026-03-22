import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Reservation class
class Reservation {
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
}

// Validator class
class BookingValidator {

    private Set<String> validRoomTypes;

    public BookingValidator() {
        validRoomTypes = new HashSet<>(Arrays.asList("Single", "Double", "Suite", "Deluxe"));
    }

    public void validate(Reservation r, Map<String, Integer> inventory)
            throws InvalidBookingException {

        if (r.getGuestName() == null || r.getGuestName().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }

        if (!validRoomTypes.contains(r.getRoomType())) {
            throw new InvalidBookingException("Invalid room type: " + r.getRoomType());
        }

        if (!inventory.containsKey(r.getRoomType())) {
            throw new InvalidBookingException("Room type not found");
        }

        if (inventory.get(r.getRoomType()) <= 0) {
            throw new InvalidBookingException("No rooms available for " + r.getRoomType());
        }
    }
}

// Booking Service
class BookingService {

    private Map<String, Integer> inventory;

    public BookingService() {
        inventory = new HashMap<>();
        inventory.put("Single", 1);
        inventory.put("Double", 1);
        inventory.put("Suite", 0);
        inventory.put("Deluxe", 1);
    }

    public void processBooking(Reservation r) {

        BookingValidator validator = new BookingValidator();

        try {
            validator.validate(r, inventory);

            inventory.put(r.getRoomType(), inventory.get(r.getRoomType()) - 1);

            System.out.println("Booking SUCCESS for " + r.getGuestName()
                    + " (" + r.getRoomType() + ")");

        } catch (InvalidBookingException e) {
            System.out.println("Booking FAILED: " + e.getMessage());
        }
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " -> " + inventory.get(type));
        }
    }
}

// Main class
public class BookMyStayApp {

    public static void main(String[] args) {

        BookingService service = new BookingService();

        service.processBooking(new Reservation("R101", "Alice", "Single"));
        service.processBooking(new Reservation("R102", "Bob", "Premium")); // invalid
        service.processBooking(new Reservation("R103", "Charlie", "Suite")); // no rooms
        service.processBooking(new Reservation("R104", "", "Double")); // empty name
        service.processBooking(new Reservation("R105", "David", "Double"));

        service.displayInventory();
    }
}