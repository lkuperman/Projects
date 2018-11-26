import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The Underwater class sets up and stores the different levels of this game
 * 
 * @Kuperman
 * 5/7/17
 */
public class Underwater extends World
{
    static int turtleStartX;
    static int turtleStartY;
    static int turtleStartRotation;
    static int villainStartX;
    static int villainStartY;
    static int villainStartRotation;
    static int numShrimp=0;
    final int CELL_SIZE=100;
    final int STATS_HEIGHT=50;
    final int NUM_COLS=6;
    private Turtle t = new Turtle();
    int count=2;
    
    GreenfootSound bloop = new GreenfootSound("bloop.wav");
    GreenfootSound levelUp = new GreenfootSound("oneup.wav");
    GreenfootSound win = new GreenfootSound("win.wav");
    GreenfootSound crunch = new GreenfootSound("crunch.wav");
    
    /**
     * Constructor for objects of class Underwater.
     * 
     */
    public Underwater()
    {    
        super(600, 650, 1); 
        printStats();
        level1();   //starts with level 1
    }

    public void act()
    {
        changeLevel();
        printStats();  //constantly updates stats
    }

    /**
     * removes all objects from the world
     */
    public void removeObjs()
    {
        removeObjects(getObjects(null));
    }
    
    /**
     * Changes levels when turtle is on the arrow
     * 
     * Keeps track of which level to change to
     * 
     * Sets turtle speed back to normal
     */
    public void changeLevel()
    {
        Actor turtle;
        Turtle.slower=0;
        List<Arrow> arrowList = getObjects(Arrow.class);
        Arrow arrow=arrowList.get(0);
        turtle = arrow.getTurtle();
        if (turtle!=null)
        {
            if (count==2)
            {
                level2();
                levelUp.play();
                count++;
            }
            else if (count==3)
            {
                level3();
                levelUp.play();
                count++;
            }
            else if (count==4)
            {
                win.play(); //if you get to level 4 then you win
                Shark.stop=true;        //shark stops moving
            }
        }
    }

    /**
     * Draws a colored square
     */
    public void drawSquare(int row, int col, Color color)
    {
        GreenfootImage square = new GreenfootImage(6*CELL_SIZE,STATS_HEIGHT);
        square.setColor(color);
        square.fillRect(col, row, NUM_COLS*CELL_SIZE, STATS_HEIGHT);
        getBackground().drawImage(square, col*CELL_SIZE, row*STATS_HEIGHT);
    }
    
    /**
     * Draw text in a given cell
     */
    public void drawText(String str, int row, int col) 
    {
        GreenfootImage imageText = new GreenfootImage(str, 18, Color.BLACK, Color.WHITE); 
        getBackground().drawImage(imageText, col, row);
    }
    
    /**
     * Prints stats of game as game is played including:
     * 
     * number of tries, shrimp eaten, and ammo
     */
    public void printStats()
    {
        String tries="Tries: " + t.tries;
        String shrimp="Shrimp Eaten: " + t.shrimpEaten + "/" + numShrimp;
        String ammo = "Ammunition: " + t.ammo;
        drawSquare( 12, 0, Color.WHITE);
        
        drawText(tries, 615, 5);
        drawText(shrimp, 615, 110);
        drawText(ammo, 615, 300);
    }
    
