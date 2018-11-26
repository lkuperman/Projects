import greenfoot.*;  
import java.util.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.JOptionPane.*;
/**
 * Welcome to Snakes and Treasures!
 * To start the game, click any square on the board. 
 * 
 * The goal of the game is to uncover as many treasures as you can without finding any snakes.
 * 
 * The flying turtle picture (camera, a Japanese monster) is a treasure, 
 * and the picture of Steve Ballmer is a snake.
 * 
 * The multiplier doubles the value of each treasure you pick during the next three turns.
 * This means if you do not pick a treasure during your next three turns, 
 * you will not receive the benefits of the multiplier.

 * The power up adds one additional life.

 * Each snake you uncover takes away one life. 
 * If you have one life and uncover a snake, you lose the game, 
 * even if you have more points than your opponent.

 * If all squares are uncovered, the player with a higher score will win the game.

 * A new game will be automatically generated as soon as the current game is finished. 
 * To stop playing, press the ?pause? button underneath the game board.
 * 
 * @Kuperman
 * @3/13/17
 */
public class Game extends World
{
    private int[][] board;
    private boolean[][] clicked;
    private int[][] stats;

    private final int PLAYER_1=0;
    private final int PLAYER_2=1;
    private final int LIVES=1;
    private final int SCORE=2;
    private final int SNAKE=1;
    private final int TREASURE=2;
    private final int MULTIPLIER=3;
    private final int LIFE_BOOST=4;
    private final int MULTI_LENGTH=3;
    private final int MULTI_VALUE=2;
    private final int TREASURE_VALUE=100;
    private final int NUM_PLAYERS=2;
    private final int NUM_STATS=4;
    private final int START_LIVES=1;
    private final int MAX_SNAKES=7;
    private final int MAX_TREASURES=10;
    private final int MAX_LIFE_BOOST=4;
    private final int MAX_MULTI=2;

    private static final int COLS = 5;  
    private static final int ROWS = 5;
    private static final int CELL_SIZE = 100;

    private int turn=0;
    private boolean gameOver=false;
    private int wins1=0;
    private int wins2=0;
    private int tie=0;

