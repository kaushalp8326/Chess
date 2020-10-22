package chess;

import java.util.*;
import piece.*;
import board.*;

/**
 * Class to be run to start the game.
 * @author Kaushal Patel
 * @author John Hoban
 *
 */
public class Chess {
	/**
	 * Constant to denote the black team's pieces (same as in ChessPiece class).
	 */
	public static final int BLACK = ChessPiece.BLACK;
	/**
	 * Constant to denote the white team's pieces (same as in ChessPiece class).
	 */
	public static final int WHITE = ChessPiece.WHITE;
	/**
	 * Integer value to keep track of which player's turn it is.
	 * Initial value is set to WHITE because white must make the first move.
	 */
	public static int turn=WHITE;
	
	/**
	 * Method to end the game when a player resigns.
	 * @param turn - whoever resigns on their turn loses the game.
	 */
	public static void resign(int turn) {
		if(turn==WHITE) {
			System.out.println("Black wins");
		}else {
			System.out.println("White wins");
		}
		System.exit(0);
	}
		
	/**
	 * Main method to run game.
	 * @param args
	 */
	public static void main(String[] args) {
		Board game=new Board();
		game.setup();
		boolean gameEnd=false;
		boolean drawRequest=false;
		Scanner sc=new Scanner(System.in);
		while(!gameEnd) {
			System.out.println("\n" + game + "\n");
			
			int newColumn;
			int newRow;
			ChessPiece piece;
			do {
				if(turn==WHITE) {
					System.out.print("White's move: ");
				}else {
					System.out.print("Black's move: ");
				}
				String move=sc.nextLine();
				if(move.equalsIgnoreCase("resign")) {
					resign(turn);
				}
				if(move.indexOf("draw?")!=-1) {
					move=move.substring(0,move.indexOf(" draw?"));
					drawRequest=true;
				}else if(drawRequest && !move.equalsIgnoreCase("draw")) {
					drawRequest=false;
				}
				if(move.equalsIgnoreCase("draw") && drawRequest) {
					System.exit(0);
				}
				
				int initialColumn = move.charAt(0) - 'a';
				int initialRow= Integer.parseInt(move.substring(1,2)) - 1;
				newRow= Integer.parseInt(move.substring(4)) - 1;
				newColumn = move.charAt(3) - 'a';
				piece=game.getBoard()[initialRow][initialColumn];
			}while(!piece.move(newColumn, newRow));
			
			//TODO check for checkmate here? (after move has been made)
			if(turn==WHITE) {
				turn=BLACK;
			}else {
				turn=WHITE;
			}
		}
		sc.close();
	}
	
}
