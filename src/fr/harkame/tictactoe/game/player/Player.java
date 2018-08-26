package fr.harkame.tictactoe.game.player;

public final class Player
{
	private static int INCR = 1;

	private int	identifiant;
	private char	sigil;

	/**
	 * Constructor
	 * 
	 * @param sigil
	 *             Sigil whitch represent player during the game
	 */
	public Player(char sigil)
	{
		identifiant = INCR++;
		this.sigil = sigil;
	}

	/**
	 * Getter - identifiant
	 * 
	 * @return identifiant
	 */
	public int getIdentifiant()
	{
		return identifiant;
	}

	/**
	 * Getter - sigil
	 * 
	 * @return sigil
	 */
	public char getSigil()
	{
		return sigil;
	}
}
