import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Another item that can be picked. Its presence in the bad is necessary for unlocking doors.
 * 
 * @author Isabel Rosa
 */
public class Key extends BagThings{
    private World w;
    private ChangeableData cd;
    private final int VALUE = 75;
    private final int WEIGHT = 5;

    /**
     * Constructor for Key class.
     * Provides access to world and database and scales the image.
     */
    public Key(World w, ChangeableData cd){
        super(w, cd);
        this.w=w;
        this.cd=cd;
        GreenfootImage image = getImage();
        image.scale(30, 15);
        setImage(image);
    }

    /**
     * Returns the value of the key.
     */
    public int getValue(){
        return VALUE;
    }

    /**
     * Returns the String name of the key.
     */
    public String toString(){
        return "key";
    }

    /**
     * Returns the weight of the key.
     */
    public int getWeight(){
        return WEIGHT;
    }

    /**
     * To pick an item, the generic superclass method is called first.
     * If the superclass method returns true (the item was indeed picked up), then the specific boolean array housing key info is changed.
     * A message is printed, and the method returns true if the item was picked, otherwise false.
     */
    public boolean bePicked(){
        boolean pick = super.bePicked();
        if(pick){
            int roomNum = w.getObjects(Room.class).get(0).getNum();
            cd.changeBooleanArray(roomNum, false, "key");
            System.out.println("You picked up a key. The weight of your bag is now " + Player.getBagWeight());
        }
        return pick;
    }

    /**
     * This method changes the boolean/coordinate arrays in the database by calling the superclass method.
     * Then, it prints out a message.
     */
    public void droppedMsg(){
        super.droppedMsg("key");
        System.out.println("You dropped a key. They weight of your bag is now " + Player.getBagWeight());
    }
}