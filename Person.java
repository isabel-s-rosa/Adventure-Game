import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the superclass for all characters. It sets up a barebones act method with some generic steps to be filled in by polymorphism.
 * It also checks a few scenarios that I put here. I could've put them in other classes, but it was easy here and there's no reason to do it elsewhere.
 * 
 * @author Isabel Rosa
 */
public abstract class Person extends Actor{
    private World w;
    public Actor currentRoom;
    private static ChangeableData cd;
    private String name;
    private int repeat=0;
    private int debtLeft;

    /**
     * Constructor for Person class.
     * Provides access to the room the character is in, the world, and the database.
     * Assigns the character a name. Puts a number to the debt that has to be paid.
     */
    public Person(Actor currentRoom, World w, ChangeableData cd, String name){
        this.w=w;
        this.currentRoom=currentRoom;
        this.cd=cd;
        this.name=name;
        debtLeft=10000100;
    }

    /**
     * Returns the weight of the bag without having to call a specific person.
     */
    public static int getBagWeight(){
        int weight = 0;
        for(int i=0; i<cd.bag.size(); i++)
            weight+=cd.bag.get(i).getWeight();
        return weight;
    }

    /**
     * Checks conditions necessary to deposit money in the bank. Returns true if theyre all met, false otherwise.
     */
    public boolean goodToDeposit(){
        return (this instanceof Player) && ((Room)currentRoom).getNum()==6 && "g".equals(GetKeyDown.key) && ((Player)this).getMoney()>0;
    }

    /**
     * If the player is okay to deposit, the money gets deposited.
     * This method also checks to see if they player has won.
     */
    public void depositAndWin(){
        System.out.println("You have deposited " + ((Player)this).getMoney() + " dollars into your bank account. You now have 0 dollars with you.");
        debtLeft-=((Player)this).getMoney();
        if(debtLeft<=0){
            System.out.println("You won! You paid off your mortgage entirely. Contragtulations!");
            System.exit(0);
        }else{
            ((Player)this).setMoney(0);
            System.out.println("You have " + debtLeft + " dollars left to pay off before you win.");
        }
    }
    
    /**
     * Deposits money if possible, otherwise prints out an error message.
     */
    public void depositIfPossible(){
        if(goodToDeposit())
            depositAndWin();
        else if("g".equals(GetKeyDown.key) && this instanceof Player)
            System.out.println("You must be in the bank with money on you in order to deposit.");
    }
    
    /**
     * If someone is touching the main player, they speak.
     */
    public void speakIfPossible(){
        if(!(this instanceof Player) && isTouching(Player.class) && repeat==0){
            speak();
            repeat=1;
            movePerson();
        }
        if(!(this instanceof Player) && !isTouching(Player.class) && repeat==1)
            repeat=0;
    }

    /**
     * Act - do whatever the Person wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * This is the barebones act method.
     * Specific methods such as how to move or what to say or how to react when a door is locked or how to move from one room to another are in each type of person's class.
     * Basically depositing and speaking are dealt with, then moving through a door is dealt with, and then normal moving is dealt with.
     */
    public void act(){
        depositIfPossible();
        speakIfPossible();
        if(isTouching(Door.class)){
            int doorId = ((Door)getOneIntersectingObject(Door.class)).getId();
            int roomNum = ((Room)currentRoom).getNum();
            if(cd.getDoorLocked(roomNum, doorId))
                lockedDoor();
            else
                moveRoom();
        }else
            movePerson();
    }

    /**
     * Returns the door a person is standing at.
     */
    public Actor getDoor(){
        return getOneIntersectingObject(Door.class);
    }

    /**
     * Returns the person's name.
     */
    public String getName(){
        return name;
    }

    /**
     * Dictates what a person does when they come across a locked door.
     * Written in this class solely for polymorphism.
     */
    public void lockedDoor(){}

    /**
     * Dictates what a person does when they move from room to room.
     * Written in this class solely for polymorphism.
     */
    public void moveRoom(){}

    /**
     * Dictates how a person moves.
     * Written in this class solely for polymorphism.
     */
    public void movePerson(){}

    /**
     * Dictates what a person says.
     * Written in this class solely for polymorphism.
     */
    public void speak(){}
}