import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * The game class provides a series of useful methods that all actors
 * in the game can take advantage of.
 * 
 * Leah Kuperman
 * 5/7/17
 */
public abstract class Game extends Actor
{
    final int TOUCHING_RADIUS=50;
    GreenfootSound bloop = new GreenfootSound("bloop.wav");
    GreenfootSound levelUp = new GreenfootSound("oneup.wav");
    GreenfootSound win = new GreenfootSound("win.wav");
    GreenfootSound crunch = new GreenfootSound("crunch.wav");
    GreenfootSound zap = new GreenfootSound("zap.wav");
    GreenfootSound shoot = new GreenfootSound("shoot.wav");
    GreenfootSound bubbles = new GreenfootSound("bubbles.wav");
    /**
     * changes the image of any object to invisible so that the obkect "disappears"
     */
    public void disappear()
    {
        GreenfootImage pic = new GreenfootImage("trans.png");
        setImage(pic);
    }

    /**
     * Finds a turtle within the "touching radius" of the current actor
     * 
     * if the turtle exists, the turtle is returned
     * 
     * otherwise, null
     */
    public Turtle getTurtle()
    {
        List<Turtle> turt = getObjectsInRange(TOUCHING_RADIUS,Turtle.class);
        if (turt.size()>0)  //crashes without testing for size
            return (Turtle)turt.get(0);
        return null;
    }

    /**
     * Returns if actor is on the turtle or not
     */
    public boolean onTurtle()
    {
        return getTurtle()!=null;
    }

    /**
     * Turns toward a given point
     * 
     * ***found from Greenfoot website
     */
    public void turnTowards (int x, int y, int addAngle)
    //had to add in the "addAngle" argument to account for the shark being 
    //naturally backwards
    //usually can just put in 0 for addAngle and be fine
    {
        double dx = x - getX();
        double dy = y - getY();
        double angle = Math.atan2(dy,dx)*180.0/Math.PI;
        setRotation( addAngle+(int)angle );
    }

    /**
     * Turns toward a given actor
     * 
     * ***found from Greenfoot website
     */
    public void turnTowards (Actor a, int addAngle)
    //see above for addAngle explanation
    {
        turnTowards(a.getX(), a.getY(), addAngle);
    }
}
