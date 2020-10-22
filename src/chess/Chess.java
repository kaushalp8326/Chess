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
			if(turn==WHITE) {
				System.out.println("White's move: ");
			}else {
				System.out.println("Black's move: ");
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
			int initialColumn;
			int initialRow=8-Integer.parseInt(move.substring(1,2));
			int newColumn;
			int newRow=8-Integer.parseInt(move.substring(4));
			if(move.substring(0,1).equalsIgnoreCase("a")) {
				initialColumn=0;
			}else if(move.substring(0,1).equalsIgnoreCase("b")) {
				initialColumn=1;
			}else if(move.substring(0,1).equalsIgnoreCase("c")) {
				initialColumn=2;
			}else if(move.substring(0,1).equalsIgnoreCase("d")) {
				initialColumn=3;
			}else if(move.substring(0,1).equalsIgnoreCase("e")) {
				initialColumn=4;
			}else if(move.substring(0,1).equalsIgnoreCase("f")) {
				initialColumn=5;
			}else if(move.substring(0,1).equalsIgnoreCase("g")) {
				initialColumn=6;
			}else {
				//h
				initialColumn=7;
			}
			if(move.substring(3,4).equalsIgnoreCase("a")) {
				newColumn=0;
			}else if(move.substring(3,4).equalsIgnoreCase("b")) {
				newColumn=1;
			}else if(move.substring(3,4).equalsIgnoreCase("c")) {
				newColumn=2;
			}else if(move.substring(3,4).equalsIgnoreCase("d")) {
				newColumn=3;
			}else if(move.substring(3,4).equalsIgnoreCase("e")) {
				newColumn=4;
			}else if(move.substring(3,4).equalsIgnoreCase("f")) {
				newColumn=5;
			}else if(move.substring(3,4).equalsIgnoreCase("g")) {
				newColumn=6;
			}else {
				//h
				newColumn=7;
			}
			ChessPiece piece=game.getBoard()[initialRow][initialColumn];
			while(!piece.move(newColumn, newRow)) {
				//Invalid move, try again
				System.out.println("Illegal move, try again");
				if(turn==WHITE) {
					System.out.println("White's move: ");
				}else {
					System.out.println("Black's move: ");
				}
				move=sc.nextLine();
				initialRow=8-Integer.parseInt(move.substring(1,2));
				newRow=8-Integer.parseInt(move.substring(4));
				if(move.substring(0,1).equalsIgnoreCase("a")) {
					initialColumn=0;
				}else if(move.substring(0,1).equalsIgnoreCase("b")) {
					initialColumn=1;
				}else if(move.substring(0,1).equalsIgnoreCase("c")) {
					initialColumn=2;
				}else if(move.substring(0,1).equalsIgnoreCase("d")) {
					initialColumn=3;
				}else if(move.substring(0,1).equalsIgnoreCase("e")) {
					initialColumn=4;
				}else if(move.substring(0,1).equalsIgnoreCase("f")) {
					initialColumn=5;
				}else if(move.substring(0,1).equalsIgnoreCase("g")) {
					initialColumn=6;
				}else {
					//h
					initialColumn=7;
				}
				if(move.substring(3,4).equalsIgnoreCase("a")) {
					newColumn=0;
				}else if(move.substring(3,4).equalsIgnoreCase("b")) {
					newColumn=1;
				}else if(move.substring(3,4).equalsIgnoreCase("c")) {
					newColumn=2;
				}else if(move.substring(3,4).equalsIgnoreCase("d")) {
					newColumn=3;
				}else if(move.substring(3,4).equalsIgnoreCase("e")) {
					newColumn=4;
				}else if(move.substring(3,4).equalsIgnoreCase("f")) {
					newColumn=5;
				}else if(move.substring(3,4).equalsIgnoreCase("g")) {
					newColumn=6;
				}else {
					//h
					newColumn=7;
				}
			}
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
