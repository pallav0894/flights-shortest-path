// Constructor template for FlightClass:
//     new FlightClass(name, ap1, ap2, t1, t2)
// Interpretation:
// name is the name of the flight
// ap1 and ap2 are the src and dest respectively
// t1 and t2 are UTCs which are the departure
// and arrival time of the flight

public class FlightClass implements Flight {

    // Data Definitions and Constructor
    String name;
    String departs;
    String arrives;
    UTC departsAt;
    UTC arrivesAt;

    FlightClass (String name, String ap1, String ap2, UTC t1, UTC t2) {
        this.name = name;
        this.departs = ap1;
        this.arrives = ap2;
        this.departsAt = t1;
        this.arrivesAt = t2;
    }

    // Returns the name of the flight
    public String name () {
        return name;
    }

    // Returns the name of the airport from which this flight departs.
    public String departs () {
        return departs;
    }

    // Returns the name of the airport at which this flight arrives.
    public String arrives () {
        return this.arrives;
    }

    // Returns the time at which this flight departs.
    public UTC departsAt () {
        return departsAt;
    }

    // Returns the time at which this flight arrives.
    public UTC arrivesAt () {
        return arrivesAt;
    }

    // Returns true iff this flight and the given flight
    //     have the same name
    //     depart from the same airport
    //     arrive at the same airport
    //     depart at the same time
    // and arrive at the same time
    public boolean isEqual (Flight f2) {
        return ((this.name.equals(f2.name()))&&
                (this.departs.equals(f2.departs()))&&
                (this.arrives.equals(f2.arrives()))&&
                (this.departsAt.isEqual(f2.departsAt()))&&
                (this.arrivesAt.isEqual(f2.arrivesAt())));
    }

    // Helper function for isEqual, checks if the given object is a Flight
    public boolean equals(Object x) {
        if (x instanceof Flight) {
            Flight flt = (Flight) x;
            return isEqual(flt);
        }
        else return false;
    }
}
