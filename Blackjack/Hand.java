
/**
 * Hand stores two cards from the deck
 * 
 * @Kuperman
 * 1/8/17
 * 
 */
public class Hand
{
    private Card[] hand = new Card[2];
    final int VALUE_FACE_CARD=10;
    final int JACK = 11;
    final int QUEEN = 12;
    final int KING = 13;
    final int ACE = 1;
    int order=0;
    /**
     * Constructor for objects of class Hand
     */
    public Hand()
    {
    }
  
    /**
     * loads the given card c1 into the first spot of hand[]
     */
    public void loadHand(Card c1)
    {
        hand[order]=c1;
        order++;
    }
    
    /**
     * returns first and second card stored in hand[]
     */
    public String toString()
    {
        return hand[0].toString() + " and " + hand[1].toString();
    }
    
    /**
     * Finds and returns the black jack value of the hand
     */
    public int getBlackJackValue()
    {
        int value=0;
        for (Card n: hand)      
        //goes through each card stored in hand, finds value, and adds the values together
        {
            if (n.getRank()>=2 && n.getRank()<=10)
            {
                value += n.getRank();
                //if the card is a number card it is worth its number
            }
            else if (n.getRank()>=JACK && n.getRank()<=KING)
            {
                value += VALUE_FACE_CARD;
                //if the card is a face card it is worth 10
            }
            else if (n.getRank()==ACE)
            {
                value += 11;
                //if the card is an ace it's worth 11
            }
        }
        return value;
    }
}
