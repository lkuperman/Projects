
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import javax.swing.JOptionPane.*;

/**
 * This test plays the game of blackjack!
 * 
 * The computer creates a deck, shuffles it, creates 2 hands, prints them to the terminal,
 * determines the winner, and prints the winner.
 * 
 * It will also play the game again as long as there are cards left in the deck to deal.
 *
 * @Kuperman
 * 1/8/17
 */
public class TestCard extends junit.framework.TestCase 
{
    Deck deck;
    private Hand myHand;
    private Hand compHand;
    private String again="";
    private int games=0;
    private int won=0;
    private int tied=0;
    private int lost=0;
    
    /**
     * Default constructor for test class TestCard
     */
    public TestCard()
    {
    }
    
     /**
     * plays blackjack game
     */

    public void play()
    {
        System.out.println("\fWelcome to Blackjack!");
        
        deck = new Deck(); 
        deck.setDeck();
        deck.shuffle();
        //starts game by setting deck and shuffling it

        deck.setTopDeck(); //sets the top of the deck back to zero
        
        myHand = new Hand();
        compHand = new Hand();
        //initializes hand of player and hand of "computer" (even though they are both computers)

        myHand.loadHand(deck.dealCard());
        myHand.loadHand(deck.dealCard());
        compHand.loadHand(deck.dealCard());
        compHand.loadHand(deck.dealCard());
        //loads both hands by taking the cards off the top of the deck

        System.out.println("\n\nYour cards are: \n\n");
        System.out.println(myHand.toString());
        System.out.println("\n\nThe dealer's cards are: \n\n");
        System.out.println(compHand.toString());
        //prints cards of both sides

        System.out.println("\n\nThe value of your cards is " + myHand.getBlackJackValue());
        System.out.println("The value of the dealer's cards is " + compHand.getBlackJackValue());
        //prints value of hand of both sides
        
        games++;        //keeps track of games

        if (compHand.getBlackJackValue()==21)
        {
            System.out.println("\n\nYou lose!\n\nThanks for playing!");
            lost++;         //adds a loss
        }
        else if (myHand.getBlackJackValue()==21)
        {
            System.out.println("\n\nYou win!\n\nThanks for playing!");
            won++;          //adds a win
        }   
        else if (myHand.getBlackJackValue()>compHand.getBlackJackValue())
        {
            System.out.println("\n\nYou win!\n\nThanks for playing!");
            won++;          //adds a win
        }
        else if (myHand.getBlackJackValue()<compHand.getBlackJackValue())
        {
            System.out.println("\n\nYou lose!\n\nThanks for playing!");
            lost++;         //adds a loss
        }
        else if (myHand.getBlackJackValue()==compHand.getBlackJackValue())
        {
            System.out.println("\n\nIt's a tie! The dealer wins.\n\nThanks for playing!");
            tied++;         //adds a tie
        }
        //determines who wins game
        
        System.out.println("\n\nStats:\n\nGames played: " + games);
        System.out.println("Games won: " + won);
        System.out.println("Games tied: " + tied);
        System.out.println("Games lost: " + lost);
        //prints how many games the user has played, won, tied, and lost
        
        again = JOptionPane.showInputDialog("Would you like to play again? \n\n Yes or no?");
        //asks player if they want to play again as long as there are cards left in the deck
    }
    
    /**
     * This method checks all the cases of the answer to the question "Would you like to play again?"
     * If the player says yes, a new game will be generated, if the player says no the game will crash.
     */
    public boolean playAgain()
    {
        if (again==null || deck.numCards()==0)
        {
            return false;
            //if they hit cancel, the game will end
        }
        else if (again.equals("yes"))
        {
            return true;
            //if they say yes, then the game restarts
        }
        else if (again.equals("no"))
        {
            return false;
            //if they say no, the game will end
        }
        else 
        {
            return false;
            //if they don't answer yes or no, the game will end
        }
    }
    
    /**
     * Plays blackjack
     */
    public void test_game()
    {
        play();
        while (playAgain()) //while the player wants to play again, game will reset
        {
            play();
        }
    }
}
