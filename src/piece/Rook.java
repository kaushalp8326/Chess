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
	 * @param row Starting row/rank.
	 * @param column Starting column/file.
	 * @param team The piece's team. Should be either {@link #BLACK} or {@link #WHITE}.
	 */
	public Rook(int row, int column, int team) {
		super(row, column, team);
		this.castleEligible = true;
	}
	
	@Override
	/**
	 * Moves the Rook. Includes special logic that disallows a Rook to be involved in castling after it has moved.
	 * @param newColumn The piece's column after the move.
	 * @param newRow The piece's row after the move.
	 * @return Returns a boolean value based on if the user has made a valid move.
	 */
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
			return "wR";
		}else {
			return "bR";
		}
	}
	
}