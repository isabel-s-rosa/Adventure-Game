import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class contains the methods for pieces of art. Basically they get picked up when the player decides.
 * 
 * @author Isabel Rosa
 */
public class Art extends BagThings{
    private World w;
    private ChangeableData cd;
    private final int VALUE = 50;
    private final int WEIGHT = 15;
    
    /**
     * Constructor for Art class.
     * Provides access for world and database as well as scales the image.
     */
    public Art(World w, ChangeableData cd){
        super(w, cd);
        this.w=w;
        this.cd=cd;
        GreenfootImage image = getImage();
        image.scale(70, 50);
        setImage(image);
    }
    
    /**
     * Returns the value of the art.
     */
    public int getValue(){
        return VALUE;
    }
    
    /**
     * Returns the name of the object.
     */
    public String toString(){
        return "piece of art";
    }
    
    /**
     * Returns the weight.
     */
    public int getWeight(){
        return WEIGHT;
    }
    
    /**
     * To pick an item, the generic superclass method is called first.
     * If the superclass method returns true (the item was indeed picked up), then the specific boolean array housing art info is changed.
     * A message is printed, and the method returns true if the item was picked, otherwise false.
     */
    public boolean bePicked(){
        boolean pick = super.bePicked();
        if(pick){
            int roomNum = w.getObjects(Room.class).get(0).getNum();
            cd.changeBooleanArray(roomNum, false, "art");
            System.out.println("You picked up a piece of art. The weight of your bag is now " + Player.getBagWeight());
        }
        return pick;
    }
    
    /**
     * This method changes the boolean/coordinate arrays in the database by calling the superclass method.
     * Then, it prints out a message.
     */
    public void droppedMsg(){
        super.droppedMsg("art");
        System.out.println("You dropped a piece of art. They weight of your bag is now " + Player.getBagWeight());
    }
}