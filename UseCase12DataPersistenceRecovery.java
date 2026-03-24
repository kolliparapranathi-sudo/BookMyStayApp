// UC12: Data Persistence & System Recovery
// Version: 12.0

import java.io.*;
import java.util.*;

// Serializable Reservation
class Reservation implements Serializable {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println(guestName + " | " + roomType);
    }
}

// Serializable Inventory
class RoomInventory implements Serializable {
    Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    public void display() {
        System.out.println("\nInventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + " : " + inventory.get(key));
        }
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "data.ser";

    // Save
    public void save(RoomInventory inventory, List<Reservation> history) {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            out.writeObject(inventory);
            out.writeObject(history);

            System.out.println("\nData saved successfully.");

        } catch (Exception e) {
            System.out.println("Error saving data.");
        }
    }

    // Load
    public Object[] load() {

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            RoomInventory inventory = (RoomInventory) in.readObject();
            List<Reservation> history = (List<Reservation>) in.readObject();

            System.out.println("\nData loaded successfully.");

            return new Object[]{inventory, history};

        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
            return null;
        }
    }
}

// Main Class
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" BOOK MY STAY APP - UC12 ");
        System.out.println("=================================");

        PersistenceService service = new PersistenceService();

        RoomInventory inventory;
        List<Reservation> history;

        // Load previous data
        Object[] data = service.load();

        if (data != null) {
            inventory = (RoomInventory) data[0];
            history = (List<Reservation>) data[1];
        } else {
            inventory = new RoomInventory();
            history = new ArrayList<>();
        }

        // Simulate booking
        history.add(new Reservation("Alice", "Single Room"));
        history.add(new Reservation("Bob", "Double Room"));

        // Display
        inventory.display();

        System.out.println("\nBooking History:");
        for (Reservation r : history) {
            r.display();
        }

        // Save state
        service.save(inventory, history);

        System.out.println("\nApplication recovered successfully!");
    }
}