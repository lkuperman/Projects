import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The starfish class will slow down the turtle each time the turtle swims over it.
 * 
 * The turtle will return to its original speed once it is eaten by the shark or 
 * touches coral.
 * 
 * Leah Kuperman
 * 5/7/17
 */
public class Starfish extends Villain
{
    /**
     * Act - do whatever the Starfish wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        attack();
    }
    
    /**
     * reduces the speed of the turtle then disappears
     * 
     */
    public void attack()
    {
        if (onTurtle() && Turtle.turtleSpeed-1>Turtle.slower)
        {
            Turtle.slower+=2;
            disappear();
        }
    }
}
