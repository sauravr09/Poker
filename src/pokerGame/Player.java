/**
 * CS Lab 04 - Polymorphism
 * Date: 02/11/20
 */
package pokerGame;

import playingCards.Card;


import java.util.ArrayList;

/**
 * An abstract Player for 2-card poker.
 *
 * @author RIT CS
 * @author Saurav Raut
 */

public abstract class Player implements Comparable< Player > {

    private static int PLAYER_NUMBER_COUNTER = 0;
    protected PokerHand hand;
    protected int playerOrder;
    protected int wins;

    /**
     * Initialize a player for 2-card poker.
     //* @param playerOrder = the number that will represent the player.
     */
    public Player(int playerOrder)
    {

        this.hand = new PokerHand();
        this.playerOrder = PLAYER_NUMBER_COUNTER++;
        this.wins = 0;
    }

    /**
     * Ask the player if they want to stand..
     *
     * @return a boolean value specifying if the human wants to stand
     */
    public abstract boolean stand();

    /**
     * Return the number of wins for this player.
     *
     * @return the number of wins
     */
    public int getWins()
    {
        return wins;
    }

    /**
     * Increment the number of wins for this player.
     */
    public void incrWins()
    {
        wins = wins + 1;
    }

    /**
     * Add a card to the player's hand.
     *
     * @param c the card to add
     */
    public void addCard( Card c )
    {
        hand.addCard(c);
    }

    /**
     * Print the hand in some "nice" format.
     */
    public void printHand()
    {
        System.out.println(hand.toString()+ " --"+this.hand.getType()); //prints the hand and the type it is
    }

    /**
     * Clear out all the player's cards.
     */
    public void newHand()
    {
        hand.firstCard = null;
        hand.secondCard = null;
    }

    /**
     * Get string representation of Player.
     * @return the part of the string representation common to all players:
     *         the player number
     */
    @Override
    public String toString()
    {
        return Integer.toString(playerOrder);
    }

    /**
     * Compare this player's hand with the specified player's hand for order.
     * Returns <table BORDER="1">
     *     <caption>compareTo standard semantics</caption>
     * <tr><td>negative integer</td><td>player hand &lt; computers hand</td>
     * <tr><td>zero</td><td>player hand == computers hand</td>
     * <tr><td>positive integer</td><td>player hand &gt; computers hand</td>
     * </table>
     *
     * @return a negative integer, zero, or a positive integer as this
     *         player is less than, equal to, or greater than the other
     */
    @Override
    public int compareTo( Player other )
    {
        int firstCardValue = hand.firstCard.getRank().getValue();
        int secondCardValue = hand.secondCard.getRank().getValue();
        int highCard;                           //variable to hold the high value card of my hand
        int lowCard;                            //variable to hold the low value card of my hand.
        if (firstCardValue >= secondCardValue) //check which card in my hand is higher.
        {
            highCard = firstCardValue;
            lowCard = secondCardValue;
        }
        else                                //if the first card and the second card in my hand are equal.
        {
            highCard = secondCardValue;
            lowCard = firstCardValue;
        }
        int oFirstCardValue = other.hand.firstCard.getRank().getValue(); //other card's first card value.
        int oSecondCardValue = other.hand.secondCard.getRank().getValue(); //other card's second card value.
        int oHighCard;                                                 //variable to hold the high value card of other's hand
        int oLowCard;                                                  //variable to hold the low value card of other's hand.
        if (oFirstCardValue >= oSecondCardValue)
        {
            oHighCard = oFirstCardValue;
            oLowCard = oSecondCardValue;
        }
        else
        {
            oHighCard = oSecondCardValue;
            oLowCard = oFirstCardValue;
        }

        if (hand.getType() == HandType.TWO_OF_KIND && other.hand.getType() != HandType.TWO_OF_KIND) // if I have pair and other don't
        {
            return 1;
        }
        else if(hand.getType() != HandType.TWO_OF_KIND && other.hand.getType() == HandType.TWO_OF_KIND) // If other has pair and I don't
        {
            return -1;
        }
        else if (hand.getType() == HandType.TWO_OF_KIND && other.hand.getType() == HandType.TWO_OF_KIND) // if other and I both have pair
        {
            if (highCard > oHighCard)
            {
                return 1;
            }
            else if (highCard < oHighCard)
            {
                return -1;
            }
            else if (lowCard > oLowCard)
            {
                return 1;
            }
            else if (lowCard < oLowCard)
            {
                return -1;
            }
            return 0;
        }
        else if(hand.getType() == HandType.FLUSH && other.hand.getType() != HandType.FLUSH) // If i have flush and other don't
        {
            return 1;
        }
        else if(hand.getType() != HandType.FLUSH && other.hand.getType() == HandType.FLUSH) // If i don't have flush and other does.
        {
            return -1;
        }
        else if(hand.getType() == HandType.FLUSH && other.hand.getType() == HandType.FLUSH) // if other and I both have flush
        {
            if (highCard > oHighCard)
            {
                return 1;
            }
            else if (highCard < oHighCard)
            {
                return -1;
            }
            else if (lowCard > oLowCard)
            {
                return 1;
            }
            else if (lowCard < oLowCard)
            {
                return -1;
            }
            return 0;
        }
        else                                                                        // if we both have regular.
        {
            if (highCard > oHighCard)
            {
                return 1;
            }
            else if (highCard < oHighCard)
            {
                return -1;
            }
            else if (lowCard > oLowCard)
            {
                return 1;
            }
            else if (lowCard < oLowCard)
            {
                return -1;
            }
            return 0;
        }

    }

}
