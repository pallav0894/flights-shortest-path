// Constructor template for UTC1:
//     new UTC1(h, m)
// Interpretation:
//     h and m are the hours and minutes corresponding to a UTC
//     (an Object of Universal Coordinated Time)

public class UTCClass implements UTC {

    int h; //the hour, limited to [0,23]
    int m; //the minute, limited to [0,59]

    UTCClass(int hour, int minute) {
        this.h = hour;
        this.m = minute;
    }

    // Returns the hour, between 0 and 23 inclusive.
    public int hour () {
        return h;
    }

    // Returns the minute, between 0 and 59 inclusive.
    public int minute () {
        return m;
    }

    // Returns true iff the given UTC is equal to this UTC.
    public boolean isEqual(UTC t2) {

        return ((this.h == t2.hour())&&(this.m == t2.minute()));

    }
}
