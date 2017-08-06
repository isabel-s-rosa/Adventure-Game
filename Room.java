import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the class where most of the objects/people are added to the world.
 * 
 * @author Isabel Rosa
 */
public class Room extends Actor implements Data{
    private int num;
    private Door[] doors=new Door[4];
    private ChangeableData cd;
    private PlaceHolder ph;
    private World world;
    private int counter=0;

    /**
     * Constructor for Room class.
     * Since there always has to be a room, I organize my data in terms of what goes in what room.
     * Along those lines, objects and people are added when a room is added because they're generally tied to a room.
     * The room number serves as its index for accessing parts of arrays and such.
     * Provides access to world and databse.
     * Scales the image.
     */
    public Room(int num, World world, ChangeableData cd){
        GreenfootImage image = getImage();
        image.scale(500, 400);
        setImage(image);
        world.addObject(this, 250, 200);
        this.num=num;
        this.cd=cd;
        this.world=world;
        placeDoors();
        if(desks[2*num]>0)
            world.addObject(new Desk(), desks[2*num], desks[2*num+1]);
        addMoveableObject(new Key(world, cd), "key");
        addMoveableObject(new Art(world, cd), "art");
        addMoveableObject(new Magnifier(world, cd), "magnifier");
        addMoveableObject(new RealArt(world, cd), "real art");
        addMoveableObject(new Flashlight(world, cd), "flashlight");
        ph=new PlaceHolder(world, cd);
        addOtherPlayer("S", "your spouse", "Don't waste time! We have to pay off our mortgage soon! Stand on top of an item and press \"p\" to pick it up. Press \"d\" to drop an item.");
        addOtherPlayer("A", "the secretive art dealer", "You hear I have the Goya? I don't believe it exists. That silly old art historian... is their name H? runs on about it all the time. Talk to them if you want to hear the legend.");
        addOtherPlayer("H", "your old professor of art history", "I know the Goya exists. I don't believe A. I think they're hiding something. If you find a piece, bring it and a magnifying glass to me here and press \"i\" to inspect it.");
        addOtherPlayer("C", "your spouse's art-collecting friend", "Trade by pressing \"t\". Bring me the real Goya and I'll trade. I value it at 10 million dollars.");
        if(num==1){
            world.addObject(new Pamphlet(), pamphlet[0], pamphlet[1]);
            world.addObject(new StationaryPerson(this, world, cd, "a museum greeter", "You don't have a membership so we can't let you into the Goya museum. Go to our pamphlet and press \"r\" to learn more about him!"), stationaryRoom[2*num], stationaryRoom[2*num+1]);
        }
        if(num==2)
            world.addObject(new StationaryPerson(this, world, cd, "your maid", "I thought you were gone so I locked up. I may have put the key in a drawer somewhere, but I don't quite remember. Press \"u\" to unlock a door and \"f\" to use your flashlight."), stationaryRoom[2*num], stationaryRoom[2*num+1]);
        if(num==6)
            world.addObject(new StationaryPerson(this, world, cd, "your banker", "Press \"g\" to give me the money to put in your account. This will pay off your mortgage."), stationaryRoom[2*num], stationaryRoom[2*num+1]);
        if(darkRoom[num]){
            for(int i=0; i<500; i++)
                world.addObject(new Black(), (i%25)*20+10, (i/25)*20+10);
        }
    }

    /**
     * A slightly faster way to add characters from OtherPlayer class to world.
     */
    private void addOtherPlayer(String name, String relation, String message){
        if(cd.getSpecificBoolean(num, name))
            world.addObject(new OtherPlayer(this, world, cd, name, relation, message), cd.getSpecificCoordinate(num*3+1, name), cd.getSpecificCoordinate(num*3+2, name));
    }

    /**
     * A slightly faster way to add items that can be picked up to the world.
     */
    private void addMoveableObject(BagThings bt, String str){
        if(cd.getSpecificBoolean(num, str))
            world.addObject(bt, cd.getSpecificCoordinate(2*num, str), cd.getSpecificCoordinate(2*num+1, str));
    }

    /**
     * This method places doors where they ought to be in this specific room.
     */
    private void placeDoors(){
        for(int i=0; i<4; i++){
            if(doorsBooleans[num][i]){
                doors[i] = new Door(cd, num, i, surroundingRooms[num][i]);
                if(i==0)
                    world.addObject(doors[i], 500, 200);
                else if(i==1)
                    world.addObject(doors[i], 250, 0);
                else if(i==2)
                    world.addObject(doors[i], 0, 200);
                else
                    world.addObject(doors[i], 250, 400);
                if(i%2==0)
                    doors[i].setRotation(90);
            }
        }
    }

    /**
     * Gets and returns the name of the room from the database.
     */
    public String toString(){
        return roomIds[num];
    }

    /**
     * Returns the room number.
     */
    public int getNum(){
        return num;
    }

    /**
     * I guess back when I was first programming this, I was in Good Programmer Mindset and actually wrote a .equals method.
     * I never use this in the rest of the program, but here it is!
     */
    public boolean equals(Object other){
        if(!(other instanceof Room || other instanceof String))
            return false;
        else{
            if(other instanceof Room){
                Room o = (Room)other;
                return this.toString().equals(o.toString());
            }else{
                String o = (String)other;
                return this.toString().equals(o);
            }
        }
    }
}