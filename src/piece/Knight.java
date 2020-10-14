package piece;

import java.util.ArrayList;

public class Knight extends ChessPiece {
	
	public Knight(int row, int column, int team) {
		super(row, column, team);
	}

	public boolean move(int newX, int newY) {
		
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
		
		// Move the piece
		this.board.remove(this);
		this.row = newY;
		this.column = newX;
		this.board.add(this);
		
		return true;
	}

	public ArrayList<int[]> getValidMoves() {
		
		ArrayList<int[]> moves = new ArrayList<int[]>(8);
		
		// Check the 8 possible moves for a knight, clockwise from 12 o'clock
		// For a knight, a move is valid if it is inside the board and not occupied by a piece of the same color
		ChessPiece[][] b = board.getBoard();
		int r = row + 2; int c = column + 1;
		if(areValidCoordinates(r,c) &&
				(b[r][c] == null || b[r][c].getTeam() != team)){
			moves.add(new int[]{r,c});
		}
		
		r = row + 1; c = column + 2;
		if(areValidCoordinates(r,c) &&
				(b[r][c] == null || b[r][c].getTeam() != team)){
			moves.add(new int[]{r,c});
		}
		
		r = row - 1; c = column + 2;
		if(areValidCoordinates(r,c) &&
				(b[r][c] == null || b[r][c].getTeam() != team)){
			moves.add(new int[]{r,c});
		}
		
		r = row - 2; c = column + 1;
		if(areValidCoordinates(r,c) &&
				(b[r][c] == null || b[r][c].getTeam() != team)){
			moves.add(new int[]{r,c});
		}
		
		r = row - 2; c = column - 1;
		if(areValidCoordinates(r,c) &&
				(b[r][c] == null || b[r][c].getTeam() != team)){
			moves.add(new int[]{r,c});
		}
		
		r = row - 1; c = column - 2;
		if(areValidCoordinates(r,c) &&
				(b[r][c] == null || b[r][c].getTeam() != team)){
			moves.add(new int[]{r,c});
		}
		
		r = row + 1; c = column - 2;
		if(areValidCoordinates(r,c) &&
				(b[r][c] == null || b[r][c].getTeam() != team)){
			moves.add(new int[]{r,c});
		}
		
		r = row + 2; c = column - 1;
		if(areValidCoordinates(r,c) &&
				(b[r][c] == null || b[r][c].getTeam() != team)){
			moves.add(new int[]{r,c});
		}
		
		return moves;
	}
	
	public String toString() {
		if(team == WHITE) {
			return "wN";
		}else {
			return "bN";
		}
	}

}
