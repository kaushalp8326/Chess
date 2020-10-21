package chess;

import piece.*;
import board.*;

public class Chess {

	public static final int BLACK = ChessPiece.BLACK;
	public static final int WHITE = ChessPiece.WHITE;
	
	public static void main(String[] args) {
		Board game=new Board();
		game.setup();
	}
	
}
