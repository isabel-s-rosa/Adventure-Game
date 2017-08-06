import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the class for the doors. They basically do nothing besides lock or be unlocked.
 * They also contain variables for the room they're in and the room they connect to.
 * 
 * @author Isabel Rosa
 */
public class Door extends Actor implements Data{
    private String nextRoom;
    private int id;
    private ChangeableData cd;
    private int currentRoom;

    /**
     * Constructor for Door class.
     * Scales image, accesses database, has the number of the room it's in, a specific ID number, and the name of the next room.
     * My method of naming rooms (int vs. String) seems haphazard but it was good practice to change it up.
     */
    protected Door(ChangeableData cd, int currentRoom, int id, String nextRoom){
        GreenfootImage image = getImage();
        image.scale(75, 20);
        setImage(image);
        this.cd=cd;
        this.currentRoom=currentRoom;
        this.id=id;
        this.nextRoom=nextRoom;
    }

    /**
     * Returns the name of the connecting room.
     */
    protected String getRoom(){
        return nextRoom;
    }

    /**
     * Returns the ID number of the door.
     */
    protected int getId(){
        return id;
    }

    /**
     * If the player has a key and is standing in front of a locked door and pressing "u", it unlocks.
     * Otherwise, if the player is trying to unlock a door but doesn't have the key, it displays an error message.
     */
    protected void beUnlocked(){
        if("u".equals(GetKeyDown.key) && cd.getDoorLocked(currentRoom, id) && getObjectsInRange(50, Player.class).size()>0){
            for(int i=0; i<cd.bag.size(); i++){
                if(cd.bag.get(i) instanceof Key){
                    cd.setDoorLocked(currentRoom, id, false);
                    System.out.println("You have unlocked the door.");
                }
            }
            if(cd.getDoorLocked(currentRoom, id))
                System.out.println("You have to have a key in your bag to unlock a door.");
        }
    }

    /**
     * Act - do whatever the Door wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * All doors do are get unlocked if necessary; otherwise, they do nothing.
     */
    public void act(){
        beUnlocked();
    }
}