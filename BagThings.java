import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;

/**
 * This is an abstract class that mostly houses generic methods that might be used by any item that can be put in the player's bag.
 * It sets up the code for an item to be picked up or dropped, which is personalized and performed by subclasses.
 * 
 * @author Isabel Rosa
 */
public abstract class BagThings extends Actor implements Data{
    private World w;
    private int weight;
    private BagThings dropped;
    private ChangeableData cd;
    private int repeat=0;
    private final int MAX_BAG_WEIGHT=40;
    protected static boolean gotPicked=false;

    /**
     * Constructor for BagThings class.
     * Gets access to world and database.
     */
    public BagThings(World w, ChangeableData cd){
        this.w=w;
        this.cd=cd;
    }
    
    /**
     * Act - do whatever the Key wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * Any item that can be picked up uses this act.
     * Picks up the item if possible.
     * If the item is picked up, it records that a pick has occured (gotPicked becomes true).
     */
    public void act(){
        boolean p=bePicked();
        if(p)
            BagThings.gotPicked=p;
    }

    /**
     * Each item has a weight, and this returns it.
     */
    public int getWeight(){
        return weight;
    }

    /**
     * Each item has a value, so this method is to allow for polymorphism.
     */
    public int getValue(){
        return -1;
    }

    /**
     * If the player is pressing the "p" key and is on top of an item that can be picked, it gets added to bag and removed from world.
     * Otherwise, if they're pressing "p" but there's nothing to pick up or the bag is too heavy, it prints out the error.
     * Returns true if something got picked up, false if not.
     */
    public boolean bePicked(){
        if("p".equals(GetKeyDown.key) && isTouching(Player.class) && !(this instanceof PlaceHolder)){
            if((Person.getBagWeight() + getWeight())<=MAX_BAG_WEIGHT){
                cd.bag.add(this);
                w.removeObject(this);
                return true;
            }else
                System.out.println("Max bag weight is " + MAX_BAG_WEIGHT + ", so you can't pick up this object.");
            return false;
        }else if("p".equals(GetKeyDown.key) && this instanceof PlaceHolder && !gotPicked)
            System.out.println("Nothing to pick.");
        return false;
    }

    /**
     * When the player presses the "d" key, a drop down menu of things to drop appears.
     * The item they pick is added to the world, and various variables are modified to record what has been dropped.
     * If there's nothing to drop, it says so.
     */
    public BagThings beDropped(){
        if(Greenfoot.isKeyDown("d")){
            if(cd.bag.size()>0){
                String choice = choice();
                if(choice!=null){
                    addToWorld(choice);
                    PlaceHolder.drop=true;
                    PlaceHolder.bt=dropped;
                    return dropped;
                }
            }else if(repeat==0){
                System.out.println("Nothing to drop.");
                repeat=1;
            }
        }else if(repeat==1)
            repeat=0;
        return null;
    }

    /**
     * This gets the item the player wants to drop and returns it in String form.
     */
    public String choice(){
        int size = cd.bag.size();
        String[] choices = new String[size];
        for(int i=0; i<choices.length; i++)
            choices[i] = cd.bag.get(i).toString();
        String input = (String) JOptionPane.showInputDialog(null, "These are the items currently in your bag. Pick one to drop.",
                "Drop", JOptionPane.QUESTION_MESSAGE, null,
                choices,
                choices[0]);
        return input;
    }

    /**
     * This takes an item in String form, finds it in the bag (in Object form) and adds it to the world.
     */
    public void addToWorld(String input){
        for(int i=0; i<cd.bag.size(); i++){
            if(cd.bag.get(i).toString().equals(input)){
                dropped=cd.bag.remove(i);
                w.addObject(dropped, (w.getObjects(Player.class)).get(0).getX(), (w.getObjects(Player.class)).get(0).getY());
            }
        }
    }

    /**
     * This is for polymorphism purposes. Whatever item is dropped has its own message.
     */
    public void droppedMsg(){}

    /**
     * When polymorphism occurs and an item does its droppedMsg() method, it calls super to do this generic variable change.
     * The String form of the item is used to change variables and record where the item now rests.
     */
    public void droppedMsg(String str){
        int roomNum = w.getObjects(Room.class).get(0).getNum();
        cd.changeBooleanArray(roomNum, true, str);
        cd.changeCoordinateArray(2*roomNum, (w.getObjects(Player.class)).get(0).getX(), str);
        cd.changeCoordinateArray(2*roomNum+1, (w.getObjects(Player.class)).get(0).getY(), str);
    }
}