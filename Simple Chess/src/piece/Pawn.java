package piece;

import java.util.ArrayList;
import java.util.List;

import main.GamePanel;
import main.Type;

public class Pawn extends Piece{

	public Pawn(int color, int col, int row) {
		super(color, col, row);
		
		type = Type.PAWN;
		
		if(color == GamePanel.WHITE) {
			image = getImage("/piece/wpawn");
		}
		else {
			image = getImage("/piece/bpawn");
		}
	}
	public boolean canMove(int targetCol, int targetRow) {
		
		if(isWithinBoard(targetCol,targetRow) && isSameSquare(targetCol,targetRow) == false) {
		//Define the move value based on color
			int moveValue;
			if(color == GamePanel.WHITE) {
				moveValue = -1;
			}
			else {
				moveValue = 1;
			}
			
			//Check the piece you hit
			
			hittingP = getHittingP(targetCol,targetRow);
			
			// default movement 1 square
			if(targetCol == preCol && targetRow == preRow + moveValue && hittingP == null) {
				return true;
			}
			
			// first movement 2 squares
			if(targetCol == preCol && targetRow == preRow + moveValue*2 && hittingP == null && moved == false &&
					pieceIsOnStraightLine(targetCol,targetRow) == false) {
				return true;
			}
			
			// Diagonal movement on capturing a piece
			if(Math.abs(targetCol - preCol) == 1 && targetRow == preRow + moveValue && hittingP != null &&
					hittingP.color != color) {
				return true;
			}
			  List<Piece> piecesCopy = new ArrayList<>(GamePanel.simPieces);
			  for (Piece piece : piecesCopy) {
				  if (hittingP != null) {
					    if(Math.abs(targetCol - preCol) == 1 && targetRow == preRow + moveValue && hittingP.color != color) {
					        return true;
					    }
					}

			  }
			
			//En Passant
			  if (Math.abs(targetCol - preCol) == 1 && targetRow == preRow + moveValue) {
				    for (Piece piece : GamePanel.simPieces) {
				      if (piece.col == targetCol && piece.row == preRow && piece.twoStepped) {
				        hittingP = piece; // Store the captured pawn for En Passant
				        return true;
				      }
				    }
				  }	
		}
		return false;
	}
}
