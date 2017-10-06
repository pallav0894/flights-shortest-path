import sun.nio.ch.FileKey;

import java.util.ArrayList;


// Defines some examples for testing and makes them available as
// public members of the class.


public class FlightSamples {

    public static ArrayList<Flight> emptyList = new ArrayList<Flight>();

    public static ArrayList<Flight> deltaFlights = initDeltaFlights();

    public static ArrayList<Flight> deltaCycle = initDeltaCycle();

    // GIVEN: the name of a flight, the airports of departure and
    //     arrival, and the departure and arrival times represented
    //     as integers using the encoding of Problem Set 00,
    // RETURNS: a flight with the given name, airports, and times

    private static Flight flt (String s, String ap1, String ap2,
                               int t1, int t2) {
        UTC lv = UTCFactory.make (t1 / 100, t1 % 100);
        UTC ar = UTCFactory.make (t2 / 100, t2 % 100);
        return FlightFactory.make (s, ap1, ap2, lv, ar);
    }


    // Returns a list of flights extracted from schedules published
    // by Delta Airlines.

    private static ArrayList<Flight> initDeltaFlights() {

        ArrayList<Flight> initDeltaFlights = new ArrayList<Flight>();
        initDeltaFlights.add(flt("Delta 0121", "LGA", "MSP", 1100, 1409));
        initDeltaFlights.add(flt ("Delta 2163", "MSP", "PDX", 1500, 1902));
        initDeltaFlights.add(flt("Delta 2079", "BOS", "DTW", 1035, 1259));
        initDeltaFlights.add(flt("Delta 1523", "BOS", "DTW", 2158,   20));
        initDeltaFlights.add(flt("Delta 0058", "BOS", "LHR",   44,  720));
        initDeltaFlights.add(flt("Delta 2531", "BOS", "LAX", 1317, 2020));
        initDeltaFlights.add(flt("Delta 2532", "BOS", "LAX", 2250,  555));
        initDeltaFlights.add(flt("Delta 1959", "BOS", "MSP", 1050, 1417));
        initDeltaFlights.add(flt("Delta 1894", "BOS", "MSP", 1355, 1730));
        initDeltaFlights.add(flt("Delta 2391", "BOS", "MSP", 2135,  105));
        initDeltaFlights.add(flt("Delta 2734", "BOS", "LGA", 1100, 1230));
        initDeltaFlights.add(flt("Delta 3550", "BZN", "LAX", 2020, 2302));
        initDeltaFlights.add(flt("Delta 1601", "DEN", "DTW", 1305, 1611));
        initDeltaFlights.add(flt("Delta 0916", "DEN", "DTW", 2332,  219));
        initDeltaFlights.add(flt("Delta 0010", "DEN", "LHR", 2030,  945));
        initDeltaFlights.add(flt("Delta 5703", "DEN", "LAX", 1404, 1715));
        initDeltaFlights.add(flt("Delta 5743", "DEN", "LAX",   34,  331));
        initDeltaFlights.add(flt("Delta 2437", "DTW", "BOS", 1345, 1546));
        initDeltaFlights.add(flt("Delta 0158", "DTW", "BOS", 1700, 1855));
        initDeltaFlights.add(flt("Delta 1700", "DTW", "BOS", 2240,   42));
        initDeltaFlights.add(flt("Delta 1511", "DTW", "DEN", 1330, 1651));
        initDeltaFlights.add(flt("Delta 1645", "DTW", "DEN", 1711, 2038));
        initDeltaFlights.add(flt("Delta 1706", "DTW", "LAX", 1320, 1845));
        initDeltaFlights.add(flt("Delta 0249", "DTW", "MSP", 1500, 1707));
        initDeltaFlights.add(flt("Delta 2359", "DTW", "MSP", 1715, 1920));
        initDeltaFlights.add(flt("Delta 2476", "DTW", "MSP",  110,  318));
        initDeltaFlights.add(flt("Delta 0059", "LHR", "BOS",  920, 1726));
        initDeltaFlights.add(flt("Delta 4378", "LHR", "BOS", 1645,   20));
        initDeltaFlights.add(flt("Delta 0011", "LHR", "DEN", 1255,  220));
        initDeltaFlights.add(flt("Delta 0302", "LAX", "BOS", 1625, 2214));
        initDeltaFlights.add(flt("Delta 5732", "LAX", "BZN",   30,  318));
        initDeltaFlights.add(flt("Delta 4574", "LAX", "DEN", 1735, 2007));
        initDeltaFlights.add(flt("Delta 5700", "LAX", "DEN",   10,  245));
        initDeltaFlights.add(flt("Delta 2077", "LAX", "PDX", 1735, 2009));
        initDeltaFlights.add(flt("Delta 1728", "MSP", "BOS", 1600, 1851));
        initDeltaFlights.add(flt("Delta 2305", "MSP", "BZN",  221,  513));
        initDeltaFlights.add(flt("Delta 1609", "MSP", "DEN", 2035, 2252));
        initDeltaFlights.add(flt("Delta 1836", "MSP", "DTW", 1224, 1415));
        initDeltaFlights.add(flt("Delta 1734", "MSP", "DTW", 1755, 1941));
        initDeltaFlights.add(flt("Delta 0592", "MSP", "LGA", 1730, 2017));
        initDeltaFlights.add(flt("Delta 2734", "LGA", "BOS", 1100, 1208));
        initDeltaFlights.add(flt("Delta 1294", "LGA", "DEN", 1310, 1754));
        initDeltaFlights.add(flt("Delta 0879", "LGA", "DTW", 1410, 1620));
        initDeltaFlights.add(flt("Delta 1422", "LGA", "MSP", 1500, 1822));
        initDeltaFlights.add(flt("Delta 0950", "PDX", "LAX", 1418, 1655));
        initDeltaFlights.add(flt("Delta 2077", "PDX", "LAX", 2045, 2314));
        initDeltaFlights.add(flt("Delta 2831", "PDX", "LAX", 2346,  225));
        initDeltaFlights.add(flt("Delta 2167", "PDX", "MSP", 2200,  120));

        return initDeltaFlights;
    }


