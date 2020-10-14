package piece;

import java.util.ArrayList;

public class Queen extends ChessPiece{
	/**
	 * Constructor for Queen
	 * @param row
	 * @param column
	 * @param team
	 */
	public Queen(int row, int column, int team) {
		super(row, column, team);
	}
	
	public boolean move(int newX, int newY) {
		//queens can move in a straight line or diagonally		
		if(!((this.row!=newX && this.column!=newY) && Math.abs(this.row-newX)!=Math.abs(this.column-newY)) || (this.row==newX && this.column==newY) || newX<1 || newX>8 || newY<1 || newY>8) {
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
	
	public String toString() {
		if(team == WHITE) {
			return "wQ";
		}else {
			return "bQ";
		}
	}
	
}
