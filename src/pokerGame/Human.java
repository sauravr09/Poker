/**
 * CS Lab 04 - Polymorphism
 * Date: 02/11/20
 */
package pokerGame;

import playingCards.Card;

import java.util.Scanner;

/**
 * A human player for 2-card poker.
 * It depends on user input to decide whether to stand or fold.
 *
 * @author RIT CS
 * @author Saurav Raut
 */
public class Human extends Player {


	public Scanner in;

    /**
     * Initialize a human player for 2-card poker
	 * @param in the Scanner object to be used to prompt user for stand/fold
	 * @param playerOrder: the number that will represent the human player.
     */
    public Human( Scanner in, int playerOrder)
	{
		super(playerOrder);
		this.in = in;
    }

    /**
     * Ask the player if they want to stand.
     * The player is prompted with a suitable message, and then
     * the players response from the Scanner is converted to
     * true (stand) or false (fold).
     *
     * @return true iff the human wants to stand
     */
    public boolean stand()
	{
		while(true)
		{
			System.out.print("Do you want to stand? (y or n)");
			Scanner in = new Scanner(System.in);
			String playerChoice = in.nextLine();
			playerChoice = playerChoice.toLowerCase();
			if(!playerChoice.equals("y") && !playerChoice.equals("n"))
			{
				continue;
			}
			else if (playerChoice.equals(("y")))
			{
				return true;
			}
			return false;

		}
    }

	/**
	 * Get a string representing this human player
	 * @return all the information in {@link Player#toString()}, plus "Human"
	 */
	@Override
	public String toString()
	{
		return "Human "+ playerOrder;
	}
}
