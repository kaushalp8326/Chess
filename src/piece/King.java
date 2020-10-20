package piece;

import java.util.ArrayList;

public class King extends ChessPiece{
	
	/**
	 * Private member to determine if a king can still castle.
	 * Is initially true, but becomes false once the King moves.
	 */
	private boolean castleEligible;
	
	/**
	 * Constructor for King
	 * @param row
	 * @param column
	 * @param team
	 */
	public King(int row, int column, int team) {
		super(row, column, team);
		this.castleEligible = true;
	}
	
	public boolean move(int newY, int newX) {
		// Check that the proposed move is in the valid moveset and contained in the
		// chessboard's boundaries
		ArrayList<int[]> moves = getValidMoves();
		boolean valid = false;
		for(int[] m: moves) {
			if(m[0] == newY && m[1] == newX) {
				valid = true;
				break;
			}
		}
		if(!valid) {
			return false;
		}
		
		// If we're castling, move the appropriate Rook first
		if(castleEligible) {
			if(newY == row && newX == 2) {
				Rook rl = (Rook)(board.getBoard()[row][0]);
				rl.move(3, row);
				rl.castleEligible = false;
			}else if(newY == row && newX == 6) {
				Rook rr = (Rook)(board.getBoard()[row][7]);
				rr.move(5, row);
				rr.castleEligible = false;
			}
		}
		
		// Move the piece and note that this King can't castle anymore.
		castleEligible = false;
		this.board.remove(this);
		this.row = newY;
		this.column = newX;
		this.board.add(this);
		
		return true;
	}

	public ArrayList<int[]> getValidMoves() {
	
		// TODO - None of this checks for whether the King puts himself in check. Still need to figure out how that will work
		
		ArrayList<int[]> moves = new ArrayList<int[]>(8);
		
		// Check the 8 possible non-castling moves for the king, clockwise from 12 o'clock.
		ChessPiece[][] b = board.getBoard();
		int r = row + 1; int c = column;
		if(areValidCoordinates(r,c) &&
				(b[r][c] == null || b[r][c].getTeam() != team)){
			moves.add(new int[]{r,c});
		}
		
		r = row + 1; c = column + 1;
		if(areValidCoordinates(r,c) &&
				(b[r][c] == null || b[r][c].getTeam() != team)){
			moves.add(new int[]{r,c});
		}
		
		r = row; c = column + 1;
		if(areValidCoordinates(r,c) &&
				(b[r][c] == null || b[r][c].getTeam() != team)){
			moves.add(new int[]{r,c});
		}
		
		r = row - 1; c = column + 1;
		if(areValidCoordinates(r,c) &&
				(b[r][c] == null || b[r][c].getTeam() != team)){
			moves.add(new int[]{r,c});
		}
		
		r = row - 1; c = column;
		if(areValidCoordinates(r,c) &&
				(b[r][c] == null || b[r][c].getTeam() != team)){
			moves.add(new int[]{r,c});
		}
		
		r = row - 1; c = column - 1;
		if(areValidCoordinates(r,c) &&
				(b[r][c] == null || b[r][c].getTeam() != team)){
			moves.add(new int[]{r,c});
		}
		
		r = row; c = column - 1;
		if(areValidCoordinates(r,c) &&
				(b[r][c] == null || b[r][c].getTeam() != team)){
			moves.add(new int[]{r,c});
		}
		
		r = row + 1; c = column - 1;
		if(areValidCoordinates(r,c) &&
				(b[r][c] == null || b[r][c].getTeam() != team)){
			moves.add(new int[]{r,c});
		}
		
		/*
		 * Castling is allowed if the King hasn't moved, there are only empty spaces between the King and the
		 * Rook, and the Rook also hasn't moved. Furthermore, none of the spaces the King crosses can be attacked
		 * by any other piece (TODO).
		 */
		// Left castle
		if(b[row][0] instanceof Rook) {
			Rook rl = (Rook)b[row][0];
			if(this.castleEligible && rl.getTeam() == this.team && rl.castleEligible
					&& b[row][1] == null && b[row][2] == null && b[row][3] == null) {
				moves.add(new int[]{row,2});
			}
		}
		
		// Right castle
		if(b[row][7] instanceof Rook) {
			Rook rr = (Rook)b[row][7];
			if(this.castleEligible && rr.getTeam() == this.team && rr.castleEligible
					&& b[row][5] == null && b[row][6] == null) {
				moves.add(new int[]{row,6});
			}
		}
		
		return moves;
	}
	
	public String toString() {
		if(team == WHITE) {
			return "wK";
		}else {
			return "bK";
		}
	}
	
}
