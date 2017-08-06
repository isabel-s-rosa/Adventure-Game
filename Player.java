import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import javax.swing.JOptionPane;

/**
 * This is the character that is controlled by the person playing the game.
 * This class contains methods to fill in for the ones the superclass is missing.
 * It also has some add-ons, like trading, inspecting art, and using a flashlight.
 * 
 * @author Isabel Rosa
 */
public class Player extends Person implements Data{
    private World w;
    private static ChangeableData cd;
    private String name;
    private int repeat=0;
    private boolean wasOn=false;
    private double pastMouseX;
    private double pastMouseY;
    private double lwX;
    private double lwY;
    private double pastThisX;
    private double pastThisY;
    private int money;

    /**
     * Constructor for Player class.
     * Provides access to current room, world, and database.
     * Gives character a name, scales the image, and adds the player to the world.
     */
    public Player(Actor currentRoom, World w, ChangeableData cd, String name){
        super(currentRoom, w, cd, name);
        this.w=w;
        this.currentRoom=currentRoom;
        this.cd=cd;
        this.name=name;
        GreenfootImage image = getImage();
        image.rotate(90);
        image.scale(50, 50);
        setImage(image);
        w.addObject(this, 250, 200);
        money=0;
    }

    /**
     * Returns money currently with the player (achieved from trading items).
     */
    public int getMoney(){
        return money;
    }

    /**
     * Sets the money currently with the player to a certain number.
     * Usually used to set money back to zero after depositing it in the bank.
     */
    public void setMoney(int i){
        money=i;
    }

    /**
     * When the player comes across a locked door, they get a message about it and bounce back a little bit.
     */
    public void lockedDoor(){
        System.out.println("Door is locked!");
        move(-5);
    }

    /**
     * This method removes everyone except the player, the placeholder (for dropping objects), and the class that gets the key being pressed.
     * This is for use when the player moves rooms.
     */
    public void removeAll(){
        List<Actor> a = w.getObjects(null);
        for(int i=0; i<a.size(); i++){
            if(!(a.get(i) instanceof Player) && !(a.get(i) instanceof PlaceHolder) && !(a.get(i) instanceof GetKeyDown))
                w.removeObject(a.get(i));
        }
        w.removeObject(currentRoom);
    }

    /**
     * This sets the player's location to right in front of the door they come out of when they move rooms.
     */
    public void setProperPlayerLocation(Actor door){
        if(((Door)door).getId()==0)
            setLocation(50, 200);
        else if(((Door)door).getId()==1)
            setLocation(250, 350);
        else if(((Door)door).getId()==2)
            setLocation(450, 200);
        else
            setLocation(250, 50);
    }

    /**
     * This is what happens when the player moves rooms.
     * It gets the name of the room the player is moving to and finds the number of that room.
     * Then, nearly everything gets removed from the world and a new room is created.
     */
    public void moveRoom(){
        Actor door = getDoor();
        String nextRoom = ((Door)door).getRoom();
        int index=0;
        for(; index<roomIds.length; index++){
            if(roomIds[index].equals(nextRoom))
                break;
        }
        removeAll();
        Actor newRoom = new Room(index, w, cd);
        setProperPlayerLocation(door);
        currentRoom = getOneIntersectingObject(Room.class);
        w.repaint();
    }

    /**
     * Determines if a certain person with a given String name is in the room the player is currently in.
     */
    public boolean isPersonThere(String str){
        List<Person> p = w.getObjects(Person.class);
        for(int i=0; i<p.size(); i++){
            if(p.get(i).getName().equals(str))
                return true;
        }
        return false;
    }

    /**
     * Returns true if an item of a given class is found in the player's bag.
     */
    public boolean isClassInBag(Class c){
        for(int i=0; i<cd.bag.size(); i++){
            if(c.isInstance(cd.bag.get(i)))
                return true;
        }
        return false;
    }

    /**
     * The object that lights up the room moves according to this method.
     */
    private void moveLight(double slope, double mX, double mY, double fX, double fY, Lighters lw){
        mY = slope*(mX-fX) + fY;
        lw.setLocation((int)mX, (int)mY);
        lw.act();
    }

