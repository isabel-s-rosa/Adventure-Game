import java.util.ArrayList;

/**
 * This is my database for information that will never change.
 * Things like room names, doors, and relative locations are stored here.
 * Coordinates for objects that are locked in place also stay here.
 * Rooms are also dark or not dark permanently, so that, too, is stored here.
 * 
 * @author Isabel Rosa
 */
public interface Data{
    String[] roomIds = new String[] {
            "start",
            "museum",
            "bedroom",
            "park",
            "safe",
            "lab",
            "bank"};
    boolean[][] doorsBooleans = new boolean[][] {
            new boolean[] {true, true, true, true},
            new boolean[] {false, false, true, false},
            new boolean[] {false, false, false, true},
            new boolean[] {true, false, true, true},
            new boolean[] {false, true, true, false},
            new boolean[] {true, false, false, false},
            new boolean[] {true, true, false, false}};
    String[][] surroundingRooms = new String[][] {
            new String[] {"museum", "bedroom", "park", "safe"},
            new String[] {"","", "start", ""},
            new String[] {"", "", "", "start"},
            new String[] {"start", "", "lab", "bank"},
            new String[] {"", "start", "bank", ""},
            new String[] {"park", "", "", ""},
            new String[] {"safe", "park", "", ""}};
    int[] desks = new int[] {
            100, 100,
            -1, -1,
            -1, -1,
            -1, -1,
            100, 300,
            200, 250,
            200, 250};
    int[] pamphlet = new int[] {
            100, 100};
    int[] stationaryRoom = new int[] {
            -1, -1,
            350, 200,
            50, 300,
            -1, -1,
            -1, -1,
            -1, -1,
            200, 275};
    boolean[] darkRoom = new boolean[] {
            false,
            false,
            false,
            true,
            true,
            false,
            false};
}