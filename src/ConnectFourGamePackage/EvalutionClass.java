package ConnectFourGamePackage;

import java.util.ArrayList;

public class EvalutionClass {

	private final static int DIRECTION=8; 
	private final static int fx[]={0,0,1,-1,-1,1,-1,1};
	private final static int fy[]={-1,1,0,0,1,1,-1,-1};
	
	
	private final static int fxx[]={0,-1,-1,-1};
	private final static int fyy[]={-1,0,-1,1};
	
	
	
	private static int count,space,opponent;
	
	private GameBoardClass gameBoard;
	private int currentXCoordinate,currentYCoordinate;
	
	public int getTheValueOfEvalutionFunction(GameBoardClass gameBoard, ArrayList<PositionClass> nextPly ) {
		
		this.gameBoard = gameBoard;
		int firstElementRowPosition;
		int value = 0;
		
		
		for(int i=0;i<nextPly.size();i++) {
			
			//gameBoard.printFinalGameBoard();
			value += getTheEvalutingValueOfThePostion(nextPly.get(i).getyAxis(), nextPly.get(i).getxAxis());
			System.out.println(nextPly.get(i).getxAxis()+"   hhhhhhhhhhhhhhhhhhhhhhhhhh   "+value );
		}
	
		/*for(int i=0;i<gameBoard.getNumberOfCol();i++) {
			
			firstElementRowPosition = gameBoard.getIndexOfFirstElement(i);
			
			//System.out.println("check position "+i+"     "+firstElementRowPosition);
			
			if( firstElementRowPosition != -1 ) {
				value += getTheEvalutingValueOfThePostion(firstElementRowPosition,i) ;
				System.out.println( i+" /////??//// "+getTheEvalutingValueOfThePostion(firstElementRowPosition,i) );
			}
		}*/
		//System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhh   "+value );
		return value;
	}
	
	private int getHuristicValue(int count) {
		
		if(count==2) return 5;
		else if(count==3) return 100;
		else if(count==4) return 10000;
		
		return 0;
		
	}
	

