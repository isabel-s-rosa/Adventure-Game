import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the class for the characters who never move. They stay in place and speak when they should.
 * 
 * @author Isabel Rosa
 */
public class StationaryPerson extends Person implements Data{
    private World w;
    public Actor currentRoom;
    private static ChangeableData cd;
    private String name;
    private String message;

    /**
     * Constructor for StationaryPerson class.
     * Provides access to the room they're in, the world, and the database.
     * Sets their name and their message and scales their image.
     */
    public StationaryPerson(Actor currentRoom, World w, ChangeableData cd, String name, String message){
        super(currentRoom, w, cd, name);
        this.w=w;
        this.currentRoom=currentRoom;
        this.cd=cd;
        this.name=name;
        this.message=message;
        GreenfootImage image = getImage();
        image.scale(70, 50);
        setImage(image);
    }

    /**
     * All stationary people do is print their message when the player runs over them.
     */
    public void speak(){
        System.out.println("This is " + name + ". They say, \"" + message + "\"");
    }
}