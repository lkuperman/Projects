import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The turtle is the main character in the game.
 * 
 * The turtle's goal is to make it through all of the levels with the least
 * amount of tries, while collecting the most amount of shrimp possible.
 * 
 * The turtle will be chased by the shark, and the starfish will slow the turtle
 * down if the turtle runs into it.
 * 
 * If the turtle shoots a shark, the shark will freeze in position. The shark can
 * still kill the turtle if the turtle touches the shark. The turtle can only shoot
 * when it has ammo. To find ammo, the turtle must swim over the bubbles.
 * 
 * If the turtle touches or is touched by any of the villains in the game,
 * the turtle will start back at its initial position in the level.
 * 
 * Leah Kuperman
 * 5/7/17
 */
public class Turtle extends Game
{
    static int tries=1;
    static int shrimpEaten=0;
    static final int turtleSpeed=4;
    static int slower=0;
    private final int BUBBLE_SPEED=5;
    static int ammo=0;
    /**
     * Act - do whatever the Turtle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move();
        eat();
        getAmmo();
        shoot();
    }    
    
    /**
     * Turtle eats shrimp if it swims over it
     */
    public void eat()
    {
        Actor shrimp;
        shrimp = getOneObjectAtOffset(0, 0, Shrimp.class);
        if (shrimp!=null)
        {
            World world;
            world = getWorld();
            world.removeObject(shrimp);
            crunch.play();
            shrimpEaten++;
        }
    }

    /**
     * Collects bubbles as ammo when turtle swims over them
     */
    public void getAmmo()
    {
        Actor bubble;
        bubble = (Bubbles)getOneObjectAtOffset(0, 0, Bubbles.class);
        if (bubble!=null && !((Bubbles)bubble).isShooting())
        //checks if bubbles are stationary or are for shooting
        {
            World world;
            world = getWorld();
            world.removeObject(bubble);
            bubbles.play();
            ammo++;
        }
    }
    
    /**
     * Sets turtle back to its original position and rotation within the level
     */
    public void backToStart()
    {
        Underwater world;
        world = (Underwater)getWorld();
        setLocation(world.turtleStartX, world.turtleStartY);
        setRotation(world.turtleStartRotation);
        tries++;    //adds to number of tries
        slower=0;       //sets speed back
    }
    
    /**
     * If the user has ammunition to use then it can shoot the shark by
     * clicking the screen.
     * 
     * Bubbles are sent off from the direction and position of the turtle
     */
    public void shoot()
    {
        Bubbles bubbles = new Bubbles(BUBBLE_SPEED);    //sets speed
        Underwater world;
        world = (Underwater)getWorld();
        if (Greenfoot.mouseClicked(null) && ammo>0) //shoots by clicking mouse
        {
            world.addObject(bubbles, getX(), getY());
            bubbles.setRotation(getRotation()); 
            //send in direction and location from turtle position
            shoot.play();
            ammo--; //decrease ammo
        }
    }

    /**
     * Tells the turtle how to move and when it can move
     */
    public void move()
    {
        if (Greenfoot.isKeyDown("up"))  //moves direction turtle is facing
            move(turtleSpeed-slower);
        if (Greenfoot.isKeyDown("down"))//moves in opposite direction it is facing
            move(-(turtleSpeed-slower));
        if (Greenfoot.isKeyDown("right")) //rotates clockwise
            setRotation(getRotation()+6);    
        if (Greenfoot.isKeyDown("left")) //rotates counterclockwise
            setRotation(getRotation()-6);    
        if (getY()>=600)
            backToStart();
    }
}
