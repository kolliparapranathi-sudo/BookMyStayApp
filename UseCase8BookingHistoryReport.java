import java.util.*;

class Reservation {
    String guestName, roomType, roomId;

    Reservation(String g, String r, String id) {
        guestName = g;
        roomType = r;
        roomId = id;
    }

    void display() {
        System.out.println(guestName + " | " + roomType + " | " + roomId);
    }
}

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        List<Reservation> history = new ArrayList<>();

        history.add(new Reservation("Alice","Single","SI_1"));
        history.add(new Reservation("Bob","Double","DO_2"));

        for(Reservation r : history) r.display();

        System.out.println("Total: " + history.size());
    }
}