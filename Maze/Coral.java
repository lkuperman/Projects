import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The coral class exists as a maze preventing the turtle from moving easily
 * throughout the world
 * 
 * Leah Kuperman
 * 5/7/17
 */
public class Coral extends Villain
{
    /**
     * Act - do whatever the Coral wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        attack();
    }    
    
    /**
     * if the turtle touches the coral, play zap sound and then attack
     */
    public void attack()
    {
        if (onTurtle())
            zap.play();
        super.attack();
    }
}
