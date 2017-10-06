import java.util.*;

// Schedules implements three primary functions: canGetThere(),
//     fastestItinerary(), and travelTime.

public class PathFinder {

    // Data Definitions and Constants

    // A HashMap of type <Integer,GetValue> which is all the possible
    //     fastest path from each flight originating from the source
    static HashMap<Integer,PathTime> finalPaths = new HashMap();

    // ArrayList of fastest possible path from the source to the destinaion.
    static ArrayList<Flight> finalItineraries = new ArrayList();

    // A holder HashMap which records the path and time from the given source
    // to each possible node.
    static HashMap<String,PathTime> paths = new HashMap();

    // Initial value for time if no path exists.
    final static int MAX_TIME = 999999999;

    // GIVEN: the names of two airports, ap1 and ap2 (respectively),
    //     and a RacketList<Flight%gt; that describes all of the flights a
    //     traveller is willing to consider taking
    // RETURNS: true if and only if it is possible to fly from the
    //     first airport (ap1) to the second airport (ap2) using
    //     only the given flights
    // EXAMPLES:
    //     canGetThere ("06N", "JFK", FlightExamples.panAmFlights)  =>  false
    //     canGetThere ("JFK", "JFK", FlightExamples.panAmFlights)  =>  true
    //     canGetThere ("06N", "LAX", FlightExamples.deltaFlights)  =>  false
    //     canGetThere ("LAX", "06N", FlightExamples.deltaFlights)  =>  false
    //     canGetThere ("LGA", "PDX", FlightExamples.deltaFlights)  =>  true
    // Strategy: Dividing by cases
    public static boolean canGetThere(String ap1,
                                      String ap2,
                                      ArrayList<Flight> flights) {

        if(ap1.equals(ap2)) {
            return true;
        }
        else if(flights.isEmpty()) {
            return false;
        }
        else {
            ArrayList<Flight> rktLst = fastestItinerary(ap1, ap2, flights);

            if(rktLst.isEmpty()) {
                return false;
            }
            else {
                return true;
            }
        }
    }

    // GIVEN: the names of two airports, ap1 and ap2 (respectively),
    //     and a RacketList<Flight> that describes all of the flights a
    //     traveller is willing to consider taking
    // WHERE: it is possible to fly from the first airport (ap1) to
    //     the second airport (ap2) using only the given flights
    // RETURNS: a list of flights that tells how to fly from the
    //     first airport (ap1) to the second airport (ap2) in the
    //     least possible time, using only the given flights
    // NOTE: to simplify the problem, your program should incorporate
    //     the totally unrealistic simplification that no layover
    //     time is necessary, so it is possible to arrive at 1500
    //     and leave immediately on a different flight that departs
    //     at 1500
    // EXAMPLES:
    //     fastestItinerary ("JFK", "JFK", FlightExamples.panAmFlights)
    //         =>  RacketLists.empty()
    //
    //     fastestItinerary ("LGA", "PDX", FlightExamples.deltaFlights)
    // =>  RacketLists.empty().cons
    //         (Flights.make ("Delta 2163",
    //                        "MSP", "PDX",
    //                        UTCs.make (15, 0), UTCs.make (19, 2))).cons
    //             (Flights.make ("Delta 0121",
    //                            "LGA", "MSP",
    //                            UTCs.make (11, 0),
    //                            UTCs.make (14, 9)))
    // Strategy: Divide by cases and combine simpler functions
    public static ArrayList<Flight> fastestItinerary(String ap1,
                                                      String ap2,
                                                      ArrayList<Flight> flights) {

//        // Convert given RacketList to ArrayList
//        ArrayList<Flight> newArrLst =
//                RacketListHelper.racketListToArrayList(flights,
//                        new ArrayList());

        HashMap<Integer,PathTime> finalMap;

        if ((flights.isEmpty())||(ap1.equals(ap2))) {
            return new ArrayList<Flight>();
        }
        else {
            finalMap = initialize(flights, ap1, ap2);
        }

        findMin(finalMap);
//
//        // Convert the computed ArrayList to RacketList
//        RacketList<Flight> finalRktItineraries
//                = RacketListHelper.arrayListToRacketList(finalItineraries,
//                new RacketLists1());
//        return finalRktItineraries;
        return finalItineraries;
    }


    // GIVEN: An ArrayList of flights, a source and a destination
    // RETURNS: A HashMap of type <Integer,GetValue> which is all the possible
    //     fastest path from each flight originating from the source
    // EXAMPLES:
    //     ArrayList<Flight> newArrList =
    //                RacketLists1.racketListToArrayList
    //                         (FlightExamples.deltaFlights1, new ArrayList());
    //     (initialize(newArrList, "LGA", "MSP")); =>
    //     {0=Flights.make("Delta 0121", "LGA", "MSP",
    //                      UTCs.make(11, 00), UTCs.make(14, 9)), 189}
    // Strategy: Iterate on the list of flights
    public static HashMap initialize(ArrayList<Flight> arrLst,
                                     String src, String dest) {
        Flight flt;
        int j = 0;
        for (int i=0; i<arrLst.size(); i++) {
            flt = arrLst.get(i);
            if (flt.departs().equals(src)) {
                paths.clear();
                paths.put(dest, new PathTimeClass(new ArrayList(),MAX_TIME));
                ArrayList<Flight> fltArrLst = new ArrayList();
                fltArrLst.add(flt);
                paths.put(flt.arrives(),
                        new PathTimeClass(fltArrLst,
                                TravelTime.totalTravelTime(fltArrLst)));
                PathTime destPath = process(arrLst, dest);
                finalPaths.put(j, destPath);
                j += 1;
            }
        }
        return finalPaths;
    }