    /**
     * When the x coordinate of the object of the Lighter class (either LightsWay or an AntiLight) is increasing (the flashlight is being pointed to the right side of the screen relative to the player), this happens.
     * The difference between when the flashlight is pointing left vs. right is in whether the variable mX increases or decreases when going through the for loop.
     */
    private void xIncreasesLight(double slope, double mX, double mY, double fX, double fY, Lighters lw){
        int i=0;
        mX=fX;
        mY=fY;
        for(; i<100; mX++){
            moveLight(slope, mX, mY, fX, fY, lw);
            i++;
        }
    }

    /**
     * When the x coordinate of the object of the Lighter class (either LightsWay or an AntiLight) is decreasing (the flashlight is being pointed to the left side of the screen relative to the player), this happens.
     * The difference between when the flashlight is pointing left vs. right is in whether the variable mX increases or decreases when going through the for loop.
     */
    private void xDecreasesLight(double slope, double mX, double mY, double fX, double fY, Lighters lw){
        int i=0;
        mX=fX;
        mY=fY;
        for(; i<100; mX--){
            moveLight(slope, mX, mY, fX, fY, lw);
            i++;
        }
    }

    /**
     * This is the method that is called to turn the lights on or off in the direction the flashlight is pointing.
     * When a LightsWay object is put for the Lighters parameter, it makes a ray of light.
     * When an AntiLight object is put for the Lighters parameter, it blacks out a previous ray of light.
     */
    public void lightsOnOff(double mX, double mY, double fX, double fY, Lighters lw){
        double slope = (mY-fY)/(mX-fX);
        w.addObject(lw, (int)fX, (int)fY);
        lw.turnTowards((int)mX, (int)mY);
        if(mX<=fX && mY<=fY)
            xDecreasesLight(slope, mX, mY, fX, fY, lw);
        else if(mX>fX && mY<fY)
            xIncreasesLight(slope, mX, mY, fX, fY, lw);
        else if(mX>fX && mY>fY)
            xIncreasesLight(slope, mX, mY, fX, fY, lw);
        else
            xDecreasesLight(slope, mX, mY, fX, fY, lw);
    }

    /**
     * Since Greenfoot is dumb and rounds to ints when getting coordinates, it's impossible to have an AntiLight object follow the exact same path as a LightsWay object.
     * For this reason, I have to make three AntiLight objects and cluster them in the same area with slightly different trajectories to cover enough ground to black out almost everywhere that needs to be blacked out.
     * This method blacks out a ray of light that needs to be blacked out.
     */
    public void blackOut(double tempmX, double tempmY, double tempthisX, double tempthisY){
        AntiLight al = new AntiLight();
        lightsOnOff(tempmX, tempmY, tempthisX, tempthisY, al);
        w.removeObject(al);
        AntiLight al2 = new AntiLight();
        lightsOnOff(tempmX+2, tempmY+2, tempthisX-1, tempthisY-1, al2);
        w.removeObject(al2);
        AntiLight al3 = new AntiLight();
        lightsOnOff(tempmX-1, tempmY-1, tempthisX+2, tempthisY+2, al3);
        w.removeObject(al3);
    }

    /**
     * Once the player moves the mouse, the previous coordinates of the mouse are stored so I know where to aim the AntiLight objects when they get rid of the ray of light.
     * This method stores those variables. Makes the vode a little more readable.
     */
    private void storeVars(double mX, double mY, double fX, double fY){
        pastMouseX = mX;
        pastMouseY = mY;
        pastThisX = fX;
        pastThisY = fY;
    }

    /**
     * Once the player stops using the flashlight (pressing f), this method goes through and blacks out every single square just in case the three AntiLight objects missed some spots.
     */
    private void allLightsOut(){
        for(int i=0; i<500; i++){
            List<Black> b = w.getObjectsAt((i%25)*20, (i/25)*20, Black.class);
            if(b.size()>0){
                if(!(b.get(0).isOn()))
                    b.get(0).on();
            }
        }
        wasOn=false;
    }

