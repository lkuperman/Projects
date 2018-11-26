import java.io.File;import java.io.IOException;import java.net.MalformedURLException;import javax.sound.sampled.AudioInputStream;import javax.sound.sampled.AudioSystem;import javax.sound.sampled.Clip;import javax.sound.sampled.LineUnavailableException;import javax.sound.sampled.UnsupportedAudioFileException;/** * Class Game - the main class of the adventure game * *  *  This class is the main class of a text based adventure game.   *  Users can walk through the maze of the hospital, pick up different items, *  find different doctors, and try to save the patient's life. *   *  The player must have the correct combination of doctors and items in a specific room in order to win. *  *  To play this game, create an instance of this class and call the "play" *  routine, or you could right click and test the playEasy method. *  *  This main class creates and initialises all the others: it creates all *  rooms, creates the parser and starts the game.  It also evaluates the *  commands that the parser returns. *   *  @author Leah Kuperman *  @version 12/11/16 */class Game {    private Parser parser;    private Room currentRoom;    private Room er, closet, outside, lounge, office, careRoom, ambulance;     private final int COMMA_SPACE=2;        //the number of characters of a comma and a space is 2    private String inventory="";    private final int MAX_WEIGHT=10;        //the maximum weight a player can hold is 10.    private int currentWeight=0;            //player starts out with no weight.    private final int ITEM_WEIGHT=1;        //each item weighs the same.    private Room lastRoom;    private String following="";    private final int MAX_DRS_FOLLOWING=3;      //maximum number of doctors that can follow at one time is 3    private final int ONE_DR=1;    private int currentDrs=0;    /**     * Create the game and initialise its internal map.     */    public Game()     {        createRooms();        parser = new Parser();    }    /**     * This method runs the sound of the greys anatomy theme song in the background of the game.     *      * This method was found on stackoverflow.com     */    public void playSound() {        try {            Clip clip = AudioSystem.getClip();            AudioInputStream inputStream = AudioSystem.getAudioInputStream(                    Game.class.getResourceAsStream("greys.wav"));            clip.open(inputStream);            clip.start();         } catch (Exception e) {            System.err.println(e.getMessage());        }    }    /**     * Create all the rooms and link their exits together.     */    private void createRooms()    {        // create the rooms including their descriptions, items, and doctors        er = new Room("in the emergency room", "", "Dr.Shepherd");        closet = new Room("in the supply closet", "morphine, syringe, scalpel");        outside = new Room("outside the building");        lounge = new Room("in the lounge", "coffee", "Dr.Grey, Dr.Sloan");        office = new Room("in the main office", "paperwork, key", "ChiefWebber");        careRoom = new Room("in the Patient Care Rooms", "chart", "Dr.Bailey, Dr.Karev");        ambulance = new Room("in the ambulance", "patient, gurnee", "Dr. Yang, Dr.Hunt");        // initialise room exits        er.setExits(outside, closet, lounge, careRoom);        closet.setExits(null, null, null, er);        outside.setExits(null, ambulance, er, null);        lounge.setExits(er, null, office, null);        office.setExits(lounge, null, null, null);        careRoom.setExits(null, er, null, null);        ambulance.setExits(null, null, null, outside);        currentRoom = outside;  // start game outside the building    }    /**     *  Main play routine.  Loops until end of play.     */    public void play()     {                    playSound();        printWelcome();        // Enter the main command loop.  Here we repeatedly read commands and        // execute them until the game is over.        boolean finished = false;        while (!finished)        {            Command command = parser.getCommand();            finished = processCommand(command);            if (allCorrect())            {                System.out.println("Congratulations! \n You saved the patient's life. \n You win!!");                finished = true;                //if all the doctors and items are in the right place then the person wins the game            }        }    }    /**     * These are the circumstances that the player must achieve so that the patient does not die.     * The correct doctors can be determined from the paperwork in the office.     * The correct items must be achieved through trial and error.     */    private boolean allCorrect()    {        String items = er.getItems();        String drs = er.getDoctors();        if (items.indexOf("scalpel")>=0 && items.indexOf("morphine")>=0 && items.indexOf("chart")>=0 && items.indexOf("patient")>=0)        {            if (drs.indexOf("Dr.Grey")>=0 && drs.indexOf("Dr.Yang")>=0 && drs.indexOf("Dr.Karev")>=0)            {                return true;            }        }        return false;    }    /**     * Print out the opening message for the player.     */    private void printWelcome()    {        System.out.println("\f");        System.out.println("Welcome to Grey's Anatomy!");        System.out.println("The goal of the game is to find you patient and save their life");        System.out.println("before it's too late. . ." + "\n");        System.out.println("Your command words are:");        parser.showCommands();        System.out.println("Type 'help' if you need help." + "\n");        System.out.println(currentRoom.longDescription());    }    /**     * Given a command, process the command.     * If this command ends the game, true is returned, otherwise false is     * returned.     */    private boolean processCommand(Command command)     {        if(command.isUnknown())        {            System.out.println("I don't know what you mean...\n");            return false;        }        String commandWord = command.getCommandWord();        if (commandWord.equals("help"))            printHelp();        else if (commandWord.equals("go"))        {            goRoom(command);        } else if (commandWord.equals("quit"))        {            if(command.hasSecondWord())                System.out.println("Quit what?");            else            {                System.out.println("You have quit the game.");                return true;  // signal that we want to quit            }        } else if (commandWord.equals("look"))        {            look(); //calls look method        } else if (commandWord.equals("grab"))        {            grab(command); //calls grab method, which needs a second word        }        else if (commandWord.equals("drop"))        {            drop(command); //calls drop method, which needs a second word        }        else if (commandWord.equals("inventory"))        {            inventory(); //calls inventory method        }        else if (commandWord.equals("bring"))        {            bring(command); //calls bring method, which needs a second word        }        else if (commandWord.equals("leave"))        {            leave(command); //calls leave method, which needs a second word        }        else if (commandWord.equals("following"))        {            following(); //calls following method        }        else if (commandWord.equals("map"))        {            map(); //shows a picture of the map        }        return false;    }    /**     * Prints out information to help the player understand the rules of the game.     *      */    private void printHelp()     {        System.out.println("You are an intern at Seattle Grace Hospital.");        System.out.println("You have been put in charge of the hospital today.");        System.out.println("Find the patient, the supplies, and");        System.out.println("the doctors you need to save their life.");        System.out.println("Your command words are:");        parser.showCommands();        System.out.println("\n");    }    /**      * Try to go to one direction. If there is an exit, enter the new     * room, otherwise print an error message.     *      * Also allows the player to flip back and forth between rooms     */    private void goRoom(Command command)     {        if(!command.hasSecondWord())        {            // if there is no second word, we don't know where to go...            System.out.println("Go where?\n");            return;        }        String direction = command.getSecondWord();        if (direction.equals("back"))        {            //if the person wants to go back instead of a direction...            if (lastRoom==null)            {                System.out.println("Nothing to go back to");                //if they are still in the first room they started in, it will print nothing to go back to            }            else            {                Room temp = currentRoom;            //sets temporaary variable pointing to the current room                currentRoom = lastRoom;             //sets the current room to be the last room                lastRoom = temp;                    //the last room becomes the current room                System.out.println(currentRoom.longDescription());          //prints description of new room            }        }        else        {            // Try to leave current room.            Room nextRoom = currentRoom.nextRoom(direction);            if (nextRoom == null)                System.out.println("There is no door!\n");            else             {                lastRoom = currentRoom;                currentRoom = nextRoom;                System.out.println(currentRoom.longDescription());            }        }    }    /**     * This method allows the user to bring a doctor that is found in the current room     */    private void bring(Command command)    {        if(!command.hasSecondWord())        {            // if there is no second word, we don't know who to look for...            System.out.println("Bring who?");            return;        }        String person = command.getSecondWord();            //the person is the second command word        String roomPerson = currentRoom.getDoctors();  //a new temporary variable points to the doctors found in the room        String newPerson = "";                   //this string will be used to store the doctors that remain in the room        if (ONE_DR + currentDrs <= MAX_DRS_FOLLOWING)        {            currentDrs++;            if (roomPerson.indexOf(person)>=0)            {                int personStart = roomPerson.indexOf(person);           //finds beginning of person's name                int personEnd = personStart + person.length();          //finds end of person's name                if (personEnd==roomPerson.length())                {                    newPerson=roomPerson.substring(0, personStart);                    //if the person's name is found at the end of the string of people in the room, everything before                    //their name will be stored in the new string of doctors in the room                }                else                {                    newPerson=roomPerson.substring(0,personStart) + roomPerson.substring(personEnd+COMMA_SPACE);                    //otherwise if the person is in the beginning or middle of the string of doctors in the room,                    //everything before the doctor's name and everything after the name plus 2 characters                     //(for a comma and a space) will be stored in the new string of doctors in the room                }                currentRoom.setDoctors(newPerson);                //the remaining string of doctors will now be stored into the current room                System.out.println(person + " is coming with you.");                System.out.println("If you want to leave " + person + " say, \"leave " + person + "\"\n");                if (following.equals(""))                {                    following = person;                    //if there is no one already following, the following will equal the dr. that is being brought                }                else                 {                    following += ", " + person;                    //if there is already a doctor following, the new dr. being brought will be added to the                    //following string with a comma and a space                }            }            else            {                System.out.println("You cannot bring " + person + "\n");            }        }        else         {            System.out.println("Too many doctors following you.\n");        }    }    /**     * This method allows the player to leave a doctor that they were previously "bringing" in the current room they are in.     */    private void leave(Command command)    {        if(!command.hasSecondWord())        {            // if there is no second word, we don't know what to drop...            System.out.println("Leave who?\n");            return;        }        String person = command.getSecondWord();            //the person is the second command word        String bringing = following;  //a new temporary variable points to the doctors currently following the player        String newFlwng = "";                   //this string will be used to store the doctors that remain following        if (bringing.indexOf(person)>=0)        {            int persStart = bringing.indexOf(person);          //finds beginning of person's name in string of following            int persEnd = persStart + person.length();        //finds end of person's name in string of following            if (persEnd==bringing.length())            {                newFlwng=bringing.substring(0, persStart);                //if the person is found at the end of the string in the inventory, everything before that person                //will be stored in the new following doctors            }            else            {                newFlwng=bringing.substring(0,persStart) + bringing.substring(persEnd+COMMA_SPACE);                //otherwise if the person is in the beginning or middle of the following everything before the person                 //and everything after the person plus 2 characters (for a comma and a space) will be stored in the                //new following            }            System.out.println("You left " + person + "\n");            //this resets the current room so that the person that was left is now located in that room            if (currentRoom.getDoctors().equals(""))            {                currentRoom.setDoctors(person);                //if there were previously no doctors in the room, the doctor that was left is now set as a doctor in the room            }            else             {                currentRoom.setDoctors(currentRoom.getDoctors() + ", " + person);                //adds the doctor that was left here to the current string of doctors in the room            }            following = newFlwng;            //following now points to what is stored in the remaining doctors that are following the player        }        else        {            System.out.println("You cannot leave " + person + "\n");        }    }    /**     * This method allows the user to grab an item from the current room they are in and put it in their inventory     */    private void grab(Command command)    {        if(!command.hasSecondWord())        {            // if there is no second word, we don't know what to grab...            System.out.println("Grab what?\n");            return;        }        String object = command.getSecondWord();            //the object is the second word of the command        String roomObj = currentRoom.getItems();            //this temporary string points to the items in the room        String newObj = "";                                 //this string will hold the remaining objects in the room        if (roomObj.indexOf(object)>=0)        {            if (ITEM_WEIGHT + currentWeight <= MAX_WEIGHT)            {                currentWeight++;                //if the new item plus the items in the inventory weigh less than the maximum weight, then the item                //can be grabbed                int objStart = roomObj.indexOf(object);         //finds beginning of item in string of room items                int objEnd = objStart + object.length();        //finds end of item in string of room items                if (objEnd==roomObj.length())                {                    newObj=roomObj.substring(0, objStart);                    //if the object is found at the end of the string of items in the room, everything before it                    //will be stored in the new string of room objects                }                else                {                    newObj=roomObj.substring(0,objStart) + roomObj.substring(objEnd+COMMA_SPACE);                    //otherwise if the object is in the beginning or middle of the inventory everything before it                     //and everything after the object plus 2 characters (for a comma and a space)                     //will be stored in the new inventory                }                currentRoom.setItems(newObj);                if (object.equals("paperwork"))                {                    printPW();                }                else                {                    System.out.println("You grabbed the " + object + "\n");                }                //this resets the current room so that the grabbed item is no longer available in that room, and                 //now is found in the inventory                if (inventory.equals(""))                {                    inventory = object;                    //if the inventory is empty, the inventory will equal the grabbed item                }                else                 {                    inventory += ", " + object;                    //if there is already an item in the inventory, the new grabbed item will be added to the                    //inventory string with a comma and a space                }            }            else            {                System.out.println("Too many items in inventory!" + "\n");                //if the new item causes the inventory to exceed the maximum weight, the item can't be added until                 //another item is dropped            }        }        else        {            System.out.println("You cannot grab " + object + "\n");        }    }    /**     * This method allows the player to drop an item from their inventory into the current room they are in.     */    private void drop(Command command)    {        if(!command.hasSecondWord())        {            // if there is no second word, we don't know what to drop...            System.out.println("Drop what?\n");            return;        }        String object = command.getSecondWord();          //the object is the second word of the command        String invObj = inventory;              //this temporary string points to the existing inventory        String newInv = "";         //this is the string that will hold the remaining inventory        if (invObj.indexOf(object)>=0)        {            int objStart = invObj.indexOf(object);          //finds beginning of item in string of inventory            int objEnd = objStart + object.length();        //finds end of item in string of inventory            if (objEnd==invObj.length())            {                newInv=invObj.substring(0, objStart);                //if the object is found at the end of the string in the inventory, everything before that object                //will be stored in the new inventory            }            else            {                newInv=invObj.substring(0,objStart) + invObj.substring(objEnd+COMMA_SPACE);                //otherwise if the object is in the beginning or middle of the inventory everything before the object                 //and everything after the object plus 2 characters (for a comma and a space) will be stored in the                //new inventory            }            System.out.println("You dropped the " + object + "\n");            //this resets the current room so that the item that was dropped is now available in that room            if (currentRoom.getItems().equals(""))            {                currentRoom.setItems(object);                //if there were previously no items in the room, this room now has the dropped item            }            else             {                currentRoom.setItems(currentRoom.getItems() + ", " + object);                //adds the dropped item to the current string of items in the room            }            inventory = newInv;            //inventory now points to what is stored in the new inventory        }        else        {            System.out.println("You cannot drop " + object + "\n");        }    }    /**     *This method looks to see which doctors are following you into the rooms you are going to     *      *      * If there is at least one doctor following, it will print all the doctors that are coming with.     */    private void following()    {        if (following.equals(""))        {            System.out.println("No doctors coming with you.\n");        }        else        {            System.out.println("You have " + following + " coming with you\n");        }    }    /**     *  This method looks to see what is in your inventory     * If there is nothing in your inventory, it will print that there is nothing in your inventory     *      * If there is at least one item, it will print all the items in your inventory.     */    private void inventory()    {        if (inventory.equals(""))        {            System.out.println("Nothing in inventory.\n");        }        else        {            System.out.println("You have " + inventory + " in your inventory\n");        }    }    /**     * This method looks to see which doctors are in the room.     * If there are no doctors, it will print that there aren't any in the room.     *      * If there are, it will print which doctors are in this room.     */    private void look()    {        if (currentRoom.getDoctors().equals(""))        {            System.out.println("Nobody in room.\n");        }        else        {            System.out.println("This room has " + currentRoom.getDoctors() + "\n");        }    }    private void printPW()    {        System.out.println("\n The paperwork reads the following:");        System.out.println("Dr.Bailey is not essential for this patient");        System.out.println("Dr.Yang is essential for this patient");        System.out.println("Dr.Shepherd is not essential for this patient");        System.out.println("Dr.Karev is essential for this patient");        System.out.println("Dr.Hunt is not essential for this patient");        System.out.println("Dr.Grey is essential for this patient");        System.out.println("Dr.Sloan is not essential for this patient");        System.out.println("CheifWebber is not essential for this patient\n");    }    private void map()    {        System.out.println("\n\n");        System.out.println("                           SEATTLE GRACE HOSPITAL MAP ");        System.out.println("");        System.out.println("                          ___________________________________________________");        System.out.println("                          |                          |                      |");        System.out.println("                          |                          |       Ambulance      |");        System.out.println("                          |                          |                      |");        System.out.println("                          |          Outside         |                      |");        System.out.println("                          |                          |______________________|");            System.out.println("                          |                          |");        System.out.println("                          |                          |");        System.out.println("                          |                          |");        System.out.println("   _______________________|__________________________|_______________________");        System.out.println("   |                      |                          |                      |");        System.out.println("   |                      |                          |                      |");        System.out.println("   |                      |                          |                      |");        System.out.println("   |    Patient Care      |        Emergency         |        Supply        |");        System.out.println("   |       Rooms          |          Room            |        Closet        |");        System.out.println("   |                      |                          |                      |");        System.out.println("   |                      |                          |                      |");        System.out.println("   |                      |                          |                      |");        System.out.println("   |______________________|__________________________|______________________|");        System.out.println("                          |                          |");        System.out.println("                          |                          |");        System.out.println("                          |                          |");              System.out.println("                          |         Doctor's         |");           System.out.println("                          |          Lounge          |");        System.out.println("                          |                          |");        System.out.println("                          |                          |");        System.out.println("                          |                          |");        System.out.println("                          |__________________________|");        System.out.println("                          |                          |");                             System.out.println("                          |                          |");                    System.out.println("                          |                          |");                             System.out.println("                          |          Office          |");                              System.out.println("                          |                          |");          System.out.println("                          |                          |");        System.out.println("                          |                          |");        System.out.println("                          |                          |");        System.out.println("                          |__________________________|");        System.out.println("\n\n");    }    public static void playEasy()    {        Game g = new Game();        g.play();    }}