    /**
     * Prepare the world for level 1
     */
    public void level1()
    {
        Bubbles bubbles = new Bubbles();
        addObject(bubbles,207,54);
        bubbles.setRotation(765);
        Bubbles bubs = new Bubbles();
        addObject(bubs,385,29);
        bubs.setRotation(225);
        Coral coral = new Coral();
        addObject(coral,341,390);
        Coral coral2 = new Coral();
        addObject(coral2,184,430);
        Coral coral3 = new Coral();
        addObject(coral3,429,344);
        Coral coral4 = new Coral();
        addObject(coral4,470,270);
        Coral coral5 = new Coral();
        addObject(coral5,323,290);
        Coral coral6 = new Coral();
        addObject(coral6,309,248);
        Coral coral7 = new Coral();
        addObject(coral7,371,246);
        Coral coral8 = new Coral();
        addObject(coral8,429,215);
        Coral coral9 = new Coral();
        addObject(coral9,214,285);
        Coral coral10 = new Coral();
        addObject(coral10,219,177);
        Coral coral11 = new Coral();
        addObject(coral11,463,444);
        Coral coral12 = new Coral();
        addObject(coral12,386,425);
        Coral coral13 = new Coral();
        addObject(coral13,230,373);
        coral2.setLocation(43,455);
        coral13.setLocation(122,453);
        coral12.setLocation(205,450);
        coral.setLocation(286,450);
        coral11.setLocation(368,451);
        coral3.setLocation(445,447);
        coral4.setLocation(555,272);
        coral8.setLocation(478,276);
        coral7.setLocation(405,273);
        coral8.setLocation(478,271);
        coral7.setLocation(405,273);
        coral5.setLocation(332,278);
        coral9.setLocation(38,174);
        coral10.setLocation(256,392);
        coral6.setLocation(215,240);
        coral5.setLocation(337,265);
        coral12.setLocation(198,455);
        coral10.setLocation(256,404);
        coral10.setLocation(265,397);
        coral4.setLocation(557,248);
        coral8.setLocation(478,251);
        coral4.setLocation(557,242);
        coral8.setLocation(479,244);
        coral7.setLocation(400,250);
        coral5.setLocation(317,246);
        coral6.setLocation(232,243);
        coral10.setLocation(263,401);
        coral9.setLocation(25,237);
        Coral coral14 = new Coral();
        addObject(coral14,131,112);
        Coral coral15 = new Coral();
        addObject(coral15,198,168);
        Coral coral16 = new Coral();
        addObject(coral16,316,29);
        Coral coral17 = new Coral();
        addObject(coral17,358,89);
        Coral coral18 = new Coral();
        addObject(coral18,577,158);
        Coral coral19 = new Coral();
        addObject(coral19,551,39);
        Coral coral20 = new Coral();
        addObject(coral20,571,105);
        coral15.setLocation(178,181);
        coral14.setLocation(136,121);
        coral6.setLocation(238,243);
        coral9.setLocation(25,251);
        coral2.setLocation(39,455);
        coral13.setLocation(118,453);
        coral12.setLocation(195,455);
        coral.setLocation(283,452);
        coral12.setLocation(192,452);
        coral10.setLocation(287,401);
        coral10.setLocation(265,405);
        coral10.setLocation(252,406);
        coral6.setLocation(238,241);
        coral3.setLocation(442,448);
        coral11.setLocation(364,452);
        coral3.setLocation(442,448);
        Shrimp shrimp = new Shrimp();
        addObject(shrimp,514,368);
        Shrimp shrimp2 = new Shrimp();
        addObject(shrimp2,46,364);
        Shrimp shrimp3 = new Shrimp();
        addObject(shrimp3,265,562);
        Shrimp shrimp4 = new Shrimp();
        addObject(shrimp4,46,61);
        Shrimp shrimp5 = new Shrimp();
        addObject(shrimp5,452,50);
        shrimp5.setLocation(461,92);
        Arrow arrow = new Arrow();
        addObject(arrow,455,41);
        shrimp5.setLocation(463,109);
        Starfish star = new Starfish();
        addObject(star,252,360);
        addObject(t,67,553);
        turtleStartX=67;
        turtleStartY=553;
        turtleStartRotation=0;
        Greenfoot.setSpeed(50);
        numShrimp+=5;
    }

