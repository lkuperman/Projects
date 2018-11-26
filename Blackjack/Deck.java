
/**
 * Holds a deck of all 52 cards and plays blackjack game
 * 
 * Author @Kuperman
 * 1/8/15
 */
public class Deck
{
    //constants
    private final int CARDS_IN_DECK=52;
    private final int NUM_SUITS=4;
    private final int NUM_RANKS=13;
    private Card[] deck = new Card[CARDS_IN_DECK];
    private int num=0;
    private int leftInDeck;
    private int dealt=0;
    private int top=0;
    private Hand myHand;
    private Hand compHand;
    private String again="";

    /**
     * Constructor for objects of class Deck
     */
    public Deck()
    {
        setDeck();
    }

    /**
     * Loads all 52 cards into the deck
     */
    public void setDeck()
    {
        for (int i=0; i<NUM_SUITS; i++)
        {
            for (int k=1; k<=NUM_RANKS; k++) //start at 1 so that there are 13 ranks
            {
                deck[num] = new Card(i, k);
                if (num<51) //avoids crash error when num is 52
                {
                    num++;
                }
            }
        }
    }

    /**
     * Prints every card in the deck
     */
    public String toString()
    {
        String temp="";
        for (int i=0; i<CARDS_IN_DECK; i++)
        {
            temp += deck[i].toString() + "\n";
        }
        return temp;
    }

    /**
     * determines whether the deck is empty or not
     */
    public boolean empty()
    {
        if (leftInDeck==0)
        {
            return true;
        }
        return false;
    }

    /**
     * The number of cards left is equal to the total number of cards minus how many have been dealt
     */
    public int numCards()
    {
        leftInDeck=CARDS_IN_DECK - dealt;
        return leftInDeck;
    }

    public Card dealCard()
    {
        Card temp;
        temp = deck[top]; //keeps track of where the top of the deck is
        top++;
        dealt++;
        return temp;
    }
    
    public int setTopDeck()
    {
        top=0;
        return top;
    }

    public void shuffle() 
    {
        int temp=0;
        Card x;
        java.util.Random rand = new java.util.Random();
        for (int i=0; i<numCards(); i++) 
        {
            temp = rand.nextInt(numCards());
            // picks random index between 0 and what's left in the deck - 1

            x = deck[i];
            deck[i] = deck[temp];
            deck[temp] = x;
            // swap cards[i] and cards[temp]
        }
    }
}