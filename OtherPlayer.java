import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * These are other characters that move but are not controlled by the player.
 * They all contain important information. They, along with the StationaryPerson characters, tell the player all the different command keys.
 * 
 * @author Isabel Rosa
 */
public class OtherPlayer extends Person implements Data{
    private World w;
    private Actor currentRoom;
    private static ChangeableData cd;
    private String name;
    private String message;
    private String relation;
    private int i=0;

    /**
     * Constructor for OtherPlayer class.
     * Provides access to room they are in, world, and database.
     * Assigns a name, relationship to player, and message.
     * Scales the image.
     */
    public OtherPlayer(Actor currentRoom, World w, ChangeableData cd, String name, String relation, String message){
        super(currentRoom, w, cd, name);
        this.w=w;
        this.currentRoom=currentRoom;
        this.cd=cd;
        this.name=name;
        this.relation=relation;
        this.message=message;
        GreenfootImage image = getImage();
        image.scale(70, 50);
        setImage(image);
    }

    /**
     * Prints out name, relation, and message when they ought to speak.
     */
    public void speak(){
        System.out.println("This is " + name + ", " + relation + ". They have the message, \"" + message + "\"");
    }

    /**
     * When they hit a locked door, they turn 180 degrees and move forwards, away from the door.
     */
    public void lockedDoor(){
        turn(180);
        move(5);
    }   

    /**
     * When these people move from room to room, the number of the next room is found and the database is updated accordingly.
     * Then, the person is removed from the world.
     */
    public void moveRoom(){
        Actor door = getDoor();
        String nextRoom = ((Door)door).getRoom();
        int nextRoomNum = 0;
        for(; nextRoomNum<roomIds.length; nextRoomNum++){
            if(roomIds[nextRoomNum].equals(nextRoom))
                break;
        }
        int currentRoomNum = ((Room)currentRoom).getNum();
        cd.changeCoordinateArray(currentRoomNum*3, 0, name);
        cd.changeCoordinateArray(nextRoomNum*3, 1, name);
        w.removeObject(this);
    }

    /**
     * This is how these characters move.
     * They pause every once in awhile, but I didn't want all the characters to creepily pause at the same time, so I have them do it in proportion to the length of their message to make it appear more random.
     */
    public void movePerson(){
        if(isAtEdge()){
            move(-3);
            turn(100);
        }
        if(i%(message.length()*3)<message.length()*2){
            if(i%20==0)
                turn((int)((0.5-Math.random())*90));
            move(3);
        }
        i++;
    }
}