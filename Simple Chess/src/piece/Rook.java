package piece;

import main.GamePanel;
import main.Type;

public class Rook extends Piece{

	public Rook(int color, int col, int row) {
		super(color, col, row);
		
		type = Type.ROOK;
		
		if(color == GamePanel.WHITE) {
			image = getImage("/piece/wrook");
		}
		else {
			image = getImage("/piece/brook");
		}
	}
	public boolean canMove(int targetCol, int targetRow) {
		
		if(isWithinBoard(targetCol,targetRow) && isSameSquare(targetCol,targetRow) == false) {
			//Rook can move in a straight line in each direction unless there is an allied piece there
			if(targetCol == preCol || targetRow == preRow) {
				if(isValidSquare(targetCol,targetRow) && pieceIsOnStraightLine(targetCol,targetRow) == false) {
					return true;
				}
				
			}
		}
		return false;
	}
	
}