    GreenfootSound gamera = new GreenfootSound("gamera.wav");
    GreenfootSound developers = new GreenfootSound("developers.wav");
    GreenfootSound bloop = new GreenfootSound("bloop.wav");
    GreenfootSound lifeBoost = new GreenfootSound("oneup.wav");
    GreenfootSound multi = new GreenfootSound("multi.wav");
    GreenfootSound win = new GreenfootSound("win.wav");

    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        super(COLS, ROWS, CELL_SIZE);
        printWelcome(); //prints instructions
        setUp();   //sets up game
    }

    /**
     * Runs the snakes and treasures game and sets up for a new game once the game has ended
     */
    public void act()
    {
        if (!gameOver && Greenfoot.mouseClicked(this)) { 
            int col = Greenfoot.getMouseInfo().getX();
            int row = Greenfoot.getMouseInfo().getY();
            stopMusic();
            //if the user clicks on a new tile, stop the music of the previous tile
            if (!clicked[row][col])     //if the current square hasn't already been clicked, move on
            {
                clicked[row][col]=true;         //sets this square as a clicked square
                if (board[row][col]==TREASURE)      //check if this spot is a treasure
                {   
                    drawGamera(row, col);           //draw treasure pic
                    treasure();
                }
                else if (board[row][col]==SNAKE) {      //check if this spot is a snake
                    drawPic(row, col);              //draw snake pic
                    snake();
                }
                else if (board[row][col]==LIFE_BOOST) {         //check if this spot is a life boost
                    drawLifeBoost(row, col);
                    lifeBoost();
                }
                else if (board[row][col]==MULTIPLIER) {         //check if this spot is a multiplier
                    drawMultiplier(row,col);
                    multi();
                }
                else                //if the square is none of the other things, then its a square
                {
                    drawSquare(row, col, Color.PINK);
                    square();
                }
                if (allClicked())       //if all the squares have been clicked, the game is over
                {
                    gameOver=true;
                    if (stats[PLAYER_1][SCORE]>stats[PLAYER_2][SCORE])                    
                        play1Win();     //if player 1's score is bigger than player 2's then player 1 wins
                    else if (stats[PLAYER_1][SCORE]<stats[PLAYER_2][SCORE])
                        play2Win();     //if player 2's score is bigger than player 1's then player 2 wins
                    else
                    {
                        System.out.println("\fPlayer 1 and 2 tie with "+stats[PLAYER_2][SCORE]+" points.");
                        tie++;  //if scores are equal then count ties
                        printGames();
                    } 
                }
            }
            else        //if the square has already been clicked then print error message
            {
                bloop.play();
                JOptionPane.showMessageDialog(null,"You already clicked that square!");
            }
        }
        if (gameOver)   //if the game is over get set up for a new game
        {
            JOptionPane.showMessageDialog(null,"Press ok to play another game.");
            setUp();
            coverUp();
        }
    }
    
    /**
     * Sets up the game so that multiple rounds can be played
     */
    public void setUp()
    {
        turn=0;         //sets turn to player 1
        gameOver=false;
        //initialized board and clicked to be the same size
        board = new int[ROWS][COLS];
        clicked = new boolean[ROWS][COLS];
        stats = new int[NUM_PLAYERS][NUM_STATS];        //creates 2D array of statistics
        for (int p=0; p<NUM_PLAYERS; p++)
        {
            stats[p][LIVES]=START_LIVES;        //sets lives of both players
        }
        for (int a=0; a<clicked.length; a++)
        {
            for (int b=0; b<clicked[0].length; b++)
            {
                clicked[a][b]=false;            //sets clicked to be false (to start)
            }
        }
        Random rand = new Random();
        int numSnakes = rand.nextInt(MAX_SNAKES) + 1;   //randomizes number of snakes
        int randRow=0;
        int randCol=0;
        for (int i=0; i<numSnakes; i++)
        {
            randRow = rand.nextInt(ROWS);
            randCol = rand.nextInt(COLS);
            board[randRow][randCol]=SNAKE;          //creates a random position for each snake
        }
        for (int c=0; c<MAX_TREASURES; c++)
        {
            randRow = rand.nextInt(ROWS);
            randCol = rand.nextInt(COLS);           //creates a random position for each treasure
            if (board[randRow][randCol]!=SNAKE)         //makes sure not to cover up snakes
                board[randRow][randCol]=TREASURE;
            else 
                c--;
        }
        int numLifeBoost = rand.nextInt(MAX_LIFE_BOOST) + 1;
        for (int g=0; g<numLifeBoost; g++)
        {
            randRow = rand.nextInt(ROWS);       //creates a random position for each life boost
            randCol = rand.nextInt(COLS);
            if (board[randRow][randCol]!=SNAKE && board[randRow][randCol]!=TREASURE)
                board[randRow][randCol]=LIFE_BOOST; //makes sure not to cover up snakes and treasures
            else 
                g--;
        }
        int numMultiplier = rand.nextInt(MAX_MULTI) + 1;
        for (int h=0; h<numMultiplier; h++)
        {
            randRow = rand.nextInt(ROWS);       //creates a random position for each multiplier
            randCol = rand.nextInt(COLS);
            if (board[randRow][randCol]!=SNAKE && board[randRow][randCol]!=TREASURE &&
            board[randRow][randCol]!=LIFE_BOOST)//makes sure not to cover up snakes, treasures or life boosts
                board[randRow][randCol]=MULTIPLIER;
            else 
                h--;
        }
    }

    /**
     * Stops music if it is playing
     */
    public void stopMusic()
    {
        if (gamera.isPlaying())
            gamera.stop();
        if (developers.isPlaying())
            developers.stop();
        if (bloop.isPlaying())
            bloop.stop();
        if (win.isPlaying())
            win.stop();
        if (lifeBoost.isPlaying())
            lifeBoost.stop();
        if (multi.isPlaying())
            multi.stop();
    }

    /**
     * Run this method if a treasure is found
     * keeps track of score including mutliplier
     */
    public void treasure()
    {
        gamera.play();      //play treasure sound effect
        if (turn==PLAYER_1)
        {
            System.out.println("\fPlayer 1 found a treasure!");
            if (stats[PLAYER_1][MULTIPLIER]>0)  //if the player still has a multiplier, multiply the treasure
            {
                stats[PLAYER_1][SCORE]+=(TREASURE_VALUE*MULTI_VALUE);  //store multiplied coins
                stats[PLAYER_1][MULTIPLIER]--;      //take one use away from the multiplier
            }
            else
                stats[PLAYER_1][SCORE]+=TREASURE_VALUE;     //if no multiplier, add normal treasure
            turn++;
        }
        else
        {
            System.out.println("\fPlayer 2 found a treasure!");
            if (stats[PLAYER_2][MULTIPLIER]>0)  //if the player still has a multiplier, multiply the treasure
            {
                stats[PLAYER_2][SCORE]+=(TREASURE_VALUE*MULTI_VALUE);   //store multiplied coins
                stats[PLAYER_2][MULTIPLIER]--;      //take one use away from the multiplier
            }
            else
                stats[PLAYER_2][SCORE]+=TREASURE_VALUE;     //if no multiplier, add normal treasure
            turn--;
        }
        printStats();       //when done, print current stats
    }

    /**
     * If the player clicks on a square while they have a multiplier, one use is taken away from the multiplier
     * then stats are printed out
     */
    public void square()
    {
        if (turn==PLAYER_1)
        {
            if (stats[PLAYER_1][MULTIPLIER]>0)  //if multiplier, subtract one
                stats[PLAYER_1][MULTIPLIER]--;
            turn++;     //changes turns
        }
        else
        {
            if (stats[PLAYER_2][MULTIPLIER]>0)  //if multiplier, subtract one
                stats[PLAYER_2][MULTIPLIER]--;
            turn--;     //changes turns
        }
        System.out.print("\f");
        printStats();   //print stats
    }

    /**
     * Determines if every square on the board has been clicked
     */
    public boolean allClicked()
    {
        for (int i=0; i<clicked.length; i++)
        {
            for (int j=0; j<clicked[i].length; j++)
            {
                if (!clicked[i][j]) 
                    return false; 
                //if any given square hasn't been clicked, then they haven't all been clicked
            }
        }
        return true;
    }

    /**
     * Run this method when a player has found a multiplier
     */
    public void multi()
    {
        multi.play();       //play multi sound effect
        if (turn==PLAYER_1)
        {
            System.out.println("\fPlayer 1 got a multiplier!");
            stats[PLAYER_1][MULTIPLIER]=MULTI_LENGTH;    //gives player mutliplier for next 3 turns
            turn++;     //changes turns
        }
        else
        {
            System.out.println("\fPlayer 2 got a multiplier!");
            stats[PLAYER_2][MULTIPLIER]=MULTI_LENGTH;       //gives player mutliplier for next 3 turns
            turn--;     //changes turns
        }
        printStats();
    }
    
    /**
     * Run this method when a player finds a snake
     */
    public void snake()
    {
        developers.play();      //play snake sound effect
        if (turn==PLAYER_1)
        {
            System.out.println("\fPlayer 1 found a snake!");
            if (stats[PLAYER_1][LIVES]==1)      //if player 1 only had one life, then player 2 wins
            {
                play2Win();     //player two wins
                gameOver=true;      //this game is now over
            }
            else    //otherwise, decrease the number of lives by one and print the current stats
            {
                stats[PLAYER_1][LIVES]--;
                printStats();
            }
            turn++;
        }
        else
        {
            System.out.println("\fPlayer 2 found a snake!");
            if (stats[PLAYER_2][LIVES]==1)     //if player 2 only had one life, then player 1 wins
            {
                play1Win();     //player one wins
                gameOver=true;      //this game is now over
            }
            else        //otherwise, decrease the number of lives by one and print the current stats
            {
                printStats();
                stats[PLAYER_2][LIVES]--;
            }
            turn--;
        } 
    }
    
    /**
     * Run this method when a player finds a life boost
     */
    public void lifeBoost()
    {
        lifeBoost.play();       //play life boost sound effect
        if (turn==PLAYER_1)
        {
            System.out.println("\fPlayer 1 found an extra life!");
            stats[PLAYER_1][LIVES]++;           //increase num lives
            turn++;
        }
        else
        {
            System.out.println("\fPlayer 2 found an extra life!");
            stats[PLAYER_2][LIVES]++;       //increase num lives
            turn--;
        }
        printStats();
    }

    /**
     * Run this method when player 1 has won a game
     * 
     * Prints winning stats and game stats
     */
    public void play1Win()
    {
        System.out.println("Player 2 loses with " + stats[PLAYER_2][SCORE] + " points.");
        System.out.println("Player 1 wins with " + stats[PLAYER_1][SCORE] + " points.");
        win.play();
        wins1++;
        printGames();
    }

    /**
     * Run this method when player 2 has won a game
     * 
     * Prints winning stats and game stats
     */
    public void play2Win()
    {
        System.out.println("Player 1 loses with " + stats[PLAYER_1][SCORE] + " points.");
        System.out.println("Player 2 wins with " + stats[PLAYER_2][SCORE] + " points.");
        win.play();
        wins2++;
        printGames();
    }

    /**
     * Covers up everything on the board with a white square
     */
    public void coverUp()
    {
        for (int i=0; i<board.length; i++)
        {
            for (int j=0; j<board[i].length; j++)
            {
                drawSquare(i, j, Color.WHITE);
            }   
        }
    }

    /**
     * Prints stats after each turn so that the players know where they stand in the game
     */
    public void printStats()
    {
        for (int i=0; i<stats.length; i++)
        {
            System.out.println("Player " + (i+1) + " has " + stats[i][LIVES] + " lives and " 
                + stats[i][SCORE] + " points");
        }
        printGames();
    }

    /**
     * Prints number of games won by each player, and tied
     */
    public void printGames()
    {
        System.out.println("\nPlayer 1: " + wins1 + "\tPlayer 2: " + wins2 +  "\tTies: " + tie);
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
        pic.scale(CELL_SIZE,CELL_SIZE);
        getBackground().drawImage(pic, col*CELL_SIZE, row*CELL_SIZE);
    }

    /**
     * Draws the symbol for the multiplier
     */
    public void drawMultiplier(int row, int col)
    {
        GreenfootImage pic = new GreenfootImage("multi.png");
        pic.scale(CELL_SIZE,CELL_SIZE);
        getBackground().drawImage(pic, col*CELL_SIZE, row*CELL_SIZE);
    }

    /**
     * Draws the symbol for a life boost
     */
    public void drawLifeBoost(int row, int col)
    {
        GreenfootImage pic = new GreenfootImage("oneup.jpg");
        pic.scale(CELL_SIZE,CELL_SIZE);
        getBackground().drawImage(pic, col*CELL_SIZE, row*CELL_SIZE);
    }

    /**
     * Draws a picture of steve ballman
     */
    public void drawPic(int row, int col)
    {
        GreenfootImage pic = new GreenfootImage("ballmer.png");
        pic.scale(CELL_SIZE,CELL_SIZE);
        getBackground().drawImage(pic, col*CELL_SIZE, row*CELL_SIZE);
    }

    /**
     * Prints welcome message and instructions for the game
     */
    public void printWelcome()
    {
        System.out.println("\fWelcome to Snakes and Treasures!");
        System.out.println("The goal of the game is to uncover as many treasures as you can...");
        System.out.println("But watch out for snakes!");
        System.out.println("The flying turtle (gamera) is a treasure,");
        System.out.println("and Steve Ballmer is a snake.");
        System.out.println("The multiplier doubles the value of each treasure you pick");
        System.out.println("during your next three turns.");
        System.out.println("The power up adds one additional life.");
        System.out.println("Each snake you uncover takes away one life.");
        System.out.println("Best of Luck!");
    }
}
