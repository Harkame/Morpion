package fr.harkame.morpion;
public final class Joueur
{
	private final int  id;
	private final char symbole;

	private static int incr = 1;

	public Joueur(char p_symbole)
	{
		id = incr++;
		symbole = p_symbole;
	}

	public final int getid()
	{
		return id;
	}

	public final char getsymbole()
	{
		return symbole;
	}
}
