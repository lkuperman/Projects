/**
 * The Card class constructs the suit and rank of each card in the game.
 * 
 * Leah Kuperman
 * 1/8/17
 */
public class Card
{
    //suits
    final int SPADES = 0;
    final int CLUBS = 3;
    final int HEARTS = 2;
    final int DIAMONDS = 1;
    //ranks
    final int JACK = 11;
    final int QUEEN = 12;
    final int KING = 13;
    final int ACE = 1;
    
    private int suit;
    private int rank;

    public Card(int theSuit, int theRank)
    {
        suit = theSuit;
        rank = theRank;
    }
    
    /**
     * constructor for each variable
     */
    public void setSuit(int theSuit)
    {
        suit = theSuit;
    }
    
    /**
     * accessor for each variable
     */
    public int getSuit()
    {
        return suit;
    }

    /**
     * accessor for each variable
     */
    public int getRank()
    {
        return rank;
    }
    
    /**
     * constructor for each variable
     */
    public void setRank(int theRank)
    {
        rank = theRank;
    }
    
    /**
     * Converts the suit and rank into english so that every card can be printed in a readable way
     *  Ex: "Ace of Spades"
     */
    public String toString()
    {
        String tempSuit="";
        if (suit == SPADES)
            tempSuit += "Spades";
        else if (suit == HEARTS)
            tempSuit += "Hearts";
        else if (suit == CLUBS)
            tempSuit += "Clubs";
        else if (suit == DIAMONDS)
            tempSuit += "Diamonds";

        String tempRank="";
        if (rank>=2 && rank<=10)
            tempRank += rank;
        else if (rank == JACK)
            tempRank += "Jack";
        else if (rank == QUEEN)
            tempRank += "Queen";
        else if (rank == KING)
            tempRank += "King";
        else if (rank == ACE)
            tempRank += "Ace";
 
        return "" + tempRank + " of " + tempSuit;

    }

}
