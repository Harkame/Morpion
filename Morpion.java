import java.util.Scanner;
import java.lang.StringBuilder;

public final class Morpion {
	private Joueur j1;
	private Joueur j2;
	private char [][] plateau;

	public Morpion(){
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

	public void start(){
			while(!gagner(j1.getnumero()) || !gagner(j2.getnumero())){
				this.jouer(this.j1.getnumero());
				System.out.println(this.toString());
				this.jouer(this.j2.getnumero());
				System.out.println(this.toString());
			}
	}

	private void jouer(int p_joueur){
		Scanner clavier = new Scanner(System.in);
		int ligne = 0;
		int colonne = 0;
		System.out.print("Ligne : ");
		try {
			ligne = clavier.nextInt();
		} catch (Exception e){
			this.jouer(p_joueur);
		}
		if(ligne < 0 || ligne >= this.plateau.length){
			while ( ligne < 0 || ligne >= this.plateau.length) {
				System.out.print("Ligne : ");
				try {
					ligne = clavier.nextInt();
				} catch (Exception e) {
					this.jouer(p_joueur);
				}
			}
		}
		System.out.print("\nColonne : ");
		try {
			colonne = clavier.nextInt();
		} catch (Exception e){
			this.jouer(p_joueur);
		}
		if(colonne < 0 || colonne >= this.plateau.length){
			while ( colonne < 0 || colonne >= this.plateau.length) {
				System.out.print("\nColonne : ");
				try {
				colonne = clavier.nextInt();
				} catch (Exception e){
					this.jouer(p_joueur);
				}
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
	}

	private boolean gagner(int p_joueur){
		return false;
	}
	
	public String toString(){
		StringBuilder resultat = new StringBuilder();
		resultat.append("\n      0        1         2\n");
		resultat.append("  |---------|---------|---------|\n");
		for(int i = 0; i < this.plateau.length; i++){
			resultat.append(i + " |    ");
			for(int j = 0; j < this.plateau.length; j++){
				resultat.append(this.plateau[i][j] + "    |    ");  
			}
			resultat.append("\n  |---------|---------|---------|\n");
		}
		resultat.append("\nJ1 : " + this.j1.getsymbole() + "\nJ2 : " + this.j2.getsymbole() + "\n");
		return resultat.toString();
	}

	public static void main(String [] Args){
		Morpion m = new Morpion();
		m.start();
		System.out.println(m.toString());
	}
}
