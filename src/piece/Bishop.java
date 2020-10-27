package piece;

import java.util.ArrayList;

/**
 * Bishop class.
 * @author Kaushal Patel
 * @author John Hoban
 *
 */
public class Bishop extends ChessPiece{
	/**
	 * Constructor for Bishop.
	 * @param row Starting row/rank.
	 * @param column Starting column/file.
	 * @param team The piece's team. Should be either {@link #BLACK} or {@link #WHITE}.
	 */
	public Bishop(int row, int column, int team) {
		super(row, column, team);
	}

	public ArrayList<int[]> getValidMoves() {
		
		ArrayList<int[]> moves = new ArrayList<int[]>();
		
		// The bishop can move in the 4 diagonal directions
		moves.addAll(getMovementLine(UP,RIGHT));
		moves.addAll(getMovementLine(DOWN,RIGHT));
		moves.addAll(getMovementLine(DOWN,LEFT));
		moves.addAll(getMovementLine(UP,LEFT));
		
		/*
		 * test each move in the moveset to make sure it doesn't put the king in check
		 * remove the moves that are invalid
		 */
		moves = testMoves(moves);
		return moves;
	}
	
	/**
	 * Method used to print the piece.
	 */
	public String toString() {
		if(team == WHITE) {
			return "wB";
		}else {
			return "bB";
		}
	}
	
}