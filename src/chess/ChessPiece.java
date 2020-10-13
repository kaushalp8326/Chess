package chess;

/**
 * 
 * @author Kaushal Patel
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
	 * Abstract method to be implemented by each subclass to define the movement of a certain type of piece.
	 * @param initialX
	 * @param initialY
	 * @param newX
	 * @param newY
	 * @return Returns a boolean value based on if the user has made a valid move
	 */
	public abstract boolean move(int initialX, int initialY, int newX, int newY);
}

class Rook extends ChessPiece{
	/**
	 * Constructor for Rook
	 * @param row
	 * @param column
	 * @param team
	 */
	public Rook(int row, int column, int team) {
		this.row=row;
		this.column=column;
		this.team=team;
	}
	
	public boolean move(int initialX, int initialY, int newX, int newY) {
		//rooks can only move in a straight line horizontally or vertically
		if(initialX!=newX && initialY!=newY) {
			return false;
		}
		if(initialY==newY && (initialX==newX || newX<1 || newX>8)) {
			return false;
		}
		if(initialX==newX && (initialY==newY || newY<1 || newY>8)) {
			return false;
		}
		this.row=newX;
		this.column=newY;
		return true;
	}
}