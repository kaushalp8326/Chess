package chess;

import piece.*;
import board.*;

public class Chess {

	public static final int BLACK = ChessPiece.BLACK;
	public static final int WHITE = ChessPiece.WHITE;
	//White must make the first move
	public static int turn=WHITE;
	
	/**
	 * 
	 * @param turn - whoever resigns on their turn loses the game
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
	
	public static void main(String[] args) {
		Board game=new Board();
		game.setup();
		boolean gameEnd=false;
		while(!gameEnd) {
			if(turn==WHITE) {
				
			}
		}
	}
	
}
