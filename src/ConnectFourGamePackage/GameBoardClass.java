package ConnectFourGamePackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameBoardClass {
	
	private ArrayList< ArrayList< String > > gameBoardArray  = new ArrayList<>();	
	private final static int NUMBER_OF_ROW=6;
	private final static int NUMBER_OF_COL=7;
	private final static String EMPTY_BOARD_VALUE = ".";
	private final static String PC_DICE = "O";
	private final static String USER_DICE = "X";
	private int currentXCoordinate,currentYCoordinate;
	
	private int emptySpaceCount,emptyOpponentSpaceCount,evalutingDiceCount,opponentDiceCount;
	
	
	private final static int fxx[]={0,-1,-1,-1};
	private final static int fyy[]={-1,0,-1,1};
	
	
	public static int getNumberOfRow() {
		return NUMBER_OF_ROW;
	}

	public static int getNumberOfCol() {
		return NUMBER_OF_COL;
	}

	public static String getEmptyBoardValue() {
		return EMPTY_BOARD_VALUE;
	}

	public static String getPcDice() {
		return PC_DICE;
	}

	public static String getUserDice() {
		return USER_DICE;
	}

	public String getDice(int row,int column) {
		return this.gameBoardArray.get(row).get(column);
	}
	
	public void setDice(int row,int column,String dice) {
		this.gameBoardArray.get(row).set(column, dice);
		return;
	}
	
	public int getEmptySpaceCount() {
		return emptySpaceCount;
	}

	public int getEmptyOpponentSpaceCount() {
		return emptyOpponentSpaceCount;
	}

	public int getEvalutingDiceCount() {
		return evalutingDiceCount;
	}

	public int getOpponentDiceCount() {
		return opponentDiceCount;
	}
	
	
	public GameBoardClass getACopyOfGameBoardClass() {
		
		GameBoardClass copyGameBoardClass = new GameBoardClass();
		
		ArrayList< ArrayList< String > > newGameBoardArray  = new ArrayList<>();	
		
		for(int i=0;i<NUMBER_OF_ROW;i++) {
			
			ArrayList<String> temp = new ArrayList<>();
			
			for(int j=0;j<NUMBER_OF_COL;j++) temp.add(this.gameBoardArray.get(i).get(j));
			
			newGameBoardArray.add(temp);
			
		}
		
		copyGameBoardClass.gameBoardArray = newGameBoardArray;
		
		
		return copyGameBoardClass;
	}
	
	
	public void createNewGameBoard() {
		
		for(int i=0;i<NUMBER_OF_ROW;i++) {
			
			ArrayList<String> tempArray = new ArrayList<>();
			
			for(int j=0;j<NUMBER_OF_COL;j++) tempArray.add(EMPTY_BOARD_VALUE);
			
			gameBoardArray.add(tempArray);
			
		}
		
		
/*		
		try {
			BufferedReader br = new BufferedReader(new FileReader("input.txt"));
			String line;
			
			
			while ((line = br.readLine()) != null) {
			
				String arr[] = line.split(" ");
				ArrayList<String> temp = new ArrayList<>();
				
				for(int i=0;i<arr.length;i++) temp.add(arr[i]);
				
				
				gameBoardArray.add(temp);
				
				//System.out.println(board.toString());
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}
	
	public int getIndexOfFirstElement(int columnNumber) {
		
		int index = -1;
		
		for(int i=0;i<NUMBER_OF_ROW;i++) {
			
			if( !gameBoardArray.get(i).get(columnNumber).equals(EMPTY_BOARD_VALUE)) {
				index = i;
				break;
			}
			
		}
		
		return index;
	}

	public int getEmptyIndexOfAColumn(int columnNumber) {
		
		int index=-1;
		
		for(int i=0;i<NUMBER_OF_ROW;i++) {
			
			if(gameBoardArray.get(i).get(columnNumber).equals(EMPTY_BOARD_VALUE)) index = i;
			else break;
		}
		
		return index;
	}
	
	
	
	private boolean outOfBoundCheck(int currentXCoordinate,int currentYCoordinate) {
		
		return ( currentXCoordinate < 0 || currentYCoordinate <0 
				|| currentXCoordinate> (getNumberOfCol()-1) || currentYCoordinate > (getNumberOfRow()-1)  );
	}
	
	private boolean isEmpty(int currentXCoordinate,int currentYCoordinate) {
		
		return getDice(currentYCoordinate, currentXCoordinate).equals( EMPTY_BOARD_VALUE );
	}
	
	
	private void attackingDirectionalCount(int currentXCoordinate,int currentYCoordinate,int direction,int i) {
		

		String evalutingDice = getDice(currentYCoordinate, currentXCoordinate);
		
		
		for(int j=0;j<3;j++) {
			
			currentXCoordinate += fxx[i]*direction;
			currentYCoordinate += fyy[i]*direction;
			
			if(  outOfBoundCheck(currentXCoordinate, currentYCoordinate)  ) break;
			
			else if( isEmpty(currentXCoordinate, currentYCoordinate) ) emptySpaceCount++;
			
			else if( evalutingDice.equals( getDice(currentYCoordinate, currentXCoordinate) ) ) evalutingDiceCount++;
			
			else if( ! evalutingDice.equals( getDice(currentYCoordinate, currentXCoordinate) ) ) break;
		
		}
		
	}
	
	
	
	private void winningDirectionalCount(int currentXCoordinate,int currentYCoordinate,int direction,int i) {
		

		String evalutingDice = getDice(currentYCoordinate, currentXCoordinate);
		
		
		for(int j=0;j<3;j++) {
			
			currentXCoordinate += fxx[i]*direction;
			currentYCoordinate += fyy[i]*direction;
			
			if(  outOfBoundCheck(currentXCoordinate, currentYCoordinate)  ) break;
			
			else if( isEmpty(currentXCoordinate, currentYCoordinate) ) break;
			
			else if( evalutingDice.equals( getDice(currentYCoordinate, currentXCoordinate) ) ) evalutingDiceCount++;
			
			else if( ! evalutingDice.equals( getDice(currentYCoordinate, currentXCoordinate) ) ) break;
		
		}
		
	}
	
	
	private void defenciveDirectionCount(int currentXCoordinate,int currentYCoordinate,int direction,int i) {
		
		String evalutingDice = getDice(currentYCoordinate, currentXCoordinate);
		
		for(int j=0;j<3;j++) {
			
			currentXCoordinate += fxx[i]*direction;
			currentYCoordinate += fyy[i]*direction;
			
			if( outOfBoundCheck(currentXCoordinate, currentYCoordinate)  ) break;
			
			else if( isEmpty(currentXCoordinate, currentYCoordinate)) break;//emptyOpponentSpaceCount++;
			
			else if( evalutingDice.equals( getDice(currentYCoordinate, currentXCoordinate) ) ) break;
			
			else if( ! evalutingDice.equals( getDice(currentYCoordinate, currentXCoordinate) ) ) opponentDiceCount++;
		
		}
	}
	
	
	public boolean winCheck() {
		
		for(int i=0;i<NUMBER_OF_COL;i++) {
			
			int row = getIndexOfFirstElement(i);
			
			if(row!=-1) {

				for(int j=0;j<4;j++) {
					
					initializeProparty();
					
					winningDirectionalCount(i, row, 1, j);
					winningDirectionalCount(i, row, -1, j);
					
					
					if(getEvalutingDiceCount()>=3 ) return true;
					
				}	
			}
			
			//System.out.println(getEvalutingDiceCount());
		}
		return false;
		
	}
	
	
	public void executeDirectionalCount( int column,int row ,int i ) {
		
		initializeProparty();
			
		attackingDirectionalCount(column, row, 1, i);
		
		//System.out.println(column+"-----------------"+row);
		attackingDirectionalCount(column, row, -1, i);
		
		//System.out.println(column+"-----------------"+row);
		defenciveDirectionCount(column, row, 1, i);
		//System.out.println(column+"-----------------"+row);
		
		defenciveDirectionCount(column, row, -1, i);
		//System.out.println(column+"-----------------"+row);
		
		
	}
	
	


	private void initializeProparty() {
		this.emptySpaceCount = 0;
		this.emptyOpponentSpaceCount = 0;
		this.evalutingDiceCount = 0;
		this.opponentDiceCount = 0;
	}

	public void printGameBoard() {
		
		for(int j=0;j<NUMBER_OF_COL;j++)  System.out.print(j+"\t" );

		System.out.println();
		
		for(int i=0;i<NUMBER_OF_ROW;i++) {
			
			for(int j=0;j<NUMBER_OF_COL;j++)  System.out.print( gameBoardArray.get(i).get(j)+"\t" );
			
			System.out.println();
			
		}
		
	}
	
	
	
	public void printFinalGameBoard() {
		
		System.out.print( " " );
		for(int j=0;j<NUMBER_OF_COL;j++)  System.out.print("  "+j+"   " );
		System.out.println();
		
		for(int i=0;i<NUMBER_OF_ROW;i++) {
			
			System.out.print( "|" );
			for(int j=0;j<NUMBER_OF_COL;j++)  System.out.print( "     "+"|" );
			System.out.println();
			
			System.out.print( "|" );
			for(int j=0;j<NUMBER_OF_COL;j++)  System.out.print( "  "+gameBoardArray.get(i).get(j)+"  "+"|" );
			System.out.println();
			
			System.out.print( "|" );
			for(int j=0;j<NUMBER_OF_COL;j++)  System.out.print( "_____"+"|" );
			System.out.println();
			
			
		}
	}
	

}
