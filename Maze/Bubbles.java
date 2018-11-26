import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The bubble class serves as weaponry for the turtle.
 * 
 * Leah Kuperman
 * 5/7/17
 */
public class Bubbles extends Game
{
    private int speed;
    private boolean shooting=false;
    /**
     * Act - do whatever the Bubbles wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Bubbles()
    {
        speed=0;
    }
    
    /**
     * the two different constructors determine the speed the bubbles should go
     * (some bubbles should not move)
     */
    public Bubbles(int num)
    {
        speed=num;
        shooting=true;
    }
    
    public void act() 
    {
        move(speed);
        if (isAtEdge()) //if reach edge of screen then turn invisible
            disappear();
    }
    
    /**
     * returns whether or not the current bubble is a shooting bubble or a
     * stationary bubble in the world
     */
    public boolean isShooting()
    {
        return shooting;
    }
    
}