    // Returns another list of flights extracted from schedules published
    // by Delta Airlines.

    private static ArrayList<Flight> initDeltaCycle() {

        ArrayList<Flight> initDeltaCycle = new ArrayList<Flight>();
        initDeltaCycle.add(flt("Delta 0105", "BOS", "ATL", 1950, 2259));
        initDeltaCycle.add(flt("Delta 1895", "ATL", "PHL", 1505, 1705));
        initDeltaCycle.add(flt("Delta 0926", "PHL", "SLC", 1059, 1615));
        initDeltaCycle.add(flt("Delta 5828", "SLC", "DFW", 1813, 2056));
        initDeltaCycle.add(flt("Delta 8122", "DFW", "MEX",  132,  435));
        initDeltaCycle.add(flt("Delta 8028", "MEX", "LAS", 1800, 2228));
        initDeltaCycle.add(flt("Delta 2837", "LAS", "MKC",  215,  505));
        initDeltaCycle.add(flt("Delta 3337", "MKC", "ORL", 2000, 2250));
        initDeltaCycle.add(flt("Delta 3617", "ORL", "BNA", 1735, 1936));
        initDeltaCycle.add(flt("Delta 4811", "BNA", "CVG", 1215, 1333));
        initDeltaCycle.add(flt("Delta 6207", "CVG", "IAH", 1850, 2131));
        initDeltaCycle.add(flt("Delta 0108", "IAH", "MAD", 2006,  715));
        initDeltaCycle.add(flt("Delta 6775", "MAD", "MIA", 1425, 2350));
        initDeltaCycle.add(flt("Delta 7199", "MIA", "YTO", 2055,    6));
        initDeltaCycle.add(flt("Delta 7037", "YTO", "BOS", 2215,    5));

        return initDeltaCycle;
    }

    public static Boolean listEquals(ArrayList<Flight> lst1, ArrayList<Flight> lst2){
        int n = lst1.size();
        int m = lst2.size();

        if (n != m) {
            return false;
        }

        int flag = 0;

        for (int i=0; i<n; i++){
            if (lst1.get(i).name().equals(lst2.get(i).name()) &&
                    lst1.get(i).departs().equals(lst2.get(i).departs()) &&
                    lst1.get(i).arrives().equals(lst2.get(i).arrives()) &&
                    lst1.get(i).departsAt().isEqual(lst2.get(i).departsAt()) &&
                    lst1.get(i).arrivesAt().isEqual(lst2.get(i).arrivesAt())){
                flag = 1;
            }
            else{
                flag = 0;
            }
        }
        if (flag == 1) {
            return true;
        }
        else return false;
    }


}