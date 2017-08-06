import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class doesn't do anything, it just has a picture of a desk to be displayed where I want it.
 * 
 * @author Isabel Rosa
 */
public class Desk extends Actor{
    /**
     * Constructor for Desk class.
     * Scales image.
     */
    public Desk(){
        GreenfootImage image = getImage();
        image.scale(60, 90);
        setImage(image);
    }
}