    // GIVEN: An array list of flights the traveler is willing to
    //        to take, and the final destination
    // RETURNS: A GetValue representing a single path and the time corresponding
    //          to a flight starting from the source
    // EXAMPLES:
    //     ArrayList<Flight> newArrList =
    //                RacketLists1.racketListToArrayList
    //                         (FlightExamples.deltaFlights1, new ArrayList());
    //     (process(newArrList, "MSP")); =>
    //     {Flights.make("Delta 0121", "LGA", "MSP",
    //                    UTCs.make(11, 00), UTCs.make(14, 9)), 189}
    // Strategy: Iterate over list of flights.
    public static PathTime process(ArrayList<Flight> arrLst, String dest) {
        Flight newFlt;
        for(int j=0; j<arrLst.size(); j++) {

            for(int i=0; i<arrLst.size(); i++) {
                newFlt = arrLst.get(i);
                ArrayList<Flight> lstOfPath = new ArrayList();

                if(paths.containsKey(newFlt.departs())) {
                    lstOfPath.addAll(paths.get(newFlt.departs()).path());

                    if(!(lstOfPath.isEmpty())) {
                        if(paths.containsKey(newFlt.arrives())) {

                            int time2 = paths.get(newFlt.arrives()).time();
                            ArrayList<Flight> tempList = new ArrayList();
                            tempList.addAll(lstOfPath);
                            tempList.add(newFlt);

                            int time = TravelTime.totalTravelTime(tempList);

                            if (time < time2) {
                                paths.put(newFlt.arrives(),
                                        new PathTimeClass(tempList, time));
                            }
                        }
                        else {

                            lstOfPath.add(newFlt);
                            int newTime = TravelTime.totalTravelTime(lstOfPath);
                            paths.put(newFlt.arrives(),
                                    new PathTimeClass(lstOfPath, newTime));
                        }
                    }
                }
            }
        }

        PathTime destPath = paths.get(dest);
        return destPath;
    }

    // GIVEN: A HashMap representing all possible flights from source
    //        to destination
    // RETURNS: An ArrayList representing the fastest path in the given HashMap
    // EXAMPLES:
    //    (findMin ({0=Flights.make("Delta 0121", "LGA", "MSP",
    //                               UTCs.make(11, 00), UTCs.make(14, 9)), 189})
    //     =>
    //    [Flights.make("Delta 0121", "LGA", "MSP", UTCs.make(11, 00),
    //                                              UTCs.make(14, 9))]
    // Strategy: Iterate over HashMap and combine simpler functions
    public static ArrayList findMin(HashMap<Integer,PathTime> hm) {
        int min = MAX_TIME;
        for (int i=0; i<hm.size(); i++) {
            if (hm.get(i).time() < min) {
                min = hm.get(i).time();
                finalItineraries = hm.get(i).path();
            }
        }
        return finalItineraries;
    }


    // GIVEN: the names of two airports, ap1 and ap2 (respectively),
    //     and a RacketList<Flight> that describes all of the flights a
    //     traveller is willing to consider taking
    // WHERE: it is possible to fly from the first airport (ap1) to
    //     the second airport (ap2) using only the given flights
    // RETURNS: the number of minutes it takes to fly from the first
    //     airport (ap1) to the second airport (ap2), including any
    //     layovers, by the fastest possible route that uses only
    //     the given flights
    // EXAMPLES:
    //     travelTime ("JFK", "JFK", FlightExamples.panAmFlights)  =>  0
    //     travelTime ("LGA", "PDX", FlightExamples.deltaFlights)  =>  482
    // Strategy: Divide on cases and combine simpler functions.
    public static int travelTime(String ap1,
                                 String ap2,
                                 ArrayList<Flight> flights) {

        if (flights.isEmpty()) {
            return 0;
        }
        else {
//            ArrayList<Flight> arrLst
//                    = RacketListHelper.
//                    racketListToArrayList(fastestItinerary(ap1, ap2,
//                            flights), new ArrayList());
            ArrayList<Flight> fastestFlights = fastestItinerary(ap1, ap2, flights);
            return TravelTime.totalTravelTime(fastestFlights);
        }
    }

    // Main Method for Testing
//    public static void main (String args[]) {
//
//        SchedulesTests.main(args);
//        TravelTimeTests.main(args);
//    }
}
