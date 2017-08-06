import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is used to light up dark rooms.
 * 
 * @author Isabel Rosa
 */
public class LightsWay extends Lighters{
    /**
     * Act - do whatever the LightsWay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * This turns off the opacity of any black square it may be standing on.
     * It gives the appearance of lighting a path.
     */
    public void act(){
        Actor b = getOneIntersectingObject(Black.class);
        if(b!=null)
            ((Black)b).off();
    }
}