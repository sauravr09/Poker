/**
 * CS Lab 04 - Polymorphism
 * Date: 02/11/20
 */
package pokerGame;

import playingCards.Card;
import playingCards.Rank;
import playingCards.Suit;

/**
 * A computer player for 2-card poker
 *
 * @author RIT CS
 * @author Saurav Raut
 */
public class Computer extends Player {

    /**
     * Define the hand that the Computer player must beat in order
     * for it to decide to stand.
     */
    private static final PokerHand MIDLAND_HAND;

    static {
        MIDLAND_HAND = new PokerHand();
        MIDLAND_HAND.addCard( new Card( Rank.QUEEN, Suit.HEARTS ) );
        MIDLAND_HAND.addCard( new Card( Rank.JACK, Suit.CLUBS ) );
    }



    /**
     * Initialize a computer player for 2-card poker
     * @param playerOrder: the number that represents the computer player.
     */
    public Computer(int playerOrder)
    {
        super(playerOrder);
    }

    /**
     * Determine if the computer should stand instead of fold.
     * The computer should stand if it has 50% or better chance of winning
     * We will approximate this by saying that the computer player will
     * stand if it has a flush or pair, or otherwise if it beats a hand
     * containing a Queen of Hearts and a Jack of Clubs.
     *
     * @return true iff the computer wants to stand
     */
    public boolean stand()
    {
        int firstCardValue = hand.firstCard.getRank().getValue();
        int secondCardValue = hand.secondCard.getRank().getValue();
        int highCard = 0;
        int lowCard;
        if (firstCardValue >= secondCardValue)
        {
            highCard = firstCardValue;
            lowCard = secondCardValue;
        }
        lowCard = secondCardValue;

        if (hand.getType() == HandType.FLUSH || hand.getType() == HandType.TWO_OF_KIND)
        {
            return true;
        }
        else if (highCard >= MIDLAND_HAND.firstCard.getRank().getValue() && lowCard > MIDLAND_HAND.secondCard.getRank().getValue())
        {
            return true;
        }
        return false;

    }

    /**
     * Get a string representing this computerized player
     * @return all the information in {@link Player#toString()}, plus "Computer"
     */
    @Override
    public String toString()
    {
        return "Computer "+ playerOrder;

    }
}
