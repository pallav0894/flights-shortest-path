import java.util.ArrayList;

// A PathTime is an object of any class that implements GetValue.
//
// Interpretation: A GetValue represents a collection of values which
// records a path and time from a particular source that
// encapsulates the information supplied by the operations
// listed in this interface.

public interface PathTime {

    // returns a ArrayList representing a path of Flight from the source
    //     to the particular destination
    ArrayList path ();

    // returns the time for a given path, as described above
    int time ();

}
