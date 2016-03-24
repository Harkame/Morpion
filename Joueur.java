public final class Joueur {
	private int numero;
	private char symbole;

	private static int incr;

	public Joueur(char p_symbole){
		this.numero = incr++;
		this.symbole = p_symbole;
	}

	public int getnumero(){
		return this.numero;
	}

	public char getsymbole(){
		return this.symbole;
	}
	
	public static void main(String [] Args){
		
	}
}