    /**
     * Prepare the world for level 2
     */
    public void level2()
    {
        removeObjs();
        Bubbles bub = new Bubbles();
        addObject(bub,564,72);
        Coral coral = new Coral();
        addObject(coral,526,460);
        Coral coral2 = new Coral();
        addObject(coral2,459,467);
        Coral coral3 = new Coral();
        addObject(coral3,381,460);
        Coral coral4 = new Coral();
        addObject(coral4,308,463);
        Coral coral5 = new Coral();
        addObject(coral5,241,467);
        Shark shark = new Shark();
        addObject(shark,99,542);
        villainStartX=99;
        villainStartY=542;
        villainStartRotation=0;
        Coral coral6 = new Coral();
        addObject(coral6,158,466);
        Coral coral7 = new Coral();
        addObject(coral7,100,469);
        shark.setLocation(549,345);
        Coral coral8 = new Coral();
        addObject(coral8,153,294);
        Coral coral9 = new Coral();
        addObject(coral9,215,306);
        Coral coral10 = new Coral();
        addObject(coral10,39,150);
        Coral coral11 = new Coral();
        addObject(coral11,108,142);
        Coral coral12 = new Coral();
        addObject(coral12,186,152);
        Coral coral13 = new Coral();
        addObject(coral13,258,143);
        Coral coral14 = new Coral();
        addObject(coral14,328,156);
        Coral coral15 = new Coral();
        addObject(coral15,418,147);
        coral10.setLocation(32,134);
        coral11.setLocation(107,126);
        coral12.setLocation(187,127);
        coral13.setLocation(258,127);
        coral14.setLocation(329,132);
        coral15.setLocation(407,133);
        coral9.setLocation(224,304);
        coral8.setLocation(146,291);
        coral9.setLocation(212,299);
        Coral coral16 = new Coral();
        addObject(coral16,407,308);
        Coral coral17 = new Coral();
        addObject(coral17,584,468);
        coral10.setLocation(32,120);
        coral11.setLocation(107,118);
        coral12.setLocation(188,118);
        coral13.setLocation(258,120);
        coral14.setLocation(329,118);
        coral15.setLocation(401,121);
        Coral coral18 = new Coral();
        Starfish star = new Starfish();
        addObject(star,188,75);
        addObject(coral18,595,128);
        Shrimp shrimp = new Shrimp();
        addObject(shrimp,219,565);
        Shrimp shrimp2 = new Shrimp();
        addObject(shrimp2,312,312);
        Shrimp shrimp3 = new Shrimp();
        addObject(shrimp3,60,218);
        Shrimp shrimp4 = new Shrimp();
        addObject(shrimp4,56,44);
        shark.setLocation(549,315);
        Arrow arrow = new Arrow();
        addObject(arrow,564,36);
        arrow.setRotation(90);
        addObject(t,473,566);
        turtleStartX=473;
        turtleStartY=566;
        t.setRotation(180);
        turtleStartRotation=180;
        numShrimp+=4;
    }

