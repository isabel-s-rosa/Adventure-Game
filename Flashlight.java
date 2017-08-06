import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The flashlight is another object that can be picked up. Its presence in the player's bag is necessary to use it.
 * 
 * @author Isabel Rosa
 */
public class Flashlight extends BagThings{
    private World w;
    private ChangeableData cd;
    private final int VALUE = 10;
    private final int WEIGHT = 7;

    /**
     * Constructor for Flashlight class.
     * Provides access to world and databse and scales image.
     */
    public Flashlight(World w, ChangeableData cd){
        super(w, cd);
        this.w=w;
        this.cd=cd;
        GreenfootImage image = getImage();
        image.scale(25, 25);
        setImage(image);
    }
    
    /**
     * Returns the value of a flashlight.
     */
    public int getValue(){
        return VALUE;
    }

    /**
     * Returns the String name of this item.
     */
    public String toString(){
        return "flashlight";
    }

    /**
     * Returns the weight of the item.
     */
    public int getWeight(){
        return WEIGHT;
    }

    /**
     * To pick an item, the generic superclass method is called first.
     * If the superclass method returns true (the item was indeed picked up), then the specific boolean array housing flashlight info is changed.
     * A message is printed, and the method returns true if the item was picked, otherwise false.
     */
    public boolean bePicked(){
        boolean pick = super.bePicked();
        if(pick){
            int roomNum = w.getObjects(Room.class).get(0).getNum();
            cd.changeBooleanArray(roomNum, false, "flashlight");
            System.out.println("You picked up a flashlight. The weight of your bag is now " + Player.getBagWeight());
        }
        return pick;
    }

    /**
     * This method changes the boolean/coordinate arrays in the database by calling the superclass method.
     * Then, it prints out a message.
     */
    public void droppedMsg(){
        super.droppedMsg("flashlight");
        System.out.println("You dropped a flashlight. They weight of your bag is now " + Player.getBagWeight());
    } 
}