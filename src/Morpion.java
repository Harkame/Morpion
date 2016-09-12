import java.util.Scanner;
import java.lang.StringBuilder;

public final class Morpion
{
	private Joueur j1;
	private Joueur j2;
	private char [][] plateau;

	public enum STATUT
	{
		NOT_START, STARTED, FINISH
	};
	private STATUT statut;

	public Morpion()
	{
		statut = STATUT.NOT_START;
		plateau = new char [3][3];
		for(int i = 0; i < plateau.length; i++)
		{
			for(int j = 0; j < plateau.length; j++)
			{
				plateau[i][j] = ' ';
			}
		}
		j1 = new Joueur('x');
		j2 = new Joueur('o');
		System.out.println(toString());
	}

	public final void start()
	{
		statut = STATUT.STARTED;
		while(true)
		{
			jouer(j1.getid());
			if(statut == STATUT.FINISH)
			{ 
				refreshScreen();
				System.out.println(" -------------------");
				System.out.println(" |                 |");
				System.out.println(" |   WINNER : J1   |");
				System.out.println(" |                 |");
				System.out.println(" -------------------\r\n");
				break;
			}
			jouer(j2.getid());
			if(statut == STATUT.FINISH)
			{ 
				refreshScreen();
				System.out.println(" -------------------");
				System.out.println(" |                 |");
				System.out.println(" |   WINNER : J2   |");
				System.out.println(" |                 |");
				System.out.println(" -------------------\r\n");
				break;
			}
		}			
	}

	private final void jouer(int p_joueur)
	{
		refreshScreen();
		System.out.println("~~~ J" + p_joueur + " ~~~");
		Scanner clavier = new Scanner(System.in);
		System.out.print("  [LIGNE] : ");
		int ligne = clavier.nextInt();
		if(ligne < 0 || ligne >= plateau.length)
		{
			while ( ligne < 0 || ligne >= plateau.length)
			{
				System.out.print("  [LIGNE] : ");
				ligne = clavier.nextInt();
			}
		}
		System.out.print("[COLONNE] : ");
		int colonne = clavier.nextInt();
		if(colonne < 0 || colonne >= plateau.length)
		{
			while ( colonne < 0 || colonne >= plateau.length)
			{
				System.out.print("[COLONNE] : ");
				colonne = clavier.nextInt();
			}
		}
		System.out.println("");
		if(plateau[ligne][colonne] == ' ')
		{
			if(p_joueur == 1)
				plateau[ligne][colonne] = j1.getsymbole();
			else
				plateau[ligne][colonne] = j2.getsymbole();
		}
		else
		{
			System.out.println("Case deja prise ! ");
			jouer(p_joueur);
		}
		if(gagner(j1.getid()) || gagner(j2.getid()))
			statut = STATUT.FINISH;
	}

	private final boolean gagner(int p_joueur)
	{
		return win_ligne(getJoueur(p_joueur)) ||
			win_colonne(getJoueur(p_joueur))  ||
			win_diagonale(getJoueur(p_joueur));
	}
	
	public String toString()
	{
		StringBuilder resultat = new StringBuilder();
		resultat.append("\n    |     0     |     1     |     2     |\r\n");
		resultat.append(" ---|-----------|-----------|-----------|---\r\n");
		for(int i = 0; i < plateau.length; i++)
		{
			resultat.append("  " + i + " |     ");
			for(int j = 0; j < plateau.length; j++)
			{
				if(j < plateau.length -1)
					resultat.append(plateau[i][j] + "     |     ");
				else
					resultat.append(plateau[i][j] + "     |");  
			}
			resultat.append(" " + i);
			resultat.append("\n ---|-----------|-----------|-----------|---\r\n");
		}
		resultat.append("    |     0     |     1     |     2     |\r\n");
		resultat.append("\r\n[J1] : " + j1.getsymbole() + "\r\n[J2] : " + j2.getsymbole() + "\r\n");
		return resultat.toString();
	}

	public final boolean win_ligne(Joueur p_joueur)
	{
		for(int i = 0; i < plateau.length; i++)
		{
			if((plateau[i][0] == p_joueur.getsymbole())  &&
				(plateau[i][1] == p_joueur.getsymbole()) &&
				(plateau[i][2] == p_joueur.getsymbole()))
			{
				return true;
			}
		}
		return false;
	}

	public final boolean win_colonne(Joueur p_joueur)
	{
		for(int i = 0; i < plateau.length; i++)
		{
			if((plateau[0][i] == p_joueur.getsymbole())  &&
				(plateau[1][i] == p_joueur.getsymbole()) &&
				(plateau[2][i] == p_joueur.getsymbole()))
			{
				return true;
			}
		}
		return false;
	}

	public final boolean win_diagonale(Joueur p_joueur)
	{
		return win_diagonale_gauche(p_joueur) ||
				win_diagonale_droite(p_joueur);
	}

	private final boolean win_diagonale_gauche(Joueur p_joueur)
	{
		for(int indice = 0; indice < plateau.length; indice++)
			if(plateau[indice][indice] != p_joueur.getsymbole())
				return false;
		return true;
	}

	private final boolean win_diagonale_droite(Joueur p_joueur)
	{
		for(int indice = 0; indice < plateau.length; indice++)
			if(plateau[indice][plateau.length - indice - 1] != p_joueur.getsymbole())
				return false;
		return true;
	}

	public final Joueur getJoueur(int p_id)
	{
		if(j1.getid() == p_id)
			return j1;
		else 
			return j2;
	}
	
	public final void refreshScreen()
	{
		System.out.print("\033[H\033[2J");  
		System.out.flush();
		System.out.println(toString());
	}  

	public static void main(String [] Args)
	{
		Morpion m = new Morpion();
		m.start();
	}
}
