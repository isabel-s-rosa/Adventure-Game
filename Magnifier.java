import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is another item that can be picked up. Its presence in the bag/room is necessary to inspect a piece of art.
 * 
 * @author Isabel Rosa
 */
public class Magnifier extends BagThings{
    private World w;
    private ChangeableData cd;
    private final int WEIGHTVALUE = 10;

    /**
     * Constructor for Magnifier class.
     * Provides access to world and database and scales the image.
     */
    public Magnifier(World w, ChangeableData cd){
        super(w, cd);
        this.w=w;
        this.cd=cd;
        GreenfootImage image = getImage();
        image.scale(27, 27);
        setImage(image);
    }
    
    /**
     * Returns the value (same as the weight, which is why I have a combined variable).
     */
    public int getValue(){
        return WEIGHTVALUE;
    }
    
    /**
     * Returns the object in String form.
     */
    public String toString(){
        return "magnifying glass";
    }
    
    /**
     * Returns the weight.
     */
    public int getWeight(){
        return WEIGHTVALUE;
    }
    
    /**
     * To pick an item, the generic superclass method is called first.
     * If the superclass method returns true (the item was indeed picked up), then the specific boolean array housing magnifier info is changed.
     * A message is printed, and the method returns true if the item was picked, otherwise false.
     */
    public boolean bePicked(){
        boolean pick = super.bePicked();
        if(pick){
            int roomNum = w.getObjects(Room.class).get(0).getNum();
            cd.changeBooleanArray(roomNum, false, "magnifier");
            System.out.println("You picked up a magnifying glass. The weight of your bag is now " + Player.getBagWeight());
        }
        return pick;
    }
    
    /**
     * This method changes the boolean/coordinate arrays in the database by calling the superclass method.
     * Then, it prints out a message.
     */
    public void droppedMsg(){
        super.droppedMsg("magnifier");
        System.out.println("You dropped a magnifying glass. They weight of your bag is now " + Player.getBagWeight());
    }
}