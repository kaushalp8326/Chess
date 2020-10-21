package chess;

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
	public void resign(int turn) {
		if(turn==WHITE) {
			System.out.println("Black wins");
		}else {
			System.out.println("White wins");
		}
		System.exit(0);
	}
	
	/**
	 * A player may offer a draw by appending "draw?" to the end of an otherwise regular move. 
	 * The draw may be accepted if the other player submits "draw" as the entirety of their next move.
	 */
	public void draw() {
		
	}
	
	/**
	 * Main method to run game.
	 * @param args
	 */
	public static void main(String[] args) {
		Board game=new Board();
		game.setup();
		boolean gameEnd=false;
		while(!gameEnd) {
			if(turn==WHITE) {
				System.out.println("White's move: ");
				
			}
		}
	}
	
}
