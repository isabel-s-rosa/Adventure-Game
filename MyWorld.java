import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import javax.swing.JOptionPane; 

/**
 * This is the class that sets everything up.
 * It creates the world and gives an introduction.
 * Throughout the game, it sets the variable gotPicked to false at the start of every round,
 * signifying that nothing has yet been picked up this round.
 * It also times the game and closes out after four minutes ("days").
 * 
 * @author Isabel Rosa
 */
public class MyWorld extends World implements Data{
    private long daysLeft = 4;
    private int startInt = 0;
    private ChangeableData cd;
    private Actor start;
    private Player p;
    private Player temp;
    private Key k;
    private long startTime;
    private GetKeyDown gkd;

    /**
     * Constructor for objects of class MyWorld.
     * Sets world size, brings certain objects to front, and sets order of being called.
     * Creates access to database, makes the start room, adds the player, creates access to which key is down, and prepares to start timer.
     */
    public MyWorld(){
        super(500, 400, 1);
        setPaintOrder(Black.class, Person.class, Desk.class, BagThings.class, Door.class, Pamphlet.class, Room.class);
        setActOrder(World.class, Key.class, Flashlight.class, Magnifier.class, Art.class, PlaceHolder.class);
        cd=new ChangeableData();
        start=new Room(0, this, cd);
        p=new Player(start, this, cd, "you");
        gkd=new GetKeyDown(this);
        startTime=-1;
    }
    
    /**
     * Displays the starting instructions.
     */
    private void startMessage(){
        if(startInt==0){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            JOptionPane.showMessageDialog(null, "You are the well-renowned art dealer, I.\nPress the arrow keys to move yourself, the person in the middle of the room.\nRun over a person to hear what they have to say.\nYou have four days to pay off your mortgage debt of $10,000,100, or else you lose your house.\nI haven't taken my econ class yet so I have a poor grasp of how mortgages work and also how to spell mortgage but I saw a movie with a plot along these lines so I turned it into a game.\nGood luck!");
            startInt=1;
        }
    }

    /**
     * Act - do whatever the Room wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * Displays start message when the game starts and sets gotPicked to false to signify nothing has yet been picked up this round.
     * Times the game and shuts down if player doesn't finish in time.
     */
    public void act(){
        startMessage();
        BagThings.gotPicked=false;
        if(startTime==-1)
            startTime=System.currentTimeMillis();
        else if((System.currentTimeMillis()-startTime)%120000<15){
            daysLeft--;
            if(daysLeft>0)
                System.out.println("A day has passed. You have " + daysLeft + " days left.");
            else{
                System.out.println("You ran out of days. Game over.");
                System.exit(0);
            }
        }
    }
}
