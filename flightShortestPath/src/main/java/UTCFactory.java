// UTCs provides a static factory method that can be used to create a
//     new instance of UTC

public class UTCFactory {

    // GIVEN: the hour in [0,23] and the minute in [0,59]
    // RETURNS: a UTC with that hour and minute
    public static UTC make (int h, int m) {
        return new UTCClass (h, m);
    }

}