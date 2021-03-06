package ConnectFourGamePackage;

import java.util.ArrayList;

public class EvalutionClass {

	private final static int DIRECTION=8; 
	private final static int fx[]={0,0,1,-1,-1,1,-1,1};
	private final static int fy[]={-1,1,0,0,1,1,-1,-1};
	

	
	
	
	private static int count,space,opponent;
	
	private GameBoardClass gameBoard;
	private int currentXCoordinate,currentYCoordinate;
	
	public int getTheValueOfEvalutionFunction(GameBoardClass gameBoard, ArrayList<PositionClass> nextPly ) {
		
		this.gameBoard = gameBoard;
		int firstElementRowPosition;
		int value = 0;

		
		for(int i=0;i<nextPly.size();i++) {
			value += getTheEvalutingValueOfThePostion(nextPly.get(i).getyAxis(), nextPly.get(i).getxAxis());
		}
		
		return value;
	}

	private int getTheEvalutingValueOfThePostion(int row, int column) {
	
		
		int value = 0;
		
		for(int i=0;i<=3;i++) {
			
			//System.out.println(column+"-----------------"+row);
			
			gameBoard.executeDirectionalCount(column, row, i);
			
			value += getValue(gameBoard.getEvalutingDiceCount()+1, gameBoard.getEmptySpaceCount())  ; 
			
			opponent = gameBoard.getOpponentDiceCount();
			space = gameBoard.getEmptyOpponentSpaceCount();
			
			if (opponent==2 && space>=0) value +=200  ;
			else if(opponent==3 && space >= 0) value+=  500 ;
			
		}
		
		
		if(gameBoard.getDice(row, column).equals(gameBoard.getUserDice())) value *= -1;
		
		return value;
		
	}
	

	
	int getValue(int count, int space) {

		if(count ==3 && space>=1 ) return count*count;
		
		else if(count ==2 && space>=2 ) return count*count;
		
		else if(count ==1 && space>=3 ) return count*count;
		
		else if(count ==4) return 100000;
		
		return 0;
		
		/*
		if(count==3 && space==0 ) return 0;
		else if(count ==3 && space>=2 ) return 80;
		else if(count ==3 && space==1 ) return 50;
		else if(count ==2 && space==0 ) return 0;
		else if(count ==2 && space==2 ) return 30;
		else if(count ==2 && space==1 ) return 5;
		else if(count ==2 && space>=3 ) return 35;
		else if(count ==1 && space>=3 ) return 1;
		else if(count ==4 ) return 10000;
		return 0;*/
	}
	
	
}
