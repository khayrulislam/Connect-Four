package ConnectFourGamePackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class MiniMaxClass {

	
	//private final static int DEPTH_LIMIT=5;
	
	int DEPTH_LIMIT;
	
	public int getNextMove(GameBoardClass gameBoard,int depth) 
	{
		ArrayList<PairClass> pairList = new ArrayList<>();
		
		DEPTH_LIMIT = depth;
	
		for(int i=0;i<gameBoard.getNumberOfCol();i++) {
			
			int emptyIndex = gameBoard.getEmptyIndexOfAColumn(i);
			
			if(emptyIndex != -1) {
				
				GameBoardClass newGameBoard = gameBoard.getACopyOfGameBoardClass();
				
				newGameBoard.setDice(emptyIndex, i, newGameBoard.getPcDice());
				
				int value = createAndTraverseTree(newGameBoard,1);
				
				pairList.add(new PairClass(i, value));
				
				System.out.println(i+"   ----------------    "+value);
			}
			
		}
		
		
		return getColumnNumber(pairList);
	}

	private int getColumnNumber(ArrayList<PairClass> pairList) {
		// TODO Auto-generated method stub
		Collections.sort(pairList);
		
		int value = pairList.get( pairList.size() -1 ).value;
		int check;
		
		int index;
		ArrayList< PairClass > maxArr = new ArrayList<>();
		
		for(int i=pairList.size()-1;i>=0;i--) {
			
			if(pairList.get(i).value ==  value) maxArr.add(pairList.get(i));
			else break;
		}
		
		if(maxArr.size()>1) {
			Random r= new Random();
			int rand = r.nextInt(maxArr.size());
			index = maxArr.get(rand).column;
			check = maxArr.get(rand).value;
		}
		
		else {
			index = maxArr.get(0).column;
			check = maxArr.get(0).value;
		}
		System.out.println("The value is "+ check+" for column "+index);
		return index;
	}
	
	
	

	

	private int createAndTraverseTree(GameBoardClass currentGameBoard, int depth) {
		
		int currenteEmptyIndex;
		
		//System.out.println(depth+"          888888888888888888888888888888888888888888888888888888888888888888");
		//currentGameBoard.printGameBoard();
		
		if(depth==DEPTH_LIMIT) {
			
			
			//currentGameBoard.printFinalGameBoard();
			EvalutionClass ec = new EvalutionClass();
			
			return ec.getTheValueOfEvalutionFunction(currentGameBoard);
			
		}
		
		else{
			
			ArrayList<Integer> valueList = new ArrayList<>();
			
			for(int i=0;i<currentGameBoard.getNumberOfCol();i++) {
				
				currenteEmptyIndex = currentGameBoard.getEmptyIndexOfAColumn(i);
				
				if(currenteEmptyIndex != -1 ) {
					
					GameBoardClass newBoard = currentGameBoard.getACopyOfGameBoardClass();
					
					if(depth%2 == 0)newBoard.setDice(currenteEmptyIndex, i, newBoard.getPcDice());
					
					else newBoard.setDice(currenteEmptyIndex, i, newBoard.getUserDice());
					
					valueList.add( createAndTraverseTree(newBoard, depth+1) );
				}
				
				else {
					

					EvalutionClass ec = new EvalutionClass();
					return ec.getTheValueOfEvalutionFunction(currentGameBoard);
					
				}
				
			}
			
			//System.out.println(valueList.toString()+" ccccccccccccccccccccccccccccccccccccccc");
			
			Collections.sort(valueList);
			
			if(depth%2==0) return valueList.get(valueList.size()-1);
			else return valueList.get(0);
			
		}
	}
	
}
