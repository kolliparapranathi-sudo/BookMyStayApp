import java.util.HashMap;

class Room {
    String type;
    double price;

    public Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public void display() {
        System.out.println(type + " | Price: " + price);
    }
}

class Inventory {
    HashMap<String, Integer> data = new HashMap<>();

    public Inventory() {
        data.put("Single Room", 5);
        data.put("Double Room", 3);
        data.put("Suite Room", 0);
    }
}

public class UseCase4RoomSearch {

    public static void main(String[] args) {

        Inventory inv = new Inventory();

        Room s = new Room("Single Room", 2000);
        Room d = new Room("Double Room", 3500);
        Room su = new Room("Suite Room", 6000);

        for (String key : inv.data.keySet()) {

            int count = inv.data.get(key);

            if (count > 0) {
                if (key.equals("Single Room")) s.display();
                if (key.equals("Double Room")) d.display();
                if (key.equals("Suite Room")) su.display();

                System.out.println("Available: " + count);
            }
        }
    }
}