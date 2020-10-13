package chess;

public abstract class ChessPiece {
	//row represents rank (1-8)
	protected int row;
	//column represents file (a-h)
	protected int column;
	protected int team;
	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	public int getTeam() {
		return team;
	}
	public abstract boolean move(int initialX, int initialY, int newX, int newY);
	public abstract void capture();
}

class Rook extends ChessPiece{
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
	
	public void capture() {
		this.row=-1;
		this.column=-1;
	}
}