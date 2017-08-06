import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * This class is to prevent annoying repeat messages.
 * This way, when the player hits a button, it only registers once, instead of around 20 times.
 * If I don't use this, it re-registers the key being down every act cycle.
 * Since act cycles are much faster than it takes a human to let go of a key, commands repeated a lot.
 * However, the getKey() method only works once per act cycle: otherwise it returns null.
 * Due to this, I had to call it only once per act cycle and then store it in a variable everyone can see.
 * This stopped the repeats and made it easy to access the key being pressed.
 * 
 * @author Isabel Rosa
 */
public class GetKeyDown extends Actor{
    protected static String key;

    /**
     * Constructor for GetKeyDown class.
     * This makes it invisible so the player can't see it.
     */
    protected GetKeyDown(World w){
        GreenfootImage image = getImage();
        image.setTransparency(0);
        w.addObject(this, 600, 500);
    }

    /**
     * Act - do whatever the GetKey wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * Gets the key being pressed.
     */
    public void act(){
        key=Greenfoot.getKey();
    }
}