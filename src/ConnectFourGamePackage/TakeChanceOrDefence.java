package ConnectFourGamePackage;

public class TakeChanceOrDefence {

	private GameBoardClass gameBoard;
	//private final static int fx[]={  0,  0,  1, -1, -1,  1, -1,  1};
	//private final static int fy[]={ -1,  1,  0,  0,  1,  1, -1, -1};
	
	
	private final static int fx[]={-1,  0,  1, -1,  1, -1,  0,  1};
	private final static int fy[]={-1, -1, -1,  0,  0,  1,  1,  1};
	
	
	
	public TakeChanceOrDefence(GameBoardClass gameBoard) {
		this.gameBoard = gameBoard; 
	}
	
	
	public boolean shouldTakeChanceOrDefence(int col, String dice) {
		
		
		int row = gameBoard.getEmptyIndexOfAColumn(col);
		
		//System.out.println(row);
		/*
			System.out.println("--------------");		
			System.out.println(col);
			System.out.println("--------------");
		*/	
		
		for(int i = 0; i < 8; i++) {
			//int numberOfOpponent = 0;
			int tempRow = row;
			int tempCol = col; 
			
			
			int toX = fx[i];
			int toY = fy[i];
			
			int opponent = 0;
			for(int j = 0; j < 3; j++) {
				tempCol += toX;
				tempRow += toY;
				
				opponent += checkMatch(tempRow, tempCol, dice);
			}
			if(opponent == 3) return true;
			
			//System.out.println(i + " "+ opponent); 
		}
		
		for(int i = 0; i < 8; i++) {
			int tempRow = row;
			int tempCol = col;
			int toX = fx[i];
			int toY = fy[i];
			int opponent = 0;
			
			for(int j = 0; j < 2; j++) {
				tempCol += toX;
				tempRow += toY;
				opponent += checkMatch(tempRow, tempCol, dice);
			}
			
			
			
			int k = 8 - (i + 1);
			tempRow = row + fy[k];
			tempCol = col + fx[k];
			opponent += checkMatch(tempRow, tempCol, dice);
			if(opponent == 3) return true;
			/*if(i == 4) {
				System.out.println(row + " "+ col);
				System.out.println(tempRow + " " + tempCol);
				System.out.println(opponent);
				
				
			}*/	
			//System.out.println(i +"  ... " + opponent);
			/*if(i == 4)
			System.out.println(tempRow + " >> "+tempCol);
			
			System.out.println(i +"  ... " + opponent);
			*/
			
		}
		
		return false;
	}
	
	
public boolean shouldTakeChanceOrDefence2(int col, String dice) {
		
		
		int row = gameBoard.getEmptyIndexOfAColumn(col);
		
		for(int i = 0; i < 8; i++) {
			//int numberOfOpponent = 0;
			int tempRow = row;
			int tempCol = col; 
			
			
			int toX = fx[i];
			int toY = fy[i];
			
			int opponent = 0;
			for(int j = 0; j < 2; j++) {
				tempCol += toX;
				tempRow += toY;
				
				opponent += checkMatch(tempRow, tempCol, dice);
			}
			if(opponent == 2) return true;
			
			//System.out.println(i + " "+ opponent); 
		}
		
		for(int i = 0; i < 8; i++) {
			int tempRow = row;
			int tempCol = col;
			int toX = fx[i];
			int toY = fy[i];
			int opponent = 0;
			
			for(int j = 0; j < 1; j++) {
				tempCol += toX;
				tempRow += toY;
				opponent += checkMatch(tempRow, tempCol, dice);
			}
			
			
			
			int k = 8 - (i + 1);
			tempRow = row + fy[k];
			tempCol = col + fx[k];
			opponent += checkMatch(tempRow, tempCol, dice);
			if(opponent == 3) return true;
		}
		
		return false;
	}



	private int checkMatch(int row, int col, String dice) {
		if(row > 5 || row < 0 || col > 6 || col < 0) return 0;
		if(dice.equals(gameBoard.getDice(row, col))) return 1; 
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}