package ConnectFourGamePackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class MiniMaxClass {

	
	//private final static int DEPTH_LIMIT=5;
	
	private final static int MIN=-10000000;
	private final static int MAX=100000000;
	
	int DEPTH_LIMIT;
	
	private ArrayList<PositionClass> getNextPlyCopy(ArrayList<PositionClass> nextPly ){
		
		ArrayList<PositionClass> newNextPly = new ArrayList<>();

		for(int i=0;i<nextPly.size();i++) newNextPly.add(nextPly.get(i));
		
		return newNextPly;
		
	}
	
	
	
	public int getNextMove(GameBoardClass gameBoard,int depth) 
	{
		ArrayList<PairClass> pairList = new ArrayList<>();
		
		
		DEPTH_LIMIT = depth;
		
		
		System.out.println("depth limit  "+ DEPTH_LIMIT);
	
		for(int i=0;i<gameBoard.getNumberOfCol();i++) {
			
			int emptyIndex = gameBoard.getEmptyIndexOfAColumn(i);
			
			if(emptyIndex != -1) {
				
				ArrayList<PositionClass> nextPly = new ArrayList<>();
				
				nextPly.add(new PositionClass(i, emptyIndex));
				
				GameBoardClass newGameBoard = gameBoard.getACopyOfGameBoardClass();
				
				newGameBoard.setDice(emptyIndex, i, newGameBoard.getPcDice());
				
				int value = createAndTraverseTree(newGameBoard,1,nextPly,false,MIN,MAX);
				
				
				if(i==3) value+=1;
				
				TakeChanceOrDefence chanceOrDefence = new TakeChanceOrDefence(gameBoard);
				
				
				
				TakeChanceOrDefence chanceOrDefence2 = new TakeChanceOrDefence(newGameBoard);
				if(chanceOrDefence2.shouldTakeChanceOrDefence(i, newGameBoard.getUserDice())) value = -100000;
				if(chanceOrDefence2.shouldTakeChanceOrDefence(i, newGameBoard.getPcDice())) value = -10000;

				if(chanceOrDefence.shouldTakeChanceOrDefence(i, gameBoard.getPcDice())) value += 1000000;
				if(chanceOrDefence.shouldTakeChanceOrDefence(i, gameBoard.getUserDice())) value += 200000;
				
				
				//
				//if(chanceOrDefence.shouldTakeChanceOrDefence2(i, gameBoard.getUserDice())) value += 20000;
				
				//if(chanceOrDefence.shouldTakeChanceOrDefence(i, gameBoard.getPcDice())) value += 1000000;
				
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
	
	
	

	

	private int createAndTraverseTree(GameBoardClass currentGameBoard, int depth, ArrayList<PositionClass>  nextPly,boolean isMaximizer, int alpha,int beta ) {
		
		int currenteEmptyIndex,best,calculatedValue;
		
		//System.out.println(depth+"          888888888888888888888888888888888888888888888888888888888888888888");
		//currentGameBoard.printGameBoard();
		
		if(depth==DEPTH_LIMIT) {
			
			EvalutionClass ec = new EvalutionClass();
			
			int value = ec.getTheValueOfEvalutionFunction(currentGameBoard, nextPly);
			/*
			//////////
			TakeChanceOrDefence chanceOrDefence = new TakeChanceOrDefence(currentGameBoard);
			for(int i = 0; i < 7; i++) {
				if(chanceOrDefence.shouldTakeChanceOrDefence(i, currentGameBoard.getPcDice())) value += 1000000;
				if(chanceOrDefence.shouldTakeChanceOrDefence(i, currentGameBoard.getUserDice())) value += 200000;
			}
			//////////////////*/
			return value;
			
			
			//return ec.getTheValueOfEvalutionFunction(currentGameBoard, nextPly);
			
		}
		
		if(isMaximizer) {
			best = MIN;
			
			//ArrayList<Integer> valueList = new ArrayList<>();
			//System.out.println("           maxxxxxxxxxx      ");
			
			for(int i=0;i<currentGameBoard.getNumberOfCol();i++) {
				
				
				
				currenteEmptyIndex = currentGameBoard.getEmptyIndexOfAColumn(i);
				
				if(currenteEmptyIndex != -1 ) {
					
					ArrayList<PositionClass> newNextPly = new ArrayList<>();
					newNextPly = getNextPlyCopy(nextPly);
					newNextPly.add(new PositionClass(i, currenteEmptyIndex));
					
					
					GameBoardClass newBoard = currentGameBoard.getACopyOfGameBoardClass();
					
					newBoard.setDice(currenteEmptyIndex, i, newBoard.getPcDice());
					
					//newBoard.printFinalGameBoard();
					
					calculatedValue = createAndTraverseTree(newBoard, depth+1, newNextPly, false, alpha,  beta) ; 
					
					//System.out.println("|Max Value "+ calculatedValue+"      depth     "+depth);
					
					best = Math.max(best, calculatedValue);
		            alpha = Math.max(alpha, best);
		            
		            if (beta <= alpha)
		                break;
				}
				/*
				else {
				
					EvalutionClass ec = new EvalutionClass();
					return ec.getTheValueOfEvalutionFunction(currentGameBoard, nextPly);
					
				}*/
				
			}
			
			
			return best;
			//System.out.println(valueList.toString()+" ccccccccccccccccccccccccccccccccccccccc");
//			
//			Collections.sort(valueList);
//			
//			if(depth%2==0) return valueList.get(valueList.size()-1);
//			else return valueList.get(0);
			
			
		}
		
		else {
			best = MAX;
			
			//currentGameBoard.printFinalGameBoard();
			
			//System.out.println("           minimizer      ");
			
			for(int i=0;i<currentGameBoard.getNumberOfCol();i++) {
				
				currenteEmptyIndex = currentGameBoard.getEmptyIndexOfAColumn(i);
				
				//System.out.println("collumn   "+currentGameBoard.getNumberOfCol());
				
				//System.out.println(" Min call "+ i+"   empty index  "+currenteEmptyIndex+"  tree height  "+depth);
				
				if(currenteEmptyIndex != -1 ) {
					
					ArrayList<PositionClass> newNextPly = new ArrayList<>();
					newNextPly = getNextPlyCopy(nextPly);
					newNextPly.add(new PositionClass(i, currenteEmptyIndex));
					
					
					GameBoardClass newBoard = currentGameBoard.getACopyOfGameBoardClass();
					
					newBoard.setDice(currenteEmptyIndex, i, newBoard.getUserDice());
					
					//newBoard.printFinalGameBoard();
					calculatedValue = createAndTraverseTree(newBoard, depth+1, newNextPly, true, alpha,  beta) ; 
					
					best = Math.min(best, calculatedValue);
		            beta = Math.min(beta, best);
		            
		            //System.out.println("best value   "+ best);
		            
		            if (beta <= alpha) 
		            	break;
		            
				}
				
			}
			
			return best;
		}
		
		
/*		else{
			
			ArrayList<Integer> valueList = new ArrayList<>();
			
			for(int i=0;i<currentGameBoard.getNumberOfCol();i++) {
				
				currenteEmptyIndex = currentGameBoard.getEmptyIndexOfAColumn(i);
				
				if(currenteEmptyIndex != -1 ) {
					
					ArrayList<PositionClass> newNextPly = new ArrayList<>();
					newNextPly = getNextPlyCopy(nextPly);
					newNextPly.add(new PositionClass(i, currenteEmptyIndex));
					
					GameBoardClass newBoard = currentGameBoard.getACopyOfGameBoardClass();
					
					if(depth%2 == 0)newBoard.setDice(currenteEmptyIndex, i, newBoard.getPcDice());
					
					else newBoard.setDice(currenteEmptyIndex, i, newBoard.getUserDice());
					
					
					
					valueList.add( createAndTraverseTree(newBoard, depth+1, newNextPly) );
				}
				
				else {
				
					EvalutionClass ec = new EvalutionClass();
					return ec.getTheValueOfEvalutionFunction(currentGameBoard, nextPly);
					
				}
				
			}
			
			//System.out.println(valueList.toString()+" ccccccccccccccccccccccccccccccccccccccc");
			
			Collections.sort(valueList);
			
			if(depth%2==0) return valueList.get(valueList.size()-1);
			else return valueList.get(0);
			
		}*/
	}
	
}
