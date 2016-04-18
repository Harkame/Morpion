import java.util.Scanner;
import java.lang.StringBuilder;

public final class Morpion {
	private Joueur j1;
	private Joueur j2;
	private char [][] plateau;

	public enum STATUT {NOT_START, STARTED, FINISH};
	private STATUT statut;

	public Morpion(){
		this.statut = STATUT.NOT_START;
		this.plateau = new char [3][3];
		for(int i = 0; i < this.plateau.length; i++){
			for(int j = 0; j < this.plateau.length; j++){
				this.plateau[i][j] = ' ';
			}
		}
		this.j1 = new Joueur('x');
		this.j2 = new Joueur('o');
		System.out.println(this.toString());
	}

	public final void start(){
		while(true){
			this.jouer(this.j1.getid());
			System.out.println(this.toString());
			if(this.statut == STATUT.FINISH){ 
				System.out.println(" -------------------");
				System.out.println(" |                 |");
				System.out.println(" |   WINNER : J1   |");
				System.out.println(" |                 |");
				System.out.println(" -------------------");
				System.out.println("");
				break;
			}
			this.jouer(this.j2.getid());
			System.out.println(this.toString());
			if(this.statut == STATUT.FINISH){ 
					System.out.println(" -------------------");
				System.out.println(" |                 |");
				System.out.println(" |   WINNER : J2   |");
				System.out.println(" |                 |");
				System.out.println(" -------------------");
				System.out.println("");
				break;
			}
		}			
	}

	private final void jouer(int p_joueur){
		System.out.println("~~~ J" + p_joueur + " ~~~");
		Scanner clavier = new Scanner(System.in);
		System.out.print("  [LIGNE] : ");
		int ligne = clavier.nextInt();
		if(ligne < 0 || ligne >= this.plateau.length){
			while ( ligne < 0 || ligne >= this.plateau.length) {
				System.out.print("  [LIGNE] : ");
				ligne = clavier.nextInt();
			}
		}
		System.out.print("[COLONNE] : ");
		int colonne = clavier.nextInt();
		if(colonne < 0 || colonne >= this.plateau.length){
			while ( colonne < 0 || colonne >= this.plateau.length) {
				System.out.print("[COLONNE] : ");
				colonne = clavier.nextInt();
			}
		}
		System.out.println("");
		if(this.plateau[ligne][colonne] == ' '){
			if(p_joueur == 1){
				this.plateau[ligne][colonne] = this.j1.getsymbole();
			} else {
				this.plateau[ligne][colonne] = this.j2.getsymbole();
			}
		}
		else {
			System.out.println("Case deja prise ! ");
			this.jouer(p_joueur);
		}
		if(this.gagner(this.j1.getid()) || this.gagner(this.j2.getid())){
			this.statut = STATUT.FINISH;
		}
	}

	private final boolean gagner(int p_joueur){
		return this.win_ligne(this.getJoueur(p_joueur)) || this.win_colonne(this.getJoueur(p_joueur)) ||this.win_diagonale(this.getJoueur(p_joueur));
	}
	
	public String toString(){
		StringBuilder resultat = new StringBuilder();
		resultat.append("\n    |     0     |     1     |     2     |\n");
		resultat.append(" ---|-----------|-----------|-----------|---\n");
		for(int i = 0; i < this.plateau.length; i++){
			resultat.append("  " + i + " |     ");
			for(int j = 0; j < this.plateau.length; j++){
				if(j < this.plateau.length -1){
					resultat.append(this.plateau[i][j] + "     |     ");
				}
				else{
					resultat.append(this.plateau[i][j] + "     |");  
				}
			}
			resultat.append(" " + i);
			resultat.append("\n ---|-----------|-----------|-----------|---\n");
		}
		resultat.append("    |     0     |     1     |     2     |\n");
		resultat.append("\n[J1] : " + this.j1.getsymbole() + "\n[J2] : " + this.j2.getsymbole() + "\n");
		return resultat.toString();
	}

	public final boolean win_ligne(Joueur p_joueur){
		for(int i = 0; i < this.plateau.length; i++){
			if((this.plateau[i][0] == p_joueur.getsymbole()) && (this.plateau[i][1] == p_joueur.getsymbole()) && (this.plateau[i][2] == p_joueur.getsymbole())){
					return true;
				}
		}
		return false;
	}

	public final boolean win_colonne(Joueur p_joueur){
		for(int i = 0; i < this.plateau.length; i++){
			if((this.plateau[0][i] == p_joueur.getsymbole()) && (this.plateau[1][i] == p_joueur.getsymbole()) && (this.plateau[2][i] == p_joueur.getsymbole())){
					return true;
				}
		}
		return false;
	}

	public final boolean win_diagonale(Joueur p_joueur){
		return this.win_diagonale_aux_gauche(p_joueur, this.plateau.length - 1, 0) ||  this.win_diagonale_aux_droite(p_joueur, this.plateau.length - 1, this.plateau.length - 1) ;
	}

	private final boolean win_diagonale_aux_gauche(Joueur p_joueur, int p_ligne, int p_colonne){
		if(p_ligne == 0 && this.plateau[p_ligne][p_colonne] == p_joueur.getsymbole()){
			return true;
		} else if(p_ligne == 0 && this.plateau[p_ligne][p_colonne] != p_joueur.getsymbole()){
			return false;
		}
		else if (this.plateau[p_ligne][p_colonne] == ' '){
			return false;
		} else{
			return this.win_diagonale_aux_gauche(p_joueur,  p_ligne - 1, p_colonne + 1);
		}
	}

	private final boolean win_diagonale_aux_droite(Joueur p_joueur, int p_ligne, int p_colonne){
		if(p_ligne == 0 && this.plateau[p_ligne][p_colonne] == p_joueur.getsymbole()){
			return true;
		} else if(p_ligne == 0 && this.plateau[p_ligne][p_colonne] != p_joueur.getsymbole()){
			return false;
		}
		else if (this.plateau[p_ligne][p_colonne] == ' '){
			return false;
		} else{
			return this.win_diagonale_aux_droite(p_joueur,  p_ligne -1, p_colonne - 1);
		}
	}

	public final Joueur getJoueur(int p_id){
		if(this.j1.getid() == p_id){
			return this.j1;
		} else {
			return this.j2;
		}
	}

	public static void main(String [] Args){
		Morpion m = new Morpion();
		m.start();
	}
}
