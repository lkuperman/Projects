import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The shark class follows the turtle and tries to eat it.
 * 
 * The shark will freeze in place if shot by the turtle with bubbles.
 * 
 * Leah Kuperman
 * 5/7/17
 */
public class Shark extends Villain
{
    static boolean stop=false;
    /**
     * Act - do whatever the Shark wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (!stop)
        {
            follow(-1,180);
            stop();
        }
        attack();
    }

    /**
     * Looks for bubbles within the touching radius of the shark
     * 
     * If there are bubbles, the bubbles are returned.
     * 
     * Else, null.
     */
    public Bubbles getBubbles()
    {
        List<Bubbles> bub = getObjectsInRange(TOUCHING_RADIUS,Bubbles.class);
        if (bub.size()>0)
            return (Bubbles)bub.get(0);
        return null;
    }

    /**
     * The shark will stop moving if it is hit by flying bubbles
     * 
     */public void stop()
    {
        if (getBubbles()!=null)
        {
            move(0);
            stop=true;
        }
    }
    
    public void attack()
    {
        if (onTurtle())
            crunch.play();
        super.attack();
    }

}
