import java.util.*;

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

    public String toString() {
        return reservationId + " | " + guestName + " | " + roomType;
    }
}

// Booking History (stores confirmed bookings)
class BookingHistory {
    private List<Reservation> historyList;

    public BookingHistory() {
        historyList = new ArrayList<>();
    }

    // Add confirmed booking
    public void addBooking(Reservation r) {
        historyList.add(r);
    }

    // Get all bookings
    public List<Reservation> getAllBookings() {
        return historyList;
    }
}

// Reporting Service
class BookingReportService {

    // Display all bookings
    public void showAllBookings(List<Reservation> bookings) {
        System.out.println("\nBooking History:");

        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Reservation r : bookings) {
            System.out.println(r);
        }
    }

    // Summary report (count per room type)
    public void generateSummary(List<Reservation> bookings) {
        Map<String, Integer> summary = new HashMap<>();

        for (Reservation r : bookings) {
            String type = r.getRoomType();
            summary.put(type, summary.getOrDefault(type, 0) + 1);
        }

        System.out.println("\nBooking Summary Report:");
        for (String type : summary.keySet()) {
            System.out.println(type + " -> " + summary.get(type));
        }
    }
}

// Main class
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Simulating confirmed bookings (from UC6)
        history.addBooking(new Reservation("R101", "Alice", "Single"));
        history.addBooking(new Reservation("R102", "Bob", "Double"));
        history.addBooking(new Reservation("R103", "Charlie", "Single"));
        history.addBooking(new Reservation("R104", "Eve", "Suite"));

        // Admin views history
        reportService.showAllBookings(history.getAllBookings());

        // Generate report
        reportService.generateSummary(history.getAllBookings());
    }
}