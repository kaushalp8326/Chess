package piece;

import java.util.ArrayList;

/**
 * 
 * @author John Hoban
 *
 */
public class Pawn extends ChessPiece {
	
	private boolean firstMove;
	public boolean enPassantVulnerable;
	
	public Pawn(int row, int column, int team) {
		super(row, column, team);
		this.firstMove = true;
		this.enPassantVulnerable = false;
	}

	@Override
	public boolean move(int newColumn, int newRow) {
		// Check that the proposed move is in the valid moveset and contained in the
		// chessboard's boundaries
		ArrayList<int[]> moves = getValidMoves();
		boolean valid = false;
		for(int[] m: moves) {
			if(m[0] == newRow && m[1] == newColumn) {
				valid = true;
				break;
			}
		}
		if(!valid) {
			return false;
		}
		
		if(firstMove) {
			firstMove = false;
			if(Math.abs(row - newRow) == 2) {
				enPassantVulnerable = true;
			}
		}
		
		// Move the piece
		this.board.remove(this);
		if(newRow==7 || newRow==0) {
			//promote Pawn to a Queen by default if no other piece is indicated
			Queen q=new Queen(newRow, newColumn, this.getTeam());
			this.board.add(q);
		}else {
			this.row = newRow;
			this.column = newColumn;
			this.board.add(this);
		}
		
		return true;
	}
	
	public boolean move(int newColumn, int newRow, String promote) {
		if(newRow==7 || newRow==0) {
			this.board.remove(this);
			if(promote.equalsIgnoreCase("r")) {
				Rook piece=new Rook(newRow, newColumn, this.getTeam());
				this.board.add(piece);
				return true;
			}else if(promote.equalsIgnoreCase("n")) {
				Knight piece=new Knight(newRow, newColumn, this.getTeam());
				this.board.add(piece);
				return true;
			}else if(promote.equalsIgnoreCase("b")) {
				Bishop piece=new Bishop(newRow, newColumn, this.getTeam());
				this.board.add(piece);
				return true;
			}else {
				//promote to queen
				Queen piece=new Queen(newRow, newColumn, this.getTeam());
				this.board.add(piece);
				return true;
			}
		}else {
			//invalid move
			return false;
		}
	}

	public ArrayList<int[]> getValidMoves() {
		
		ArrayList<int[]> moves = new ArrayList<int[]>(4);
		int direction;
		if(team == WHITE) {
			direction = 1;
		}else {
			direction = -1;
		}
		ChessPiece[][] b = board.getBoard();
		
		// Pawns can have up to 4 possible moves available:
		// Forward 1, forward 2, capture/EP left, and capture/EP right
		
		// Forward moves
		int r = row + direction; int c = column;
		if(areValidCoordinates(r,c) && b[r][c] == null){
			moves.add(new int[]{r,c});
		}
		// If a pawn can't move forward 1, it can't move forward 2
		r += direction;
		if(firstMove && areValidCoordinates(r,c) && b[r+direction][c] == null && b[r][c] == null){
			moves.add(new int[]{r,c});
		}
		
		// Left capture
		r = row + direction; c = column - 1;
		if(areValidCoordinates(r,c) && b[r][c] != null && b[r][c].getTeam() != team){
			moves.add(new int[]{r,c});
		}
		// Right capture
		r = row + direction; c = column + 1;
		if(areValidCoordinates(r,c) &&
				(b[r][c] != null && b[r][c].getTeam() != team)){
			moves.add(new int[]{r,c});
		}
		
		// Left en passant
		r = row + direction; c = column - 1;
		if(areValidCoordinates(r,c) && b[r][c] == null && b[row][c] instanceof Pawn && b[row][c].getTeam() != team) {
			Pawn p = (Pawn)b[row][c];
			if(p.enPassantVulnerable) {
				moves.add(new int[] {r,c});
			}
		}
		// Right en passant
		r = row + direction; c = column + 1;
		if(areValidCoordinates(r,c) && b[r][c] == null && b[row][c] instanceof Pawn && b[row][c].getTeam() != team) {
			Pawn p = (Pawn)b[row][c];
			if(p.enPassantVulnerable) {
				moves.add(new int[] {r,c});
			}
		}
		
		return moves;
	}
	
	public String toString() {
		if(team == WHITE) {
			return "wp";
		}else {
			return "bp";
		}
	}

}
