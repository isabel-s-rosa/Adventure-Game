import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is basically an information class.
 * To learn ore about some of the characters or the art in the game, the player can read the pamphlet.
 * 
 * @author Isabel Rosa
 */
public class Pamphlet extends Actor{
    private int repeat=0;

    /**
     * Constructor for Pamphlet class.
     * Scales the image.
     */
    protected Pamphlet(){
        GreenfootImage image = getImage();
        image.scale(20, 30);
        setImage(image);
    }

    /**
     * Act - do whatever the Pamphlet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * If the player presses "r" when standing on the pamphlet, they can read about Goya.
     * If they press "a", they can learn more about the character A.
     */
    public void act(){
        if(isTouching(Player.class) && Greenfoot.isKeyDown("r") && repeat==0){
            System.out.println("Goya was a famous Spanish artist during the Romantic era.\n" +
                "He was well-known in his time for being the king's portraitist.\n" +
                "Even today, his name is world-renowned, and his paintings are near priceless.\n" +
                "Some say there's a lost Goya. Be the one to find it, and unimaginable riches " +
                "would surely come your way.\nWhispers in the dark say the notorious art dealer, A, " +
                "already has the piece hidden away.\nWith a reputation for trickery, " +
                "one can never be sure. Press \"a\" to learn more about him.");
            repeat=1;
        }else if(isTouching(Player.class) && Greenfoot.isKeyDown("a") && repeat==0){
            System.out.println("A is a French art dealer with a few tricks up their sleeve.\n" +
                "Involved in the Monet scandal of last year, they are cunning in the art of " +
                "mixing truth and lies.\nThey sell just enough real paintings to get a reputation, " +
                "but enough fakes to make a customer wary.");
            repeat=1;
        }else if(isTouching(Player.class) && !Greenfoot.isKeyDown("r") && !Greenfoot.isKeyDown("a"))
            repeat=0;
    }
}
