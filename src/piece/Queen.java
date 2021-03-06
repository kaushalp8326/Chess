package piece;

import java.util.ArrayList;

/**
 * Queen class.
 * @author Kaushal Patel
 * @author John Hoban
 *
 */
public class Queen extends ChessPiece{
	/**
	 * Constructor for Queen.
	 * @param row Starting row/rank.
	 * @param column Starting column/file.
	 * @param team The piece's team. Should be either {@link #BLACK} or {@link #WHITE}.
	 */
	public Queen(int row, int column, int team) {
		super(row, column, team);
	}

	public ArrayList<int[]> getAllMoves() {
		
		ArrayList<int[]> moves = new ArrayList<int[]>();
		
		// The queen can move in 8 directions. Iterate through them, clockwise from 12 o'clock.
		moves.addAll(getMovementLine(UP,NONE));
		moves.addAll(getMovementLine(UP,RIGHT));
		moves.addAll(getMovementLine(NONE,RIGHT));
		moves.addAll(getMovementLine(DOWN,RIGHT));
		moves.addAll(getMovementLine(DOWN,NONE));
		moves.addAll(getMovementLine(DOWN,LEFT));
		moves.addAll(getMovementLine(NONE,LEFT));
		moves.addAll(getMovementLine(UP,LEFT));
		
		return moves;
	}
	
	/**
	 * Method used to print the piece.
	 */
	public String toString() {
		if(team == WHITE) {
			return "wQ";
		}else {
			return "bQ";
		}
	}
	
}
