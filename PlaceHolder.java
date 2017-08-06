import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Since picking an object removes it from the world, I need a placeholder that can never be picked.
 * It stays in the world the whole time. Its purpose is for dropping items.
 * I can't have the item that was picked contain the method to drop itself bc once its picked, it stops existing.
 * In short, it inherits the beDropped() method from BagThings and calls it every method in case something gets dropped.
 * 
 * @author Isabel Rosa
 */
public class PlaceHolder extends BagThings{
    private World w;
    private ChangeableData cd;
    protected static boolean drop=false;
    protected static BagThings bt=null;

    /**
     * Constructor for PlaceHolder class.
     * Provides access to world and database and makes item invisible.
     */
    public PlaceHolder(World w, ChangeableData cd){
        super(w, cd);
        this.w=w;
        this.cd=cd;
        GreenfootImage image = getImage();
        image.setTransparency(0);
        w.addObject(this, 600, 500);
    }

    /**
     * Act - do whatever the PlaceHolder wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * When something is dropped, the static variable drop becomes true and the static variable bt is set to the item that was dropped.
     * The item is added back into the world and its drop message is called. The variables are set back to false and null, respectively.
     */
    public void act(){
        beDropped();
        if(drop){
            bt.droppedMsg();
            drop=false;
            bt=null;
        }
    } 
}