package piece;

import main.GamePanel;
import main.Type;

public class Knight extends Piece{

	public Knight(int color, int col, int row) {
		super(color, col, row);
		
		type= Type.KNIGHT;
		
		if(color == GamePanel.WHITE) {
			image = getImage("/piece/wknight");
		}
		else {
			image = getImage("/piece/bknight");
		}
	}
	public boolean canMove(int targetCol, int targetRow) {
		
		if(isWithinBoard(targetCol,targetRow)) {
			//Knight can move in a L shape 2:1 or 1:1
			if(Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 2) {
				if(isValidSquare(targetCol,targetRow)) {
					return true;
				}
			}
		}
		return false;
	}
	
}