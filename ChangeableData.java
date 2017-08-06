import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * This is my database class for data that can be changed.
 * This class keeps track of locations of items that can be picked up.
 * It also locates characters that can move.
 * The player's "bag" is also modified in this class.
 * 
 * @author Isabel Rosa
 */
public class ChangeableData extends Actor{
    protected ArrayList<BagThings> bag = new ArrayList<>();
    private int[] keysXY;
    private boolean[] keysBoolean;
    private int[] artXY;
    private boolean[] artBoolean;
    private int[] magnifierXY;
    private boolean[] magnifierBoolean;
    private int[] realArtXY;
    private boolean[] realArtBoolean;
    private int[] flashlightXY;
    private boolean[] flashlightBoolean;
    private boolean[][] doorsLocked;
    private int[][] sRoom;
    private int[][] aRoom;
    private int[][] hRoom;
    private int[][] cRoom;

    /**
     * Constructor for ChangeableData class.
     * Initializes all the variables above (except bag, which is added to during the game).
     * These are the starting positions of everything.
     * 
     * I use a lot of different styles of keeping tracks of things here.
     * It may seem haphazard, but I wanted to be flexible and creative.
     */
    protected ChangeableData(){
        keysXY = new int[] {
            100, 100,
            -1, -1,
            -1, -1,
            -1, -1,
            -1, -1,
            -1, -1,
            -1, -1};
        keysBoolean = new boolean[] {
            true,
            false,
            false,
            false,
            false,
            false,
            false};
        artXY = new int[] {
            400, 300,
            -1, -1,
            400, 100,
            -1, -1,
            -1, -1,
            -1, -1,
            -1, -1};
        artBoolean = new boolean[] {
            true,
            false,
            true,
            false,
            false,
            false,
            false};
        magnifierXY = new int[] {
            -1, -1,
            -1, -1,
            300, 100,
            -1, -1,
            -1, -1,
            -1, -1,
            -1, -1};
        magnifierBoolean = new boolean[] {
            false,
            false,
            true,
            false,
            false,
            false,
            false};
        realArtXY = new int[] {
            -1 ,-1,
            -1, -1,
            -1, -1,
            -1, -1,
            350, 20,
            -1, -1,
            -1, -1};
        realArtBoolean = new boolean[] {
            false,
            false,
            false,
            false,
            true,
            false,
            false};
        flashlightXY = new int[] {
            400, 75,
            -1, -1,
            -1, -1,
            -1, -1,
            -1, -1,
            -1, -1,
            -1, -1};
        flashlightBoolean = new boolean[]{
            true,
            false,
            false,
            false,
            false,
            false,
            false};
        doorsLocked = new boolean[][] {
            new boolean[] {false, false, false, true},
            new boolean[] {false, false, false, false},
            new boolean[] {false, false, false, false},
            new boolean[] {false, false, true, false},
            new boolean[] {false, true, true, false},
            new boolean[] {true, false, false, false},
            new boolean[] {true, false, false, false}};
        sRoom = new int[][] {
            new int[] {1, 50, 100},
            new int[] {0, 200, 100},
            new int[] {0, 200, 250},
            new int[] {0, 300, 100},
            new int[] {0, 100, 200},
            new int[] {0, 300, 50},
            new int[] {0, 30, 100}};
        aRoom = new int[][] {
            new int[] {0, 200, 100},
            new int[] {0, 50, 50},
            new int[] {0, 350, 350},
            new int[] {0, 150, 250},
            new int[] {1, 250, 200},
            new int[] {0, 200, 100},
            new int[] {0, 350, 350}};
        hRoom = new int[][] {
            new int[] {0, 60, 140},
            new int[] {0, 100, 200},
            new int[] {0, 350, 200},
            new int[] {0, 100, 300},
            new int[] {0, 20, 50},
            new int[] {1, 250, 200},
            new int[] {0, 200, 100}};
        cRoom = new int[][] {
            new int[] {0, 50, 50},
            new int[] {1, 50, 50},
            new int[] {0, 100, 300},
            new int[] {0, 400, 200},
            new int[] {0, 300, 300},
            new int[] {0, 275, 200},
            new int[] {0, 100, 100}
        };
    }

    /**
     * This returns a coordinate of an object, specified by the parameters.
     */
    protected int getSpecificCoordinate(int ind, String str){
        if(str.equals("art"))
            return artXY[ind];
        else if(str.equals("key"))
            return keysXY[ind];
        else if(str.equals("magnifier"))
            return magnifierXY[ind];
        else if(str.equals("real art"))
            return realArtXY[ind];
        else if(str.equals("flashlight"))
            return flashlightXY[ind];
        else if(str.equals("S"))
            return sRoom[ind/3][ind%3];
        else if(str.equals("A"))
            return aRoom[ind/3][ind%3];
        else if(str.equals("H"))
            return hRoom[ind/3][ind%3];
        else if(str.equals("C"))
            return cRoom[ind/3][ind%3];
        return -1;
    }

    /**
     * This modifies a spot in the coordinate array of a specific object/person.
     * The spot and the object/person and the value it's being set to are all parameters.
     */
    protected void changeCoordinateArray(int ind, int val, String str){
        if(str.equals("art"))
            artXY[ind]=val;
        else if(str.equals("key"))
            keysXY[ind]=val;
        else if(str.equals("magnifier"))
            magnifierXY[ind]=val;
        else if(str.equals("real art"))
            realArtXY[ind]=val;
        else if(str.equals("flashlight"))
            flashlightXY[ind]=val;
        else if(str.equals("S"))
            sRoom[ind/3][ind%3]=val;
        else if(str.equals("A"))
            aRoom[ind/3][ind%3]=val;
        else if(str.equals("H"))
            hRoom[ind/3][ind%3]=val;
        else if(str.equals("C"))
            cRoom[ind/3][ind%3]=val;
    }

    /**
     * This changes the value in a boolean array, specified by parameters.
     */
    protected void changeBooleanArray(int ind, boolean val, String str){
        if(str.equals("art"))
            artBoolean[ind]=val;
        else if(str.equals("key"))
            keysBoolean[ind]=val;
        else if(str.equals("magnifier"))
            magnifierBoolean[ind]=val;
        else if(str.equals("real art"))
            realArtBoolean[ind]=val;
        else if(str.equals("flashlight"))
            flashlightBoolean[ind]=val;
    }

    /**
     * This returns true if an object/person is in a room, false if it is not.
     */
    protected boolean getSpecificBoolean(int ind, String str){
        if(str.equals("art"))
            return artBoolean[ind];
        else if(str.equals("key"))
            return keysBoolean[ind];
        else if(str.equals("magnifier"))
            return magnifierBoolean[ind];
        else if(str.equals("real art"))
            return realArtBoolean[ind];
        else if(str.equals("flashlight"))
            return flashlightBoolean[ind];
        else if(str.equals("S"))
            return sRoom[ind][0]==1;
        else if(str.equals("A"))
            return aRoom[ind][0]==1;
        else if(str.equals("H"))
            return hRoom[ind][0]==1;
        else if(str.equals("C"))
            return cRoom[ind][0]==1;
        return false;
    }
    
    /**
     * This returns true if the door in question is locked.
     */
    protected boolean getDoorLocked(int room, int door){
        return doorsLocked[room][door];
    }
    
    /**
     * This sets a specific door to be either locked or unlocked.
     */
    protected void setDoorLocked(int room, int door, boolean val){
        doorsLocked[room][door]=val;
    }
}