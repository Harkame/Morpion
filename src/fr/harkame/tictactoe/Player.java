package fr.harkame.tictactoe;

public final class Player
{
	private static int INCR = 1;

	private int	id;
	private char	sigil;

	public Player(char sigil)
	{
		id = INCR++;
		this.sigil = sigil;
	}

	public final int getId()
	{
		return id;
	}

	public final char getSigil()
	{
		return sigil;
	}
}
