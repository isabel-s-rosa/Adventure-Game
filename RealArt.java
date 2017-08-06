import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the class for the piece of art that is the (secret) aim of the game to find.
 * It's essentially just another piece of art but with a different value.
 * 
 * @author Isabel Rosa
 */
public class RealArt extends Art{
    private World w;
    private ChangeableData cd;
    private final int VALUE = 10000000;
    
    /**
     * Constructor for RealArt Class.
     * Provides access to world and database.
     */
    public RealArt(World w, ChangeableData cd){
        super(w, cd);
        this.w=w;
        this.cd=cd;
    }
    
    /**
     * Returns value of real Goya.
     */
    public int getValue(){
        return VALUE;
    }
}