package board;

import piece.*;

/**
 * 
 * @author Kaushal Patel
 * @author John Hoban
 *
 */

/**
 * Wrapper class for the chessboard matrix.
 * @author johnr
 *
 */
public class Board {

	private static ChessPiece[][] board = new ChessPiece[8][8];
	
	/**
	 * Default constructor; does nothing.
	 */
	public Board() {}
	
	/**
	 * Adds a chess piece to the board if its position is available.
	 * @param cp - The chess piece being added.
	 * @return True or False, if the add was successful.
	 */
	public boolean add(ChessPiece cp) {
		int row = cp.getRow();
		int col = cp.getColumn();
		if(board[row][col] != null) {
			return false;
		}
		board[row][col] = cp;
		return true;
	}
	
	/**
	 * Removes a piece from the board via the ChessPiece object.
	 * @param cp
	 * @return
	 */
	public boolean remove(ChessPiece cp) {
		board[cp.getRow()][cp.getColumn()] = null;
		return true;
	}
	
	/**
	 * Removes a piece from the board via a coordinate pair.
	 * @param row
	 * @param col
	 * @return True if a piece is deleted, false if the cell was already empty.
	 */
	public boolean remove(int row, int col) {
		if(board[row][col] == null) {
			return false;
		}
		board[row][col] = null;
		return true;
	}
	
	/**
	 * Gets the current board state.
	 * @return An 8x8 matrix containing the current game state.
	 */
	public ChessPiece[][] getBoard(){
		return board;
	}
	
	public String toString() {
		String s = "";
		for(int row = 7; row >= 0; row--) {
			for(int col = 0; col < 8; col++) {
				if(board[row][col] != null) {
					s += board[row][col].toString() + " ";
				}else if((row + col) % 2 == 0) {
					s += "## ";
				}else {
					s += "   ";
				}
			}
			s += String.valueOf(row+1) + "\n";
		}
		s += " a  b  c  d  e  f  g  h";
		return s;
	}
	
}
