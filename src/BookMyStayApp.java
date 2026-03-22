import java.util.*;

// Reservation class (with ID from UC6)
class Reservation {
    private String reservationId;
    private String guestName;

    public Reservation(String reservationId, String guestName) {
        this.reservationId = reservationId;
        this.guestName = guestName;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }
}

// Add-On Service class
class Service {
    private String serviceName;
    private double cost;

    public Service(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }

    public String toString() {
        return serviceName + " (₹" + cost + ")";
    }
}

// Add-On Service Manager
class AddOnServiceManager {

    // Map: Reservation ID → List of Services
    private Map<String, List<Service>> serviceMap;

    public AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    // Add service to reservation
    public void addService(String reservationId, Service service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);

        System.out.println("Added service: " + service.getServiceName() +
                " to Reservation ID: " + reservationId);
    }

    // Display services
    public void displayServices(String reservationId) {
        System.out.println("\nServices for Reservation ID: " + reservationId);

        List<Service> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services added.");
            return;
        }

        for (Service s : services) {
            System.out.println(s);
        }
    }

    // Calculate total cost
    public double calculateTotalCost(String reservationId) {
        List<Service> services = serviceMap.get(reservationId);
        double total = 0;

        if (services != null) {
            for (Service s : services) {
                total += s.getCost();
            }
        }

        return total;
    }
}

// Main class
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        // Sample reservations (from UC6)
        Reservation r1 = new Reservation("R101", "Alice");
        Reservation r2 = new Reservation("R102", "Bob");

        AddOnServiceManager manager = new AddOnServiceManager();

        // Add services
        manager.addService(r1.getReservationId(), new Service("Breakfast", 200));
        manager.addService(r1.getReservationId(), new Service("WiFi", 100));
        manager.addService(r2.getReservationId(), new Service("Airport Pickup", 500));

        // Display services
        manager.displayServices(r1.getReservationId());
        manager.displayServices(r2.getReservationId());

        // Cost calculation
        System.out.println("\nTotal Cost for " + r1.getReservationId() + ": ₹" +
                manager.calculateTotalCost(r1.getReservationId()));

        System.out.println("Total Cost for " + r2.getReservationId() + ": ₹" +
                manager.calculateTotalCost(r2.getReservationId()));
    }
}