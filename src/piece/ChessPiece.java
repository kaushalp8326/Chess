package piece;

import java.util.ArrayList;
import board.Board;

/**
 * 
 * @author Kaushal Patel
 * @author John Hoban
 *
 */

public abstract class ChessPiece {
	/**
	 * This is an abstract class for all chess pieces.
	 * The class includes variables for the pieces row, column, and team designation.
	 * The class includes the methods getRow, getColumn, and getTeam to get the pieces row, column, and team designation respectively.
	 * Additionally, the capture method changes the row and column fields to -1 to denote a piece has been captured.
	 * There is a single abstract method named move, which will be defined in each subclass depending on how each piece is able to move on the board.
	 */
	//row represents rank (1-8)
	protected int row;
	//column represents file (a-h)
	protected int column;
	protected int team;
	
	/**
	 * Internal reference to the single Board object that represents the game.
	 * Useful for methods involving moving.
	 */
	protected Board board = new Board();
	
	/**
	 * Generic constructor that will be called whenever a new piece of any kind is made.
	 * @param row
	 * @param column
	 * @param team
	 */
	public ChessPiece(int row, int column, int team) {
		this.row = row;
		this.column = column;
		this.team = team;
	}
	
	/**
	 * @return Returns the row/rank of the piece.
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * @return Returns the column/file of the piece
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * @return Returns which player the piece belongs to.
	 */
	public int getTeam() {
		return team;
	}
	
	/**
	 * This method denotes the piece as being captured by setting its row/rank and column/file both to -1.
	 */
	public void capture() {
		this.row=-1;
		this.column=-1;
	}
	
	/**
	 * Checks if a row-column pair falls within the bounds of the chessboard.
	 * @param row
	 * @param col
	 * @return True if the coordinates fall inside an 8x8 board, false otherwise.
	 */
	public static boolean areValidCoordinates(int row, int col) {
		return (0 <= row && row <= 7) && (0 <= col && col <= 7);
	}
	
	/**
	 * Abstract method to be implemented by each subclass to define the movement of a certain type of piece.
	 * @param newX
	 * @param newY
	 * @return Returns a boolean value based on if the user has made a valid move
	 */
	public abstract boolean move(int newX, int newY);

	/**
	 * Returns the set of valid moves available to a piece.
	 * @return Returns an ArrayList of coordinate pairs, each representing a valid move.
	 */
	public abstract ArrayList<int[]> getValidMoves();
}
