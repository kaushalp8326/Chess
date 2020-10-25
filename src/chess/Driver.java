package chess;

import piece.*;
import java.util.ArrayList;
import board.*;

/*
 * Proving-ground class for testing new methods, classes, whatever
 */
public class Driver {

	public static final int BLACK = ChessPiece.BLACK;
	public static final int WHITE = ChessPiece.WHITE;
	
	public static void main(String[] args) {
		
		Board b = new Board();
		King k = new King(0,4,BLACK);
		Rook r = new Rook(0,0,BLACK);
		Rook r2 = new Rook(0,7,BLACK);
		b.add(r);
		b.add(r2);
		b.add(k);
		ArrayList<int[]> moves = k.getValidMoves();
		System.out.println(b);
		for(int[] m: moves) {
			System.out.println("[" + m[0] + "," + m[1] + "]");
		}
		
		r.move(3, 0);
		moves = k.getValidMoves();
		System.out.println(b);
		for(int[] m: moves) {
			System.out.println("[" + m[0] + "," + m[1] + "]");
		}
		
		k.move(6, 0);
		System.out.println(b);
		moves = k.getValidMoves();
		for(int[] m: moves) {
			System.out.println("[" + m[0] + "," + m[1] + "]");
		}
		
	}
	
}
