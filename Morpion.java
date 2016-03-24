import java.util.Scanner;
import java.lang.StringBuilder;

public final class Morpion {
	private Joueur j1;
	private Joueur j2;
	private char [][] plateau;

	public Morpion(){
		this.plateau = new char [3][3];
	}

	public void start(){
		Scanner clavier = new Scanner(System.in);
			while(!gagner(j1.getnumero()) || !gagner(j2.getnumero())){
				this.jouer(this.j1.getnumero(), clavier.nextInt(), clavier.nextInt());
				this.jouer(this.j2.getnumero(), clavier.nextInt(), clavier.nextInt());
			}
	}

	private void jouer(int p_joueur, int p_ligne, int p_colonne){
		if(this.plateau[p_ligne][p_colonne] != ' '){
			if(p_joueur == 1){
				this.plateau[p_ligne][p_colonne] = this.j1.getsymbole();
			} else {
				this.plateau[p_ligne][p_colonne] = this.j2.getsymbole();
			}
		}
		else {
			Scanner clavier = new Scanner(System.in);
			this.jouer(p_joueur, clavier.nextInt(), clavier.nextInt());
		}
	}

	private boolean gagner(int p_joueur){
		return false;
	}
	
	public String toString(){
		StringBuilder resultat = new StringBuilder();
		resultat.append("-------------------");
		resultat.append("|");
		for(int i = 0; i < this.plateau.length; i++){
			for(int j = 0; j < this.plateau.length; j++){
				resultat.append(this.plateau[i][j] + " | "); 
			}
			resultat.append("-------------------");
		}
		resultat.append("-------------------");
		return resultat.toString();
	}

	public static void main(String [] Args){
		Morpion m = new Morpion(new Joueur('x'), new Joueur('o'));
		System.out.println(m.toString());
	}
}
