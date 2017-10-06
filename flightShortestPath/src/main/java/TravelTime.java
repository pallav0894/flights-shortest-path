import java.util.ArrayList;

// TravelTime provides helper functions for time calculation.

public class TravelTime {

    // totalTravelTime: ListofFlight -> NonNegInteger
    // Given: a list of flight
    // Returns: a number that indicates the total travel time in minutes
    // Strategy: Iterate on the given list and combine simpler functions.
    public static int
    totalTravelTime(ArrayList<Flight> lstFlt) {
        int time = 0;

//              System.out.println("flag1");
        for(int i=0; i<lstFlt.size(); i++) {

            if((i+1) == lstFlt.size()) {

                time += timeOfAFlight(lstFlt.get(i));
            }
            else {
                time += timeOfAFlight(lstFlt.get(i))
                        + layover(lstFlt.get(i), lstFlt.get(i+1));
            }
        }
        return time;
    }

    // timeOfAFlight: Flight -> NonNegInt
    // GIVEN: A Flight
    // RETURNS: time of a flight in minutes
    public static int timeOfAFlight(Flight flt) {
        return timeDifference(flt.departsAt(), flt.arrivesAt());
    }


    // layover: Flight Flight -> NonNegInt
    // GIVEN: 2 Flights
    // RETURNS: layover time between 2 given flights
    public static int layover(Flight flt1, Flight flt2) {
        return timeDifference(flt1.arrivesAt(), flt2.departsAt());
    }

    // timeDifference: UTC UTC -> NonNegInt
    // Given: UTC for when a flight departs and when a flight arrives
    // Returns: time difference between given UTCs
    // Strategy: Divide on cases
    public static int timeDifference(UTC dep, UTC arr) {
        if (isNextDay(dep, arr)) {
            return overnightTimeDiff(dep, arr);
        }
        else {
            return sameDayTimeDiff(dep, arr);
        }
    }

    // overnightTimeDiff: UTC UTC -> NonNegInt
    // GIVEN: departure and arrival time
    // WHERE: the UTCs belong to different date
    // RETURNS: time of an overnight itinarary
    public static int overnightTimeDiff(UTC dep, UTC arr) {
        int t1 = timeDifference(dep, UTCFactory.make(23, 59));
        int t2 = timeDifference(UTCFactory.make(00, 00), arr);
        return t1 + t2 + 1;
    }


    // sameDayTimeDiff: UTC UTC -> NonNegInt
    // GIVEN: departure and arrival time
    // WHERE: the UTCs belong to same date
    // RETURNS: time of a same day itinerary
    public static int sameDayTimeDiff(UTC dep, UTC arr) {
        int hourDep = dep.hour();
        int hourArr = arr.hour();
        int minDep = dep.minute();
        int minArr = arr.minute();
        int hrDiff = hourDiff(hourDep, hourArr);
        int mnDiff = minDiff(minDep, minArr);

        if (minDep > minArr) {
            return (60 * (hrDiff - 1)) + mnDiff;
        }
        else {
            return (60 * hrDiff) + mnDiff;
        }
    }

    // isNextDay? UTC UTC -> Boolean
    // Given: two UTCs
    // Returns: true if the first UTC is later than second UTC, false otherwise
    // Strategy: Divide on cases
    public static Boolean isNextDay(UTC utc1, UTC utc2) {
        if (utc1.hour() > utc2.hour()) {
            return true;
        }
        else if ((utc1.hour() == utc2.hour()) &&
                ((utc1.minute() > utc2.minute()))) {
            return true;
        }
        else if ((utc1.hour() == utc2.hour()) &&
                (utc1.minute() == utc2.minute())) {
            return false;
        }
        else if ((utc1.hour() == utc2.hour()) &&
                (utc1.minute() < utc2.minute())) {
            return false;
        }
        else return false;
    }

    // hourDiff: NonNegInt NonNegInt -> NonNegInt
    // GIVEN: start hour and arrive hour of a flight
    // RETURNS: Hour difference
    public static int hourDiff(int h1, int h2) {
        if (h1 > h2) {
            return ((h2 - h1) + 24);
        }
        else {
            return (h2 - h1);
        }
    }

    // minDiff: NonNegInt NonNegInt -> NonNegInt
    // GIVEN: starting minutes and arriving minutes of a flight
    // RETURNS: Minute difference
    public static int minDiff(int m1, int m2) {
        if (m1 > m2) {
            return ((m2 - m1) + 60);
        }
        else {
            return (m2 - m1);
        }
    }
}
