package fr.harkame.tictactoe.game;

import java.util.Scanner;

import fr.harkame.tictactoe.game.player.Player;
import fr.harkame.tictactoe.game.state.State;

import java.lang.StringBuilder;

public class TicTacToe
{
	public static String PROPERTY_LINE_SEPARATOR = "line.separator";

	public static int		BOARD_SIZE	= 3;
	public static String	LINE_SEPARATOR	= System.getProperty(PROPERTY_LINE_SEPARATOR);

	private Player	player1;
	private Player	player2;

	private char[][] board;

	private State state;

	private Scanner keyboard;

	/**
	 * Default constructor
	 * 
	 * Initialize the game (state, board, players)
	 */
	public TicTacToe()
	{
		state = State.NOT_START;
		board = new char[BOARD_SIZE][BOARD_SIZE];

		for(int i = 0; i < board.length; i++)
			for(int j = 0; j < board.length; j++)
				board[i][j] = ' ';

		player1 = new Player('x');
		player2 = new Player('o');

		keyboard = new Scanner(System.in);
	}
	
	/**
	 * Constructor with parameters (Player's symbols)
	 * 
	 * @param player1_symbol Symbol of the player 1
	 * @param player2_symbol Symbol of the player 2
	 * 
	 * Initialize the game (state, board, players)
	 */
	public TicTacToe(char player1_symbol, char player2_symbol)
	{
		state = State.NOT_START;
		board = new char[BOARD_SIZE][BOARD_SIZE];

		for(int i = 0; i < board.length; i++)
			for(int j = 0; j < board.length; j++)
				board[i][j] = ' ';

		player1 = new Player(player1_symbol);
		player2 = new Player(player2_symbol);

		keyboard = new Scanner(System.in);
	}

	/**
	 * Start the game
	 * 
	 * While true loop, if an player win (line, column or diagonal) its stop
	 * the program with an wining message
	 */
	public void start()
	{
		state = State.STARTED;
		
		while(true)
		{
			play(player1);
			if(state == State.FINISH)
			{
				refreshScreen();
				System.out.println(createVictoryMessage(player1.getIdentifiant()));
				break;
			}
			play(player2);
			if(state == State.FINISH)
			{
				refreshScreen();
				System.out.println(createVictoryMessage(player2.getIdentifiant()));
				break;
			}
		}

		keyboard.close();
	}

	/**
	 * Refresh the screen, ask to an player to enter the coordinate of the
	 * choosen case, line then column.
	 * 
	 * Put the player's sigil into the choosen case
	 * 
	 * @param player
	 *             Player who play
	 */
	private void play(Player player)
	{
		refreshScreen();

		System.out.println("");
		System.out.println("~~~ P" + player.getIdentifiant() + " ~~~");
		System.out.print("[LINE] : ");
		int line = keyboard.nextInt();
		if(line < 0 || line >= BOARD_SIZE)
		{
			while(line < 0 || line >= BOARD_SIZE)
			{
				System.out.print("[LINE] : ");
				line = keyboard.nextInt();
			}
		}
		System.out.print("[COLUMN] : ");
		int column = keyboard.nextInt();
		if(column < 0 || column >= BOARD_SIZE)
		{
			while(column < 0 || column >= BOARD_SIZE)
			{
				System.out.print("[COLONNE] : ");
				column = keyboard.nextInt();
			}
		}
		System.out.println("");
		if(board[line][column] == ' ')
			board[line][column] = player.getSigil();
		else
		{
			System.out.println("Impossible !");
			play(player);
		}

		if(win(player1) || win(player2))
			state = State.FINISH;
	}

	/**
	 * Check if an player win the game (line, column or diagonal)
	 * 
	 * @param player
	 *             Player to check
	 * @return True if the player win (line, column or diagonal)
	 */
	private boolean win(Player player)
	{
		return winLine(player) || winColumn(player) || winDiagonal(player);
	}

	/**
	 * Check if an player win the game with an line
	 * 
	 * @param player
	 *             Player to check
	 * @return True if the player win an line
	 */
	public boolean winLine(Player player)
	{
		for(int i = 0; i < board.length; i++)
			if((board[i][0] == player.getSigil()) && (board[i][1] == player.getSigil())
				&& (board[i][2] == player.getSigil()))
				return true;

		return false;
	}

	/**
	 * Check if an player win the game with an column
	 * 
	 * @param player
	 *             Player to check
	 * @return True if the player win an column
	 */
	public boolean winColumn(Player player)
	{
		for(int i = 0; i < board.length; i++)
			if((board[0][i] == player.getSigil()) && (board[1][i] == player.getSigil())
				&& (board[2][i] == player.getSigil()))
				return true;

		return false;
	}

	/**
	 * Check if player an player win the game with an diagonal (left or right)
	 * 
	 * @param player
	 *             Player to check
	 * @return True if the player win with an diagonal (left or right)
	 */
	public boolean winDiagonal(Player player)
	{
		return winDiagonalLeft(player) || winDiagonalRight(player);
	}

	/**
	 * Check if an player win the game with right an diagonal
	 * 
	 * @param player
	 *             Player to check
	 * @return True if the player win with an left diagonal
	 */
	private boolean winDiagonalLeft(Player player)
	{
		for(int indice = 0; indice < board.length; indice++)
			if(board[indice][indice] != player.getSigil())
				return false;

		return true;
	}

	/**
	 * Check if player in parameter win the game with left diagonal
	 * 
	 * @param player
	 *             Player to check
	 * @return True if the player win with right diagonal
	 */
	private boolean winDiagonalRight(Player player)
	{
		for(int indice = 0; indice < board.length; indice++)
			if(board[indice][board.length - indice - 1] != player.getSigil())
				return false;

		return true;
	}

	/**
	 * Print character \033c, flush and print toString
	 */
	private void refreshScreen()
	{
		System.out.print("\033c");
		System.out.flush();
		System.out.println(toString());
	}
	
	/**
	 * Create the victory's message, according to the winner (player_id)
	 * 
	 * @param player_id ID of the winner
	 * 
	 * @return An String to describe the victory
	 */
	private String createVictoryMessage(int player_id)
	{
		StringBuilder victoryMessage = new StringBuilder();
		
		victoryMessage.append(" ------------------------");
		victoryMessage.append(LINE_SEPARATOR);
		victoryMessage.append(" |                      |");
		victoryMessage.append(LINE_SEPARATOR);
		victoryMessage.append(" |   WINNER : player");
		victoryMessage.append(player_id);
		victoryMessage.append("   |");
		victoryMessage.append(LINE_SEPARATOR);
		victoryMessage.append(" |                      |");
		victoryMessage.append(LINE_SEPARATOR);
		victoryMessage.append(" ------------------------");
		victoryMessage.append(LINE_SEPARATOR);
		
		return victoryMessage.toString();
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

		toString.append("|");
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
}
