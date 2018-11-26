import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * The villain class is an abstract class that allows the villain to follow
 * and attack the turtle
 * 
 * @Kuperman
 * 5/7/17
 */
public abstract class Villain extends Game
{
    final int FOLLOW_RADIUS=600;
    /**
     * Act - do whatever the Villain wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {

    }    
    
    /**
     * If turtle is on a villain object, set the turtle back to its starting 
     * position in the level
     * 
     * Also accounts for glitch when the attacker is on the turtle's initial 
     * position--villain will be sent back to its original position if this is the 
     * case
     */
    public void attack()
    {
        if (onTurtle())
        {
            if (getX()==Underwater.turtleStartX && getY()==Underwater.turtleStartY)
            {
                setLocation(Underwater.villainStartX, Underwater.villainStartY);
                setRotation(Underwater.villainStartRotation);
                //start villain location and rotation
            }
            else
                getTurtle().backToStart();
        }
    }

    /**
     * if the turtle is in the given "follow radius" then the villain will
     * turn towards it and follow it at the given speed
     */
    public void follow(int speed, int addAngle)
    //addAngle is so that the image of the actor turns the right way
    //the shark originally faces backwards
    {
        Actor turtle;
        List<Turtle> tortuga = getObjectsInRange(FOLLOW_RADIUS,Turtle.class);
        if (tortuga.size()>0)
        {
            turtle = tortuga.get(0);
            if (turtle!=null)
            {
                turnTowards(turtle,addAngle);
                move(speed);
            }
        }
    }
}