    /**
     * Prepare the world for level 3
     */
    public void level3()
    {
        removeObjs();
        Coral coral = new Coral();
        addObject(coral,554,126);
        Coral coral2 = new Coral();
        addObject(coral2,469,199);
        Coral coral3 = new Coral();
        addObject(coral3,399,147);
        Coral coral4 = new Coral();
        addObject(coral4,565,228);
        Coral coral5 = new Coral();
        addObject(coral5,384,284);
        Coral coral6 = new Coral();
        addObject(coral6,371,210);
        Coral coral7 = new Coral();
        addObject(coral7,503,315);
        Coral coral8 = new Coral();
        addObject(coral8,290,236);
        Coral coral9 = new Coral();
        addObject(coral9,453,365);
        Coral coral10 = new Coral();
        addObject(coral10,307,386);
        Coral coral11 = new Coral();
        addObject(coral11,259,294);
        Coral coral12 = new Coral();
        addObject(coral12,191,248);
        Coral coral13 = new Coral();
        addObject(coral13,148,345);
        Coral coral14 = new Coral();
        addObject(coral14,175,383);
        Coral coral15 = new Coral();
        addObject(coral15,371,435);
        Coral coral16 = new Coral();
        addObject(coral16,490,297);
        Coral coral17 = new Coral();
        addObject(coral17,253,181);
        Starfish starfish = new Starfish();
        addObject(starfish,320,120);
        Starfish starfish2 = new Starfish();
        addObject(starfish2,145,163);
        Shrimp shrimp = new Shrimp();
        addObject(shrimp,200,502);
        Shrimp shrimp2 = new Shrimp();
        addObject(shrimp2,96,449);
        Shrimp shrimp3 = new Shrimp();
        addObject(shrimp3,67,334);
        Shrimp shrimp4 = new Shrimp();
        addObject(shrimp4,62,264);
        Shrimp shrimp5 = new Shrimp();
        addObject(shrimp5,105,225);
        Arrow arrow = new Arrow();
        addObject(arrow,558,552);
        coral.setLocation(556,119);
        coral2.setLocation(482,122);
        coral3.setLocation(340,26);
        coral2.setLocation(482,141);
        coral.setLocation(564,140);
        coral6.setLocation(416,185);
        coral17.setLocation(253,181);
        coral17.setLocation(253,181);
        coral17.setLocation(253,181);
        coral17.setLocation(253,181);
        coral17.setLocation(253,181);
        coral17.setLocation(253,181);
        coral17.setLocation(253,181);
        coral17.setLocation(253,181);
        coral17.setLocation(253,181);
        coral17.setLocation(253,181);
        coral17.setLocation(150,71);
        shrimp5.setLocation(242,51);
        coral3.setLocation(335,74);
        starfish.setLocation(260,288);
        coral2.setLocation(418,184);
        coral.setLocation(572,170);
        coral6.setLocation(498,181);
        coral2.setLocation(458,361);
        coral3.setLocation(444,61);
        coral.setLocation(569,206);
        coral6.setLocation(491,225);
        coral3.setLocation(469,90);
        coral4.setLocation(415,247);
        coral16.setLocation(492,373);
        coral6.setLocation(491,240);
        coral.setLocation(568,217);
        coral8.setLocation(353,226);
        coral5.setLocation(303,214);
        coral8.setLocation(340,210);
        coral8.setLocation(350,230);
        coral16.setLocation(324,137);
        shrimp5.setLocation(452,171);
        coral8.setLocation(355,230);
        coral5.setLocation(257,121);
        coral17.setLocation(186,109);
        coral9.setLocation(108,126);
        coral12.setLocation(127,285);
        starfish2.setLocation(191,201);
        starfish2.setLocation(150,135);
        coral8.setLocation(392,269);
        coral12.setLocation(110,280);
        coral14.setLocation(228,467);
        coral13.setLocation(148,397);
        coral12.setLocation(105,281);
        coral13.setLocation(129,368);
        coral14.setLocation(158,457);
        shrimp4.setLocation(198,197);
        starfish.setLocation(284,278);
        starfish.setLocation(427,284);
        coral11.setLocation(276,277);
        coral10.setLocation(306,350);
        coral15.setLocation(330,434);
        coral2.setLocation(502,406);
        starfish.setLocation(423,544);
        coral8.setLocation(361,495);
        coral7.setLocation(472,322);
        shrimp5.setLocation(469,170);
        coral6.setLocation(491,240);
        coral6.setLocation(491,240);
        coral6.setLocation(491,240);
        coral6.setLocation(491,240);
        coral6.setLocation(491,240);
        coral6.setLocation(491,240);
        coral6.setLocation(491,240);
        coral6.setLocation(491,240);
        coral6.setLocation(491,240);
        coral6.setLocation(491,240);
        coral6.setLocation(491,240);
        coral6.setLocation(491,240);
        coral6.setLocation(491,240);
        coral6.setLocation(491,240);
        coral6.setLocation(491,240);
        coral6.setLocation(491,240);
        coral6.setLocation(491,240);
        coral6.setLocation(491,240);
        coral6.setLocation(491,240);
        coral6.setLocation(183,554);
        shrimp2.setLocation(57,499);
        shrimp3.setLocation(37,375);
        shrimp.setLocation(225,417);
        coral16.setLocation(306,108);
        coral4.setLocation(424,250);
        coral4.setLocation(371,176);
        Coral coral18 = new Coral();
        addObject(coral18,423,256);
        coral3.setLocation(493,78);
        coral4.setLocation(352,169);
        coral18.setLocation(417,247);
        coral11.setLocation(266,291);
        coral10.setLocation(298,365);
        starfish.setLocation(334,419);
        starfish.setLocation(520,413);
        starfish2.setLocation(117,137);
        coral4.setLocation(354,168);
        coral16.setLocation(319,122);
        coral5.setLocation(250,112);
        coral4.setLocation(367,184);
        coral16.setLocation(319,129);
        coral6.setLocation(181,542);
        coral7.setLocation(465,326);
        coral18.setLocation(423,257);
        coral4.setLocation(373,192);
        coral16.setLocation(322,121);
        coral4.setLocation(377,197);
        coral16.setLocation(335,134);
        coral17.setLocation(188,112);
        coral9.setLocation(115,122);
        starfish2.setLocation(112,133);
        coral3.setLocation(484,80);
        coral3.setLocation(473,76);
        coral16.setLocation(316,138);
        coral4.setLocation(362,191);
        coral18.setLocation(413,254);
        coral11.setLocation(255,297);
        coral10.setLocation(297,365);
        coral12.setLocation(108,303);
        coral11.setLocation(276,298);
        coral14.setLocation(148,457);
        coral6.setLocation(162,543);
        coral14.setLocation(143,458);
        coral13.setLocation(127,368);
        shrimp2.setLocation(44,513);
        shrimp3.setLocation(31,376);
        addObject(t,564,48);
        t.setRotation(90);
        turtleStartX=564;
        turtleStartY=48;
        turtleStartRotation=90;
        Shark shark = new Shark();
        addObject(shark,561,316);
        shark.setRotation(30);
        villainStartX=561;
        villainStartY=316;
        villainStartRotation=30;
        arrow.setRotation(90);
        numShrimp+=5;
    }
}
