// UC10: Booking Cancellation & Inventory Rollback
// Version: 10.0

import java.util.*;

// Reservation Class
class Reservation {
    String guestName;
    String roomType;
    String roomId;

    public Reservation(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }
}

// Inventory
class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
    }

    public void increase(String roomType) {
        inventory.put(roomType, inventory.get(roomType) + 1);
    }

    public void display() {
        System.out.println("\n--- Inventory ---");
        for (String key : inventory.keySet()) {
            System.out.println(key + " : " + inventory.get(key));
        }
    }
}

// Cancellation Service
class CancellationService {

    private Map<String, Reservation> bookings = new HashMap<>();
    private Stack<String> rollbackStack = new Stack<>();

    public void addBooking(Reservation r) {
        bookings.put(r.roomId, r);
    }

    public void cancel(String roomId, RoomInventory inventory) {

        if (!bookings.containsKey(roomId)) {
            System.out.println("Cancellation Failed: Invalid Room ID");
            return;
        }

        Reservation r = bookings.get(roomId);

        // Push to rollback stack
        rollbackStack.push(roomId);

        // Restore inventory
        inventory.increase(r.roomType);

        // Remove booking
        bookings.remove(roomId);

        System.out.println("Booking Cancelled → " + r.guestName +
                " | " + r.roomType +
                " | Room ID: " + roomId);
    }

    public void showRollbackStack() {
        System.out.println("\nRollback Stack: " + rollbackStack);
    }
}

// Main Class
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" BOOK MY STAY APP - UC10 ");
        System.out.println("=================================");

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        // Existing bookings
        service.addBooking(new Reservation("Alice", "Single Room", "SI_1"));
        service.addBooking(new Reservation("Bob", "Double Room", "DO_2"));

        // Cancel booking
        service.cancel("SI_1", inventory);

        // Invalid cancellation
        service.cancel("SI_1", inventory);

        // Display
        inventory.display();
        service.showRollbackStack();

        System.out.println("\nApplication executed successfully!");
    }
}