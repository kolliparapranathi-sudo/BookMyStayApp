import java.util.*;

class Reservation {
    String name;
    String room;

    public Reservation(String name, String room) {
        this.name = name;
        this.room = room;
    }
}

class Inventory {
    HashMap<String, Integer> data = new HashMap<>();

    public Inventory() {
        data.put("Single Room", 2);
        data.put("Double Room", 2);
        data.put("Suite Room", 1);
    }
}

public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        Queue<Reservation> queue = new LinkedList<>();

        queue.add(new Reservation("Alice", "Single Room"));
        queue.add(new Reservation("Bob", "Single Room"));
        queue.add(new Reservation("Charlie", "Single Room"));

        Inventory inv = new Inventory();
        Set<String> usedIds = new HashSet<>();

        while (!queue.isEmpty()) {

            Reservation r = queue.poll();

            int count = inv.data.getOrDefault(r.room, 0);

            if (count > 0) {

                String id;
                do {
                    id = r.room.substring(0, 2) + new Random().nextInt(1000);
                } while (usedIds.contains(id));

                usedIds.add(id);
                inv.data.put(r.room, count - 1);

                System.out.println("Confirmed: " + r.name + " | " + id);
            } else {
                System.out.println("Failed: " + r.name);
            }
        }
    }
}