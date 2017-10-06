import java.util.ArrayList;

// Constructor template for PathTimeClass:
//     new PathTimeClass(x, y)
// Interpretation:
//     x is the path to a destination from a given source
//     y is the time elapsed for traversing the path in x

public class PathTimeClass implements PathTime {

    // Data Definition and Constructor
    ArrayList path;
    int time;

    PathTimeClass(ArrayList path, int time) {
        this.path = path;
        this.time = time;
    }

    // returns a ArrayList representing a path of Flight from the source
    //     to the particular destination
    public ArrayList path() {
        return this.path;
    }

    // returns the time for a given path, as described above
    public int time() {
        return this.time;
    }
}
