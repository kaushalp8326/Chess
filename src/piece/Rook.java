package piece;

import java.util.ArrayList;

/**
 * Rook class.
 * @author Kaushal Patel
 * @author John Hoban
 *
 */
public class Rook extends ChessPiece{	
	/**
	 * Private member to determine if a king can still castle against this rook.
	 * Is initially true, but becomes false once the Rook moves.
	 */
	public boolean castleEligible;
	
	/**
	 * Constructor for Rook.
	 * @param row
	 * @param column
	 * @param team
	 */
	public Rook(int row, int column, int team) {
		super(row, column, team);
		this.castleEligible = true;
	}
	
	@Override
	public boolean move(int newColumn, int newRow) {
		boolean valid = super.move(newColumn, newRow);
		if(valid) {
			castleEligible = false;
		}
		return valid;
	}

	public ArrayList<int[]> getValidMoves() {
		
		ArrayList<int[]> moves = new ArrayList<int[]>();
		
		// The rook can move up, down, left, and right
		moves.addAll(getMovementLine(UP,NONE));
		moves.addAll(getMovementLine(NONE,RIGHT));
		moves.addAll(getMovementLine(DOWN,NONE));
		moves.addAll(getMovementLine(NONE,LEFT));
		
		return moves;
	}
	
	/**
	 * Method used to print the piece.
	 */
	public String toString() {
		if(team == WHITE) {
			return "wR";
		}else {
			return "bR";
		}
	}
	
}