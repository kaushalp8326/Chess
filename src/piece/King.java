package piece;

import java.util.ArrayList;

public class King extends ChessPiece{
	/**
	 * Constructor for King
	 * @param row
	 * @param column
	 * @param team
	 */
	public King(int row, int column, int team) {
		super(row, column, team);
	}
	
	public boolean move(int newX, int newY) {
		//kings can only move one space
		if((this.row==newX && this.column==newY) || newX<1 || newX>8 || newY<1 || newY>8) {
			return false;
		}
		if(Math.abs(this.row-newX)>1 || Math.abs(this.column-newY)>1) {
			return false;
		}
		this.row=newX;
		this.column=newY;
		return true;
	}

	public ArrayList<int[]> getValidMoves() {
		// TODO Auto-generated method stub
		return null;
	}
}