	private int getTheEvalutingValueOfThePostion(int row, int column) {
		/*
		String evalutingDice = gameBoard.getDice(row, column);
		int value = 0;
		
		for(int i=0;i<DIRECTION;i++) {
			
			count = 1;
			currentXCoordinate = column;
			currentYCoordinate = row;
			
			for(int j=0;j<3;j++) {
				
				currentXCoordinate += fx[i];
				currentYCoordinate += fy[i];
				
				if( currentXCoordinate < 0 || currentYCoordinate <0 
						|| currentXCoordinate> (gameBoard.getNumberOfCol()-1) || currentYCoordinate > (gameBoard.getNumberOfRow()-1)  ) break;
				
				else if(gameBoard.getDice(currentYCoordinate, currentXCoordinate).equals( "." )) continue;
				
				else if( evalutingDice.equals( gameBoard.getDice(currentYCoordinate, currentXCoordinate) ) ) count++;
				
				else if( ! evalutingDice.equals( gameBoard.getDice(currentYCoordinate, currentXCoordinate) ) ) break;

			}
			
			value += getHuristicValue(count);
		}
		
		if(evalutingDice.equals(gameBoard.getUserDice())) value *= -1;
		
		return value;*/
		
		
		int value = 0;
		

		for(int i=0;i<=3;i++) {
			
			
			//System.out.println("directional array start  ");
			
			count = 1;
			space = 0;
			opponent = 0;
			
			
			getDirectionalCount( column, row, 1, i);
			getDirectionalCount( column, row, -1, i);
			
			/*currentXCoordinate = column;
			currentYCoordinate = row;
			
			
			for(int j=0;j<3;j++) {
				
				currentXCoordinate += fxx[i];
				currentYCoordinate += fyy[i];
				
				if( currentXCoordinate < 0 || currentYCoordinate <0 
						|| currentXCoordinate> (gameBoard.getNumberOfCol()-1) || currentYCoordinate > (gameBoard.getNumberOfRow()-1)  ) break;
				
				else if(gameBoard.getDice(currentYCoordinate, currentXCoordinate).equals( "." )) space++;
				
				else if( evalutingDice.equals( gameBoard.getDice(currentYCoordinate, currentXCoordinate) ) ) count++;
				
				else if( ! evalutingDice.equals( gameBoard.getDice(currentYCoordinate, currentXCoordinate) ) ) break;
			
			}
			
			currentXCoordinate = column;
			currentYCoordinate = row;
			
			for(int j=0;j<3;j++) {
				
				currentXCoordinate += fxx[i]*-1;
				currentYCoordinate += fyy[i]*-1;
				
				if( currentXCoordinate < 0 || currentYCoordinate <0 
						|| currentXCoordinate> (gameBoard.getNumberOfCol()-1) || currentYCoordinate > (gameBoard.getNumberOfRow()-1)  ) break;
				
				else if(gameBoard.getDice(currentYCoordinate, currentXCoordinate).equals( "." )) space++;
				
				else if( evalutingDice.equals( gameBoard.getDice(currentYCoordinate, currentXCoordinate) ) ) count++;
				
				else if( ! evalutingDice.equals( gameBoard.getDice(currentYCoordinate, currentXCoordinate) ) ) break;
			
			}
			*/
			value += getValue(count, space);
			
			space = 0;
			getDefenciveReward( column, row, 1, i);
			getDefenciveReward( column, row, -1, i);
			
			if (opponent==2 && space>=1) value += 1000;
			else if(opponent==3 && space >= 0) value+= 1000;
			
			//System.out.println("/////////////////////////   "+  opponent);
		}
		
		if(gameBoard.getDice(row, column).equals(gameBoard.getUserDice())) value *= -1;
		
		return value;
		
	}
	
	
	private void getDefenciveReward(int column,int row,int direction,int i) {
		
		String evalutingDice = gameBoard.getDice(row, column);
		currentXCoordinate = column;
		currentYCoordinate = row;
		
		for(int j=0;j<3;j++) {
			
			//System.out.println("/4444444444444444444444444444      "+  opponent);
			
			currentXCoordinate += fxx[i]*direction;
			currentYCoordinate += fyy[i]*direction;
			
			if( currentXCoordinate < 0 || currentYCoordinate <0 
					|| currentXCoordinate> (gameBoard.getNumberOfCol()-1) || currentYCoordinate > (gameBoard.getNumberOfRow()-1)  ) break;
			
			else if(gameBoard.getDice(currentYCoordinate, currentXCoordinate).equals( "." )) space++;
			
			else if( evalutingDice.equals( gameBoard.getDice(currentYCoordinate, currentXCoordinate) ) ) break;
			
			else if( ! evalutingDice.equals( gameBoard.getDice(currentYCoordinate, currentXCoordinate) ) ) opponent++;
		
		}
	}
	
	
	private void getDirectionalCount(int column,int row,int direction,int i) {
		

		String evalutingDice = gameBoard.getDice(row, column);
		currentXCoordinate = column;
		currentYCoordinate = row;
		
		for(int j=0;j<3;j++) {
			
			currentXCoordinate += fxx[i]*direction;
			currentYCoordinate += fyy[i]*direction;
			
			if( currentXCoordinate < 0 || currentYCoordinate <0 
					|| currentXCoordinate> (gameBoard.getNumberOfCol()-1) || currentYCoordinate > (gameBoard.getNumberOfRow()-1)  ) break;
			
			else if(gameBoard.getDice(currentYCoordinate, currentXCoordinate).equals( "." )) space++;
			
			else if( evalutingDice.equals( gameBoard.getDice(currentYCoordinate, currentXCoordinate) ) ) count++;
			
			else if( ! evalutingDice.equals( gameBoard.getDice(currentYCoordinate, currentXCoordinate) ) ) break;
		
		}
		
	}
	
	
	
	int getValue(int count, int space) {
		
		
		if(count==3 && space==0 ) return 0;
		else if(count ==3 && space>=2 ) return 80;
		else if(count ==3 && space==1 ) return 50;
		else if(count ==2 && space==0 ) return 0;
		else if(count ==2 && space==2 ) return 30;
		else if(count ==2 && space==1 ) return 5;
		else if(count ==2 && space>=3 ) return 35;
		else if(count ==1 && space>=3 ) return 1;
		else if(count ==4 ) return 10000;
		return 0;
	}
	
	
}
