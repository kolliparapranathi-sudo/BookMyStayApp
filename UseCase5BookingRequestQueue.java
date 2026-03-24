import java.util.LinkedList;
import java.util.Queue;

class Reservation {
    String name;
    String room;

    public Reservation(String name, String room) {
        this.name = name;
        this.room = room;
    }

    public void display() {
        System.out.println(name + " -> " + room);
    }
}

public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        Queue<Reservation> queue = new LinkedList<>();

        queue.add(new Reservation("Alice", "Single Room"));
        queue.add(new Reservation("Bob", "Double Room"));
        queue.add(new Reservation("Charlie", "Suite Room"));

        for (Reservation r : queue) {
            r.display();
        }
    }
}