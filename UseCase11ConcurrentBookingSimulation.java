// UC11: Concurrent Booking Simulation (Thread Safety)
// Version: 11.0

import java.util.*;

// Reservation
class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Shared Inventory
class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
    }

    // synchronized critical section
    public synchronized boolean allocate(String roomType, String guestName) {

        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            inventory.put(roomType, available - 1);
            System.out.println("Allocated → " + guestName + " | " + roomType);
            return true;
        } else {
            System.out.println("Failed → " + guestName + " | No availability");
            return false;
        }
    }
}

// Shared Queue
class BookingQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public synchronized void add(Reservation r) {
        queue.add(r);
    }

    public synchronized Reservation get() {
        return queue.poll();
    }
}

// Worker Thread
class BookingProcessor extends Thread {

    private BookingQueue queue;
    private RoomInventory inventory;

    public BookingProcessor(BookingQueue queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {

        while (true) {

            Reservation r;

            synchronized (queue) {
                r = queue.get();
            }

            if (r == null) break;

            inventory.allocate(r.roomType, r.guestName);
        }
    }
}

// Main Class
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" BOOK MY STAY APP - UC11 ");
        System.out.println("=================================");

        RoomInventory inventory = new RoomInventory();
        BookingQueue queue = new BookingQueue();

        // Simulate multiple requests
        queue.add(new Reservation("Alice", "Single Room"));
        queue.add(new Reservation("Bob", "Single Room"));
        queue.add(new Reservation("Charlie", "Single Room"));

        // Multiple threads
        BookingProcessor t1 = new BookingProcessor(queue, inventory);
        BookingProcessor t2 = new BookingProcessor(queue, inventory);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        System.out.println("\nSimulation completed safely!");
    }
}