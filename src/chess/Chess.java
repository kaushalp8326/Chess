package chess;

import java.util.*;
import piece.*;
import board.*;

/**
 * Class to be run to start the game.
 * @author Kaushal Patel
 * @author John Hoban
 *
 */
public class Chess {
	/**
	 * Constant to denote the black team's pieces (same as in ChessPiece class).
	 */
	public static final int BLACK = ChessPiece.BLACK;
	/**
	 * Constant to denote the white team's pieces (same as in ChessPiece class).
	 */
	public static final int WHITE = ChessPiece.WHITE;
	/**
	 * Integer value to keep track of which player's turn it is.
	 * Initial value is set to WHITE because white must make the first move.
	 */
	public static int turn=WHITE;
	
	/**
	 * Method to end the game when a player resigns.
	 * @param turn - whoever resigns on their turn loses the game.
	 */
	public static void resign(int turn) {
		if(turn==WHITE) {
			System.out.println("Black wins");
		}else {
			System.out.println("White wins");
		}
		System.exit(0);
	}
		
	/**
	 * Main method to run game.
	 * @param args
	 */
	public static void main(String[] args) {
		Board game=new Board();
		game.setup();
		
		boolean gameEnd=false;
		boolean drawRequest=false;
		Scanner sc=new Scanner(System.in);
		while(!gameEnd) {

			System.out.println(game);
			
			// Before any moves are made, all pawns on this team that were vulnerable to en passant
			// capture are no longer vulnerable, since a complete turn has passed.
			ChessPiece[][] b = game.getBoard();
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					if(b[i][j] instanceof Pawn && b[i][j].getTeam() == turn) {
						Pawn p = (Pawn)b[i][j];
						p.enPassantVulnerable = false;
					}
				}
			}

			boolean executedMove = false;
			do {
				// Read user input
				boolean promotionPotential=false;
				String promotion="";
				if(turn==WHITE) {
					System.out.print("White's move: ");
				}else {
					System.out.print("Black's move: ");
				}
				String move=sc.nextLine();
				if(move.length()==7) {
					promotionPotential=true;
					promotion=move.substring(6);
				}
				if(move.equalsIgnoreCase("resign")) {
					resign(turn);
				}
				if(move.indexOf("draw?")!=-1) {
					move=move.substring(0,move.indexOf(" draw?"));
					drawRequest=true;
				}else if(drawRequest && !move.equalsIgnoreCase("draw")) {
					drawRequest=false;
				}
				if(move.equalsIgnoreCase("draw") && drawRequest) {
					System.exit(0);
				}
				
				drawRequest = false;
				int initialColumn = move.charAt(0) - 'a';
				int initialRow=8-Integer.parseInt(move.substring(1,2));
				int newColumn = move.charAt(3) - 'a';
				int newRow=8-Integer.parseInt(move.substring(4));
				
				// Execute move, if legal
				ChessPiece p = game.getBoard()[initialRow][initialColumn];
				if(p instanceof Pawn && promotionPotential) {
					Pawn pwn = (Pawn)p;
					executedMove = (pwn != null && pwn.getTeam() == turn && pwn.move(newColumn, newRow, promotion));
				}else {
					executedMove = (p != null && p.getTeam() == turn && p.move(newColumn, newRow));
				}
				
				// Error message for illegal move
				if(!executedMove) {
					System.out.println("Illegal move, try again");
				}
				
			}while(!executedMove);
			
			if(turn==WHITE) {
				turn=BLACK;
			}else {
				turn=WHITE;
			}
			
			ChessPiece[][] temp=new ChessPiece[8][8];
			for(int i=0; i<8; i++) {
				for(int j=0; j<8; j++) {
					temp[i][j]=b[i][j];
				}
			}
			//find the King
			int kingRow=0;
			int kingColumn=0;
			for(int i=0; i<8; i++) {
				for(int j=0; j<8; j++) {
					if(temp[i][j]!=null) {
						if(temp[i][j] instanceof King && temp[i][j].getTeam()==turn) {
							kingRow=i;
							kingColumn=j;
							i=8;
							j=8;
							break;
						}
					}
				}
			}
			//TODO bug after here for input g5 e6
			if(((King)(temp[kingRow][kingColumn])).isCheck(temp)) {
				if(((King)(temp[kingRow][kingColumn])).isCheckmate(temp)) {
					System.out.println("Checkmate");
					gameEnd=true;
					resign(turn);
				}else {
					System.out.println("Check");
				}
			}

			System.out.println();
		}
		sc.close();
	}
	
}
