// ScheduleTests provides a main method that can be called to test methods
// canGetThere() and fastestItinerary() in class Schedules

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PathFinderTests {

    @Test
    public void checkCanGetThere(){
        // Tests for canGetThere

        // test same origin and destination
        Boolean t1 = PathFinder.
                canGetThere("BOS","BOS",FlightSamples.deltaCycle);

        // test for route in lists of flights with cycle
        Boolean t2 = PathFinder.
                canGetThere("BOS","ATL",FlightSamples.deltaCycle);

        // test for non-existent origin
        Boolean t3 = PathFinder.
                canGetThere("PDX","YTO",FlightSamples.deltaCycle);

        // test for non-existent destination
        Boolean t4 = PathFinder.
                canGetThere("MAD","PDX",FlightSamples.deltaCycle);

        // test for non-trivial itinerary in list without cycles
        Boolean t5 = PathFinder.
                canGetThere("LGA","DTW",FlightSamples.deltaCycle);

        assertEquals(t1, true);
        assertEquals(t2, true);
        assertEquals(t3, true);
        assertEquals(t4, true);
        assertEquals(t5, true);
    }

    @Test
    public void checkItinerary(){

        // Tests for fastestItinerary

        // test for same origin and destination
        ArrayList<Flight> itn1 = PathFinder.fastestItinerary("JFK", "JFK", FlightSamples.emptyList);

        ArrayList<Flight> itn1Result = FlightSamples.emptyList;

        // test for an fastest itinerary that is not nonstop
        ArrayList<Flight> itn2 = PathFinder.fastestItinerary("LGA", "PDX", FlightSamples.deltaFlights);
        System.out.println(itn2);

        ArrayList<Flight> itn2Result = new ArrayList<Flight>();

        itn2Result.add(FlightFactory.make ("Delta 2163","MSP", "PDX",UTCFactory.make (15, 0), UTCFactory.make (19, 2)));
        itn2Result.add(FlightFactory.make ("Delta 0121", "LGA", "MSP", UTCFactory.make (11, 0),
                        UTCFactory.make (14, 9)));
        System.out.println(itn2Result);

        // test for a fastest itinerary that is nonstop in a list that
        // has multiple paths and multiple non-stop paths
        ArrayList<Flight> itn3 = PathFinder.fastestItinerary("BOS", "MSP", FlightSamples.deltaFlights);

        ArrayList<Flight> itn3Result = new ArrayList<Flight>();
        itn3Result.add(FlightFactory.make ("Delta 1959","BOS", "MSP",UTCFactory.make (10, 50),UTCFactory.make (14, 17)));

        //test for itinerary in a list of flight that is a cycle
        ArrayList<Flight> itn4 = PathFinder.fastestItinerary("BOS", "ATL", FlightSamples.deltaCycle);
        ArrayList<Flight> itn4Result = new ArrayList<Flight>();
        itn4Result.add(FlightFactory.make ("Delta 0105","BOS", "ATL",UTCFactory.make (19, 50),UTCFactory.make (22, 59)));

        assert FlightSamples.listEquals(itn1, itn1Result);
        assert FlightSamples.listEquals(itn2, itn2Result);
        assert FlightSamples.listEquals(itn3, itn3Result);
        assert FlightSamples.listEquals(itn4, itn4Result);

    }
}