    /**
     * This whole method deals with the flashlight.
     * If someone is pressing the "f" key, has a flashlight, and is in a dark room, they can use the flashlight.
     * If the flashlight was on and the person moved their mouse, the AntiLight objects go out and turn off the previous ray of light, since the ray shouldn't still be bright if the player stopped pointing their light that way.
     * Then, a new ray is created. Otherwise, if the "f" key isn't down but some lights were on, then everything gets blacked out.
     */
    public void lights(){
        if(Greenfoot.isKeyDown("f") && isClassInBag(Flashlight.class) && darkRoom[((Room)currentRoom).getNum()] && Greenfoot.getMouseInfo()!=null){
            double fX = this.getX();
            double fY = this.getY();
            double mX = Greenfoot.getMouseInfo().getX();
            double mY = Greenfoot.getMouseInfo().getY();
            if(wasOn && ((mX!=pastMouseX || mY!=pastMouseY) || fX!=pastThisX || fY!=pastThisY)){
                double tempmX = pastMouseX;
                double tempmY = pastMouseY;
                double tempthisX = pastThisX;
                double tempthisY = pastThisY;
                storeVars(mX, mY, fX, fY);//I have to store these variables immediately in case the person moves their mouse. That's why all the tempmX stuff is necessary.
                blackOut(tempmX, tempmY, tempthisX, tempthisY);
            }else
                storeVars(mX, mY, fX, fY);//again I can't just store the variables after this if/else bc then it might be too late and the player might have moved their mouse.
            LightsWay lw = new LightsWay();
            lightsOnOff(mX, mY, fX, fY, lw);
            lwX = lw.getX();
            lwY = lw.getY();
            w.removeObject(lw);
            wasOn=true;
        }
        if(!Greenfoot.isKeyDown("f") && isClassInBag(Flashlight.class) && darkRoom[((Room)currentRoom).getNum()] && wasOn)
            allLightsOut();
    }

    /**
     * This method is not used in anything. My friend Sam just wanted to see if this format works in Java since he knows it works in JavaScript but we didn't learn it in our Java class.
     * Turns out it does work, as does my isClassInBag method. A win for all!
     * I thought that kind of formatting was super cool so I kept the method so I could look at it.
     */
    public void sam(){
        System.out.println(isClassInBag(Flashlight.class) ? "yes" : "no");
    }

    /**
     * This gets the value of the "input", which is the item the player chose to trade.
     * Then, based on the value and the person they chose to trade with, it either performs the trade and prints a success message, or doesn't perform the trade and prints why.
     */
    public int doTrade(String input, String pplInput){
        int value=0;
        for(int i=0; i<cd.bag.size(); i++){
            if(cd.bag.get(i).toString().equals(input)){
                value = cd.bag.get(i).getValue();
                if((value==10000000 || value==50) && !pplInput.equals("C") && !pplInput.equals("H") && !pplInput.equals("A")){
                    System.out.println(pplInput + " says the painting looks too expensive. They refuse the trade.");
                    value=0;
                }else if(value==10000000 && (pplInput.equals("H") || pplInput.equals("A"))){
                    System.out.println(pplInput + " says they don't want your painting. They refuse the trade.");
                    value=0;
                }else if(value==50 && (pplInput.equals("C") || pplInput.equals("H"))){
                    System.out.println(pplInput + " says this is a worthless fake. They refure the trade.");
                    value=0;
                }else if(value==50 && pplInput.equals("A")){
                    System.out.println(pplInput + " says this is a fake, but he'll trade you anyways. Consider yourself lucky.");
                    cd.bag.remove(i);
                    money+=value;
                }else{
                    cd.bag.remove(i);
                    money+=value;
                }
            }
        }
        return value;
    }

