import java.util.*;

class Service {
    String name;
    double cost;

    public Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

class AddOnServiceManager {

    private HashMap<String, List<Service>> map;

    public AddOnServiceManager() {
        map = new HashMap<>();
    }

    public void addService(String reservationId, Service service) {
        map.putIfAbsent(reservationId, new ArrayList<>());
        map.get(reservationId).add(service);
    }

    public double getTotalCost(String reservationId) {

        double total = 0;

        if (map.containsKey(reservationId)) {
            for (Service s : map.get(reservationId)) {
                total += s.cost;
            }
        }

        return total;
    }

    public void displayServices(String reservationId) {

        if (map.containsKey(reservationId)) {
            System.out.println("Services for Reservation " + reservationId + ":");

            for (Service s : map.get(reservationId)) {
                System.out.println(s.name + " : " + s.cost);
            }
        } else {
            System.out.println("No services selected");
        }
    }
}

public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "R101";

        manager.addService(reservationId, new Service("Breakfast", 500));
        manager.addService(reservationId, new Service("Spa", 1500));
        manager.addService(reservationId, new Service("Airport Pickup", 800));

        manager.displayServices(reservationId);

        double total = manager.getTotalCost(reservationId);

        System.out.println("Total Add-On Cost: " + total);
    }
}