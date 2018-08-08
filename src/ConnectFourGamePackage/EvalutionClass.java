package ConnectFourGamePackage;

public class EvalutionClass {

	private final static int DIRECTION=8; 
	private final static int fx[]={0,0,1,-1,-1,1,-1,1};
	private final static int fy[]={-1,1,0,0,1,1,-1,-1};
	
	
	private final static int fxx[]={0,-1,-1,-1};
	private final static int fyy[]={-1,0,-1,1};
	
	
	
	
	
	private GameBoardClass gameBoard;
	private int currentXCoordinate,currentYCoordinate,count;
	
	public int getTheValueOfEvalutionFunction(GameBoardClass gameBoard) {
		
		this.gameBoard = gameBoard;
		int firstElementRowPosition;
		int value = 0;
	
		for(int i=0;i<gameBoard.getNumberOfCol();i++) {
			
			firstElementRowPosition = gameBoard.getIndexOfFirstElement(i);
			
			//System.out.println("check position "+i+"     "+firstElementRowPosition);
			
			if( firstElementRowPosition != -1 ) {
				value += getTheEvalutingValueOfThePostion(firstElementRowPosition,i) ;
				System.out.println( i+" /////??//// "+getTheEvalutingValueOfThePostion(firstElementRowPosition,i) );
			}
		}
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
		
		
		String evalutingDice = gameBoard.getDice(row, column);
		int value = 0,space,opponent;
		
		for(int i=0;i<=3;i++) {
			
			
			//System.out.println("directional array start  ");
			
			count = 1;
			space = 0;
			opponent = 0;
			currentXCoordinate = column;
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
			
			value += getValue(count, space, opponent);
			
			System.out.println(count+"   "+space+"   "+getValue(count, space,opponent));
		}
		
		if(evalutingDice.equals(gameBoard.getUserDice())) value *= -1;
		
		return value;
		
	}
	
	
	int getValue(int count, int space,int opponent) {
		
		if(opponent ==3 ) return 1000;
		else if(count==3 && space==0 ) return 0;
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
