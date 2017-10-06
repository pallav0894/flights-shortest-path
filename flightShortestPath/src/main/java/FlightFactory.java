// Flights provides a static factory method that can be used to create a
//     new instance of Flight

public class FlightFactory {

    // static factory method makes a flight
    public static Flight make(String name, String ap1, String ap2,
                              UTC t1, UTC t2) {
        return new FlightClass(name, ap1, ap2, t1, t2);
    }
}
