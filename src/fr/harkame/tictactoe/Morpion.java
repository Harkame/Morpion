package fr.harkame.tictactoe;

import java.util.Scanner;
import java.lang.StringBuilder;

public final class Morpion
{
	public final static int		BOARD_SIZE	= 3;
	public final static String	LINE_SEPARATOR	= System.getProperty("line.separator");
	public final static Scanner	KEYBOARD		= new Scanner(System.in);

	private Player	player1;
	private Player	player2;

	private char[][] board;

	private State state;

	public Morpion()
	{
		state = State.NOT_START;
		board = new char[BOARD_SIZE][BOARD_SIZE];

		for(int i = 0; i < board.length; i++)
			for(int j = 0; j < board.length; j++)
				board[i][j] = ' ';

		player1 = new Player('x');
		player2 = new Player('o');
	}

	public void start()
	{
		state = State.STARTED;

		while(true)
		{
			play(player1);
			if(state == State.FINISH)
			{
				refreshScreen();
				System.out.println(" ------------------------");
				System.out.println(" |                      |");
				System.out.println(" |   WINNER : player1   |");
				System.out.println(" |                      |");
				System.out.println(" ------------------------");
				break;
			}
			play(player2);
			if(state == State.FINISH)
			{
				refreshScreen();
				System.out.println(" ------------------------");
				System.out.println(" |                      |");
				System.out.println(" |   WINNER : player2   |");
				System.out.println(" |                      |");
				System.out.println(" ------------------------");
				break;
			}
		}

		KEYBOARD.close();
	}

	private void play(Player player)
	{
		refreshScreen();

		System.out.println("~~~ J" + player.getId() + " ~~~");
		System.out.print("  [LINE] : ");
		int line = KEYBOARD.nextInt();
		if(line < 0 || line >= BOARD_SIZE)
		{
			while(line < 0 || line >= BOARD_SIZE)
			{
				System.out.print("  [LINE] : ");
				line = KEYBOARD.nextInt();
			}
		}
		System.out.print("[COLUMN] : ");
		int column = KEYBOARD.nextInt();
		if(column < 0 || column >= BOARD_SIZE)
		{
			while(column < 0 || column >= BOARD_SIZE)
			{
				System.out.print("[COLONNE] : ");
				column = KEYBOARD.nextInt();
			}
		}
		System.out.println("");
		if(board[line][column] == ' ')
			board[line][column] = player.getSigil();
		else
		{
			System.out.println("Impsossible !");
			play(player);
		}

		if(win(player1) || win(player2))
			state = State.FINISH;
	}

	private boolean win(Player player)
	{
		return winLine(player) || winColumn(player) || winDiagonal(player);
	}

	public final boolean winLine(Player p_joueur)
	{
		for(int i = 0; i < board.length; i++)
			if((board[i][0] == p_joueur.getSigil()) && (board[i][1] == p_joueur.getSigil())
				&& (board[i][2] == p_joueur.getSigil()))
				return true;

		return false;
	}

	public final boolean winColumn(Player p_joueur)
	{
		for(int i = 0; i < board.length; i++)
			if((board[0][i] == p_joueur.getSigil()) && (board[1][i] == p_joueur.getSigil())
				&& (board[2][i] == p_joueur.getSigil()))
				return true;

		return false;
	}

	public final boolean winDiagonal(Player p_joueur)
	{
		return winDiagonalLeft(p_joueur) || winDiagonalRight(p_joueur);
	}

	private final boolean winDiagonalLeft(Player p_joueur)
	{
		for(int indice = 0; indice < board.length; indice++)
			if(board[indice][indice] != p_joueur.getSigil())
				return false;

		return true;
	}

	private final boolean winDiagonalRight(Player p_joueur)
	{
		for(int indice = 0; indice < board.length; indice++)
			if(board[indice][board.length - indice - 1] != p_joueur.getSigil())
				return false;

		return true;
	}

	public final void refreshScreen()
	{
		System.out.print("\033c");
		System.out.flush();
		System.out.println(toString());
	}

	@Override
	public String toString()
	{
		StringBuilder toString = new StringBuilder();

		toString.append(LINE_SEPARATOR);
		toString.append("    ");

		for(int index = 0; index < BOARD_SIZE; index++)
		{
			toString.append("|     ");
			toString.append(index);
			toString.append("     ");
		}

		toString.append(LINE_SEPARATOR);
		toString.append(" ---");

		for(int index = 0; index < BOARD_SIZE; index++)
			toString.append("|-----------");

		toString.append("|---");
		toString.append(LINE_SEPARATOR);

		for(int i = 0; i < BOARD_SIZE; i++)
		{
			toString.append("  " + i + " ");

			for(int j = 0; j < BOARD_SIZE; j++)
			{
				toString.append("|     ");
				toString.append(board[i][j]);
				toString.append("     ");
			}

			toString.append("|");

			toString.append(LINE_SEPARATOR);

			toString.append(" ---");

			for(int index = 0; index < BOARD_SIZE; index++)
				toString.append("|-----------");

			toString.append("|---");

			toString.append(LINE_SEPARATOR);
		}

		for(int j = 0; j <= BOARD_SIZE; j++)
			toString.append("    |       ");

		return toString.toString();
	}

	public static void main(String[] Args)
	{
		Morpion morpion = new Morpion();
		morpion.start();
	}
}
