package piece;

import java.util.ArrayList;

public class Bishop extends ChessPiece{
	/**
	 * Constructor for Bishop
	 * @param row
	 * @param column
	 * @param team
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
		
		return moves;
	}
	
	public String toString() {
		if(team == WHITE) {
			return "wB";
		}else {
			return "bB";
		}
	}
	
}