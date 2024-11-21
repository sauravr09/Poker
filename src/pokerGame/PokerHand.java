/**
 * CS Lab 04 - Polymorphism
 * Date: 02/11/20
 */
package pokerGame;

import playingCards.Card;
import playingCards.Rank;
import playingCards.Suit;

/**
 * A class to encapsulate a hand of cards for a 2-card poker game
 *
 * @author RIT CS
 * @author Saurav Raut
 */
public class PokerHand implements Comparable< PokerHand > {

    protected Card firstCard;
    protected Card secondCard;

    /**
     * Initialize a poker hand.
     * @rit.post The hand has no cards
     */
    public PokerHand()
    {
        this.firstCard = null;
        this.secondCard = null;
    }

    /**
     * Add a card to the hand.
     * This method must be called exactly twice because a hand size is 2.
     *
     * @param card the card to add to hand
     * @rit.post cards are in sorted order
     */
    public void addCard( Card card )
    {
        if (firstCard == null)
        {
            firstCard = card;
        }
        secondCard = card;
    }

    /**
     * What kind of hand is this?
     *
     * @return one of the {@link HandType} designations
     * @rit.pre addCard has been called twice
     */
    HandType getType()
    {
        if (firstCard.getSuit() == secondCard.getSuit()) //checks to see if it is a flush (same suit)
        {
            return HandType.FLUSH;
        }
        else if (firstCard.getRank() == secondCard.getRank()) //checks to see if it is a pair (same rank)
        {
            return HandType.TWO_OF_KIND;
        }
        return HandType.REGULAR; // if it a just regular hand
    }

    /**
     * Show this hand's contents.
     *
     * @return a string containing all the cards in the hand
     */
    public String toString()
    {
        return "|"+firstCard.getRank()+""+firstCard.getSuit()+"|"+ " |"+secondCard.getRank()+""+secondCard.getSuit()+"|";
    }

    /**
     * Does this hand beat another hand?<br>
     * Rules
     * <ul>
     *     <li>
     *         Pair beats flush, which beats two arbitrary cards.
     *     </li>
     *     <li>
     *         Otherwise compare higher card ranks.
     *     </li>
     *     <li>
     *         If higher card ranks are the same, compare lower card ranks.
     *     </li>
     *     <li>
     *         If both card ranks are the same, return 0 -- hands are identical.
     *     </li>
     * </ul>
     */
    @Override
    public int compareTo( PokerHand other )
    {
        int firstCardValue = firstCard.getRank().getValue();
        int secondCardValue = secondCard.getRank().getValue();
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
        int oFirstCardValue = other.firstCard.getRank().getValue(); //other card's first card value.
        int oSecondCardValue = other.secondCard.getRank().getValue(); //other card's second card value.
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

        if (getType() == HandType.TWO_OF_KIND && other.getType() != HandType.TWO_OF_KIND) // if I have pair and other don't
        {
            return 1;
        }
        else if(getType() != HandType.TWO_OF_KIND && other.getType() == HandType.TWO_OF_KIND) // If other has pair and I don't
        {
            return -1;
        }
        else if (getType() == HandType.TWO_OF_KIND && other.getType() == HandType.TWO_OF_KIND) // if other and I both have pair
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
        else if(getType() == HandType.FLUSH && other.getType() != HandType.FLUSH) // If i have flush and other don't
        {
            return 1;
        }
        else if(getType() != HandType.FLUSH && other.getType() == HandType.FLUSH) // If i don't have flush and other does.
        {
            return -1;
        }
        else if(getType() == HandType.FLUSH && other.getType() == HandType.FLUSH) // if other and I both have flush
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
