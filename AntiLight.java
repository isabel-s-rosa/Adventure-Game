import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is used to redarken a dark room after the LightsWay class lightens it.
 * 
 * @author Isabel Rosa
 */
public class AntiLight extends Lighters{
    /**
     * Act - do whatever the LightsWay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * This turns up the opacity of a dark square, making it dark again.
     */
    public void act(){
        Actor b = getOneIntersectingObject(Black.class);
        if(b!=null)
            ((Black)b).on();
    }  
}