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
	
	public boolean isCheck() {
		for(int row=0; row<8; row++) {{
			for(int column=0; column<8; column++) {
				ChessPiece piece=board.getBoard()[row][column];
				//if the pieces are on the same team as the king, we don't have to check them
				if(piece.getTeam()==this.team) {
					break;
				}else {
					ChessPiece p;
					if(piece instanceof Bishop) {
						p=(Bishop)piece;
					}else if(piece instanceof King) {
						p=(King)piece;
					}else if(piece instanceof Knight) {
						p=(Knight)piece;
					}else if(piece instanceof Pawn) {
						p=(Pawn)piece;
					}else if(piece instanceof Queen) {
						p=(Queen)piece;
					}else {
						//piece is instanceof Rook
						p=(Rook)piece;
					}
					//generate moveset for the piece and see if it contains the kings position
					ArrayList<int[]> moves = p.getValidMoves();
					for(int i=0; i<moves.size(); i++) {
						if(moves.get(i)[0]==this.row && moves.get(i)[1]==this.column) {
							return true;
						}
					}
				}
			}
		}
			
		}
		return false;
	}
	
	public String toString() {
		if(team == WHITE) {
			return "wK";
		}else {
			return "bK";
		}
	}
	
}
