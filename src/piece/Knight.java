package piece;

import java.util.ArrayList;

/**
 * Knight class.
 * @author Kaushal Patel
 * @author John Hoban
 *
 */
public class Knight extends ChessPiece {
	/**
	 * Constructor for Knight.
	 * @param row
	 * @param column
	 * @param team
	 */
	public Knight(int row, int column, int team) {
		super(row, column, team);
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
	
	/**
	 * Method used to print the piece.
	 */
	public String toString() {
		if(team == WHITE) {
			return "wN";
		}else {
			return "bN";
		}
	}

}
