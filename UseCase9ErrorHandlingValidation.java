import java.util.*;

class InvalidBookingException extends Exception {
    InvalidBookingException(String msg){ super(msg); }
}

public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        Map<String,Integer> inv = new HashMap<>();
        inv.put("Single",1);

        try {
            if(!inv.containsKey("Luxury"))
                throw new InvalidBookingException("Invalid Room");

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}