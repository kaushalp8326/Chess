package piece;

import board.*;
import java.util.ArrayList;
import java.util.function.IntFunction;

/**
 * King class.
 * @author Kaushal Patel
 * @author John Hoban
 *
 */
public class King extends ChessPiece{
	
	/**
	 * Private member to determine if a king can still castle.
	 * Is initially true, but becomes false once the King moves.
	 */
	private boolean castleEligible;
	
	/**
	 * Constructor for King.
	 * @param row
	 * @param column
	 * @param team
	 */
	public King(int row, int column, int team) {
		super(row, column, team);
		this.castleEligible = true;
	}
	
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
		
		// If we're castling, move the appropriate Rook first
		if(castleEligible) {
			if(newRow == row && newColumn == 2) {
				Rook rl = (Rook)(board.getBoard()[row][0]);
				rl.move(3, row);
				rl.castleEligible = false;
			}else if(newRow == row && newColumn == 6) {
				Rook rr = (Rook)(board.getBoard()[row][7]);
				rr.move(5, row);
				rr.castleEligible = false;
			}
		}
		
		// Move the piece and note that this King can't castle anymore.
		castleEligible = false;
		this.board.remove(this);
		this.row = newRow;
		this.column = newColumn;
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
		
		/*
		 * test each move in the moveset to make sure it doesn't put the king in check
		 * remove the moves that are invalid
		 */
		/*
		Board temp=this.board;
		for(int i=0; i<moves.size(); i++) {
			temp=this.board;
			temp.remove(this.row,this.column);
			King tempKing=new King(moves.get(i)[0], moves.get(i)[1], this.getTeam());
			temp.add(tempKing);
			if(tempKing.isCheck(temp.getBoard())) {
				moves.remove(i);
				i--;
			}
		}
		*/
		moves=testMoves(moves);
		return moves;
	}
	
	/**
	 * 
	 * @param rowDirection
	 * @param colDirection
	 * @return If the King is put into check from a piece in this direction.
	 */
	public boolean safeFrom(IntFunction<Integer> rowDirection, IntFunction<Integer> colDirection, ChessPiece[][] b){
		//ChessPiece[][] b = board.getBoard();
		int r = this.row;
		int c = this.column;
		while(areValidCoordinates(rowDirection.apply(r), colDirection.apply(c))) {
			r = rowDirection.apply(r);
			c = colDirection.apply(c);
			if(b[r][c] == null) {
				//safe for now, keep going until you find a piece
			}else if(b[r][c].getTeam() == team) {
				//safe, this is your piece
				return true;
			}else {
				//opposing teams piece in line with King
				//make sure it can't attack King
				ChessPiece target=b[r][c];
				ArrayList<int[]> moves = target.getValidMoves();
				for(int i=0; i<moves.size(); i++) {
					if(moves.get(i)[0]==this.row && moves.get(i)[1]==this.column) {
						//the opposing teams piece can attack the King
						return false;
					}
				}
				//went through all moves and it cannot attack the King
				return true;
				
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @return If the King is put into check from a Knight in any direction.
	 */
	public boolean safeFromKnight(ChessPiece[][] b) {
		//ChessPiece[][] b = board.getBoard();
		int r=this.row;
		int c=this.column;
		r=this.row-2;
		c=this.column+1;
		if(areValidCoordinates(r,c)) {
			if(b[r][c] instanceof Knight && b[r][c].getTeam()!=this.team) {
				return false;
			}
		}
		r=this.row-1;
		c=this.column+2;
		if(areValidCoordinates(r,c)) {
			if(b[r][c] instanceof Knight && b[r][c].getTeam()!=this.team) {
				return false;
			}
		}
		r=this.row+1;
		c=this.column+2;
		if(areValidCoordinates(r,c)) {
			if(b[r][c] instanceof Knight && b[r][c].getTeam()!=this.team) {
				return false;
			}
		}
		r=this.row+2;
		c=this.column+1;
		if(areValidCoordinates(r,c)) {
			if(b[r][c] instanceof Knight && b[r][c].getTeam()!=this.team) {
				return false;
			}
		}
		r=this.row+2;
		c=this.column-1;
		if(areValidCoordinates(r,c)) {
			if(b[r][c] instanceof Knight && b[r][c].getTeam()!=this.team) {
				return false;
			}
		}
		r=this.row+1;
		c=this.column-2;
		if(areValidCoordinates(r,c)) {
			if(b[r][c] instanceof Knight && b[r][c].getTeam()!=this.team) {
				return false;
			}
		}
		r=this.row-1;
		c=this.column-2;
		if(areValidCoordinates(r,c)) {
			if(b[r][c] instanceof Knight && b[r][c].getTeam()!=this.team) {
				return false;
			}
		}
		r=this.row-2;
		c=this.column-1;
		if(areValidCoordinates(r,c)) {
			if(b[r][c] instanceof Knight && b[r][c].getTeam()!=this.team) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * @return True if the player's King is in check, false otherwise.
	 */
	public boolean isCheck(ChessPiece[][] b) {
		//safeFrom + all directions
		//safeFromKnight
		//&& all answers and return
		return !(safeFromKnight(b)&&safeFrom(UP,NONE,b)&&safeFrom(UP,RIGHT,b)&&safeFrom(NONE,RIGHT,b)&&safeFrom(DOWN,RIGHT,b)&&safeFrom(DOWN,NONE,b)&&safeFrom(DOWN,LEFT,b)&&safeFrom(NONE,LEFT,b)&&safeFrom(UP,LEFT,b));
	}
	
	/**
	 * 
	 * @return True if the player has lost the game, false otherwise.
	 */
	public boolean isCheckmate(ChessPiece[][] b) {
		//if the King is not in check, it is not checkmate either
		if(!isCheck(b)) {
			return false;
		}
		//See if any of this player's pieces can make a move that will not put the King in check
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(b[i][j]!=null) {
					if(b[i][j].getTeam()==getTeam()) {
						ChessPiece test;
						if(b[this.row][this.column] instanceof Bishop) {
							test=new Bishop(this.row, this.column, this.getTeam());
						}else if(b[this.row][this.column] instanceof King) {
							test=new King(this.row, this.column, this.getTeam());
						}else if(b[this.row][this.column] instanceof Knight) {
							test=new Knight(this.row, this.column, this.getTeam());
						}else if(b[this.row][this.column] instanceof Pawn) {
							test=new Pawn(this.row, this.column, this.getTeam());
						}else if(b[this.row][this.column] instanceof Queen) {
							test=new Queen(this.row, this.column, this.getTeam());
						}else {
							//Rook
							test=new Rook(this.row, this.column, this.getTeam());
						}
						if(test.getValidMoves().size()!=0) {
							return false;
						}
					}
				}
			}
		}
		//there are no possible valid moves, so checkmate
		return true;
	}
	
	/**
	 * Method used to print the piece.
	 */
	public String toString() {
		if(team == WHITE) {
			return "wK";
		}else {
			return "bK";
		}
	}
	
}
