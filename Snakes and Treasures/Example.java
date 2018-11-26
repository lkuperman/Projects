import greenfoot.*;  
import java.util.*;
// IMPORTANT:  If using older version of Greenfoot, uncomment this line.  
//             If using newer version, comment out this line.
//import java.awt.Color;  

/**
 * Write a description of class here.
 * 
 * @author 
 * @version (a version number or a date)
 */
public class Example  extends World
{
    private static final int COLUMNS = 5;  
    private static final int ROWS = 5;
    private static final int CELL_SIZE = 100;
    GreenfootSound gamera = new GreenfootSound("gamera.wav");
    GreenfootSound developers = new GreenfootSound("developers.wav");

    private int flip = 0;

    public Example()
    {    
        super(COLUMNS, ROWS, CELL_SIZE); 

    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this)) { 
            int col = Greenfoot.getMouseInfo().getX();
            int row = Greenfoot.getMouseInfo().getY();
            if (gamera.isPlaying())
                gamera.stop();
            if (developers.isPlaying())
                 developers.stop();
            if (flip == 0)
            //drawText("" + row + " " + col, row*CELL_SIZE + CELL_SIZE/2, col*CELL_SIZE+CELL_SIZE/2);
            {   
                drawGamera(row, col);
                gamera.play();
            }
            else if (flip == 1)
                drawSquare(row, col, Color.GREEN);
            else 
            {
                drawPic(row, col);
                developers.play();
            }
            flip++;
            flip = flip%3;

        }
    }

    /*
     * Draw text in a given cell
     */
    public void drawText(String str, int row, int col) 
    {
        GreenfootImage imageText = new GreenfootImage(str, 18, Color.BLACK, Color.WHITE); 
        getBackground().drawImage(imageText, col, row);

    }

    /**
     * Draws a colored square
     */
    public void drawSquare(int row, int col, Color color)
    {
        GreenfootImage square = new GreenfootImage(CELL_SIZE,CELL_SIZE);
        square.setColor(color);
        square.fillRect(col, row, CELL_SIZE, CELL_SIZE);
        getBackground().drawImage(square, col*CELL_SIZE, row*CELL_SIZE);
    }

    /**
     * Draws a picture of gamera
     */
    public void drawGamera(int row, int col)
    {
        GreenfootImage pic = new GreenfootImage("gamera.png");
        getBackground().drawImage(pic, col*CELL_SIZE, row*CELL_SIZE);
    }

    /**
     * Draws a picture of steve ballman
     */
    public void drawPic(int row, int col)
    {
        GreenfootImage pic = new GreenfootImage("ballmer.png");
        getBackground().drawImage(pic, col*CELL_SIZE, row*CELL_SIZE);
    }
}

