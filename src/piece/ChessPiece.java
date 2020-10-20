package piece;

import java.util.ArrayList;
import java.util.function.IntFunction;

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
	 * Constants for a piece's team.
	 */
	public static final int WHITE = 0;
	public static final int BLACK = 1;
	
	/**
	 * Internal reference to the single Board object that represents the game.
	 * Useful for methods involving moving.
	 */
	protected Board board = new Board();
	
	/**
	 * Directional moveset iterators. See documentation for getMovementLine().
	 */
	protected static final IntFunction<Integer> UP = r -> ++r;
	protected static final IntFunction<Integer> DOWN = r -> --r;
	protected static final IntFunction<Integer> LEFT = c -> --c;
	protected static final IntFunction<Integer> RIGHT = c -> ++c;
	protected static final IntFunction<Integer> NONE = i -> i;
	
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
	 * Default method for moving a piece based on a piece's unique way of generating a moveset.
	 * !! IMPORTANT: THIS MUST BE OVERRIDEN FOR PAWN AND KING SINCE THEY HAVE EXTRA PROPERTIES TO CHANGE AFTER A MOVE !!
	 * @param newX
	 * @param newY
	 * @return Returns a boolean value based on if the user has made a valid move
	 */
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
		
		// Move the piece
		this.board.remove(this);
		this.row = newY;
		this.column = newX;
		this.board.add(this);
		
		return true;
	}
	
	/**
	 * Iterate through one possible direction, or "line", of movement, starting from the piece's origin.
	 * Keeps adding valid moves to the movement line until another piece or the edge of the board is encountered.
	 * For use with pieces with movement "speed" limit only by the board: Rook, Bishop, Queen.
	 * @param rowDirection - IntFunction to represent row traversal. r -> r-- for moving left, r -> r++ for moving right.
	 * @param colDirection - IntFunction to represent column traversal. c -> c-- for moving down, c -> c++ for moving up.
	 * @return An ArrayList containing valid moveset coordinates. These should be combined with other directional iterations to generate the full moveset.
	 */
	public ArrayList<int[]> getMovementLine(IntFunction<Integer> rowDirection, IntFunction<Integer> colDirection){
		ArrayList<int[]> moves = new ArrayList<int[]>();
		ChessPiece[][] b = board.getBoard();
		int r = row; int c = column;
		while(areValidCoordinates(rowDirection.apply(r), colDirection.apply(c))) {
			r = rowDirection.apply(r);
			c = colDirection.apply(c);
			if(b[r][c] == null) {
				moves.add(new int[] {r,c});
			}else if(b[r][c].getTeam() != team) {
				moves.add(new int[] {r,c});
				break;
			}else {
				break;
			}
		}
		return moves;
	}

	/**
	 * Returns the set of valid moves available to a piece.
	 * @return Returns an ArrayList of coordinate pairs, each representing a valid move.
	 */
	public abstract ArrayList<int[]> getValidMoves();
}