    /**
     * This gets and returns the player's choice of people to trade with.
     */
    public String getTradePerson(List<Person> people){
        String[] pplChoices = new String[people.size()];
        for(int i=0; i<people.size(); i++)
            pplChoices[i] = people.get(i).getName();
        String pplInput = (String) JOptionPane.showInputDialog(null, "These are the people in the room. Pick one to trade with.",
                "Trade", JOptionPane.QUESTION_MESSAGE, null,
                pplChoices,
                pplChoices[0]);
        return pplInput;
    }

    /**
     * This gets and returns the player's choice of items to trade.
     */
    public String getTradeObject(){
        int size = cd.bag.size();
        String[] choices = new String[size];
        for(int i=0; i<choices.length; i++)
            choices[i] = cd.bag.get(i).toString();
        String input = (String) JOptionPane.showInputDialog(null, "These are the items currently in your bag. Pick one to trade.",
                "Trade", JOptionPane.QUESTION_MESSAGE, null,
                choices,
                choices[0]);
        return input;
    }

    /**
     * This method removes the player from the list of people available to trade with.
     */
    public void removeSelfFromTraders(List<Person> people){
        for(int i=0; i<people.size(); i++){
            if(people.get(i) instanceof Player)
                people.remove(i);
        }
    }

    /**
     * If there are items in bag and people to trade with and the player picks one of each, the trade is processed and either occurs or doesn't depending on the trader and the item.
     * Otherwise, an error message is printed.
     */
    public void tradeIfPossible(){
        if(cd.bag.size()>0){
            List<Person> people = w.getObjects(Person.class);
            removeSelfFromTraders(people);
            if(people.size()>0){
                String pplInput = getTradePerson(people);
                if(pplInput!=null){
                    String input = getTradeObject();
                    if(input!=null){
                        if(input!=null && pplInput!=null){
                            int value=doTrade(input, pplInput);
                            if(value!=0)
                                System.out.println("Trade successful. " + pplInput + " now has your " + input + " and you now have " + money + " dollars. You gained " + value + " dollars from the trade.");
                        }
                    }
                }
            }else
                System.out.println("No one to trade with.");
        }else
            System.out.println("Nothing to trade.");
    }

    /**
     * This method prints the results of the inspection. For each piece of art in the player's bag, a verdict is reached and printed.
     */
    private void inspectArt(){
        String ans = "you have a ";
        for(int i=0; i<cd.bag.size(); i++){
            if(cd.bag.get(i) instanceof Art){
                if(cd.bag.get(i) instanceof RealArt)
                    ans+="real Goya and a ";
                else
                    ans+="fake and a ";
            }
        }
        System.out.println("H has done the analysis. He says " + ans.substring(0, ans.length()-7) + ".");
    }
    
    /**
     * This is how the player controls horizontal movement.
     */
    public void moveLeftRight(){
        if((Greenfoot.isKeyDown("left") && Greenfoot.isKeyDown("right")) || !((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right")))){}
        else if(Greenfoot.isKeyDown("left")){
            setRotation(180);
            move(3);
        }else{
            setRotation(0);
            move(3);
        }
    }
    
    /**
     * This is how the player controls vertical movement.
     */
    public void moveUpDown(){
        if((Greenfoot.isKeyDown("up") && Greenfoot.isKeyDown("down")) || !((Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down")))){}
        else if(Greenfoot.isKeyDown("up")){
            setRotation(270);
            move(3);
        }else{
            setRotation(90);
            move(3);
        }
    }

    /**
     * This is how the person moves. It also contains the inspecting, trading, and flashlight using methods bc here was a convenient place to put them.
     */
    public void movePerson(){
        if("i".equals(GetKeyDown.key) && isClassInBag(Art.class) && (isClassInBag(Magnifier.class) || w.getObjects(Magnifier.class).size()>0) && isPersonThere("H"))
            inspectArt();
        else if("i".equals(GetKeyDown.key))
            System.out.println("To inspect, you have to have at least one piece of art in your bag, a magnifying glass in your bag or in the room, and H in the room.");
        if("t".equals(GetKeyDown.key))
            tradeIfPossible();
        lights();
        moveLeftRight();
        moveUpDown();
    }
}