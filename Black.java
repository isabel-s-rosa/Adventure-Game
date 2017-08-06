import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is for big black pixels of sorts that cover a room that is labeled dark.
 * They can be turned off by becoming translucent, or on by becoming completely opaque.
 * 
 * @author Isabel Rosa
 */
public class Black extends Actor{
    private boolean off=false;
    private int transparency;
    
    /**
     * Constructor for Black class.
     * Scales the image and initializes transparency variable.
     */
    protected Black(){
        GreenfootImage image = getImage();
        image.scale(image.getWidth() - 300, image.getHeight() - 300);
        setImage(image);
        transparency = image.getTransparency();
    }
    
    /**
     * Not completely necessary, but it's always nice to have a toString method just in case.
     */
    public String toString(){
        return "black";
    }
    
    /**
     * Makes the square translucent, appearing to give light to the room.
     */
    protected void off(){
        GreenfootImage gi = getImage();
        gi.setTransparency(0);
        off=true;
        transparency = gi.getTransparency();
    }
    
    /**
     * Makes the square opaque, appearing to darken the room.
     */
    protected void on(){
        GreenfootImage gi = getImage();
        gi.setTransparency(255);
        off=false;
        transparency = gi.getTransparency();
    }
    
    /**
     * Returns true if the square is opaque (dark) and false if it's translucent (light).
     */
    protected boolean isOn(){
        return !off;
    }
}