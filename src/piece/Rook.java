package piece;

import java.util.ArrayList;

public class Rook extends ChessPiece{
	/**
	 * Constructor for Rook
	 * @param row
	 * @param column
	 * @param team
	 */
	public Rook(int row, int column, int team) {
		super(row, column, team);
	}
	
	public boolean move(int newX, int newY) {
		//rooks can only move in a straight line horizontally or vertically
		if(column!=newX && column!=newY) {
			return false;
		}
		if(row==newY && (row==newX || newX<1 || newX>8)) {
			return false;
		}
		if(column==newX && (row==newY || newY<1 || newY>8)) {
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
			return "wR";
		}else {
			return "bR";
		}
	}
	
}