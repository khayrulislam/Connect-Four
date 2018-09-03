package ConnectFourGamePackage;

import java.io.IOException;
import java.util.Scanner;



public class PlayGameClass {

	int index;
	int i=0;
	public void startGame()  {
		
		GameBoardClass gbc = new GameBoardClass();
		gbc.createNewGameBoard();
		
		
		
		
		System.out.println("Enter 1 if you want take the first move or enter 0 : ");
		
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		boolean turn = false;
		if( input.equals("1") ) {
			turn = true;
		}
		else if ( input.equals("0") ) {
			turn = false;
		}
		
		else {
			return;
		}
			
		gbc.printFinalGameBoard();

		
		while(i<42) {
			
			if(turn) {
				
				index = userTurn();
				gbc.setDice(gbc.getEmptyIndexOfAColumn(index), index, gbc.getUserDice());
				gbc.printFinalGameBoard();
				
				if(gbc.winCheck() ) {
					System.out.println("user win ");
					break;
				}
				
				turn = false;
			}
			
			else {

				
			

				if(i==0)index = 3;
				else index = computerTurn(gbc);

				gbc.setDice(gbc.getEmptyIndexOfAColumn(index), index, gbc.getPcDice());
				gbc.printFinalGameBoard();
				
				
				if(gbc.winCheck() ) {
					System.out.println("computer win ");
					break;
				}
				
				turn = true;
			}
			
		/*	index = userTurn();
			gbc.setDice(gbc.getEmptyIndexOfAColumn(index), index, gbc.getUserDice());
			gbc.printFinalGameBoard();
			
<<<<<<< HEAD
			index = computerTurn(gbc);
=======
			if(gbc.winCheck()  && !turn) {
				System.out.println("user win ");
				break;
			}
			

			/*index = computerTurn(gbc);
>>>>>>> branch 'master' of https://github.com/khayrulislam/Connect-Four.git
			gbc.setDice(gbc.getEmptyIndexOfAColumn(index), index, gbc.getPcDice());
			gbc.printFinalGameBoard();
			
<<<<<<< HEAD
			*/

			 if(gbc.winCheck()  && turn) {
				System.out.println("computer win ");
				break;
			}
	
		
			i++;
		}
		
		if(!gbc.winCheck())System.out.println("Match draw ");
		
	}
	
	private int userTurn() {
		// TODO Auto-generated method stub
		System.out.print("Enter an Index : ");
		Scanner sc = new Scanner(System.in);
		int ind = sc.nextInt();
		
		return ind;
	}
	
	
	private int computerTurn(GameBoardClass gbc)  {
		// TODO Auto-generated method stub
		System.out.println("computer turn : ");
		MiniMaxClass mmc = new MiniMaxClass();
		
		int col2 = mmc.getNextMove(gbc,7);
	/*	int col2;
		
		if(i>8) col2 = mmc.getNextMove(gbc,6);
		else col2 = mmc.getNextMove(gbc,2);
		*/
		//int col3 = mmc.getNextMove(gbc,1);
		
		//System.out.println(col1+ "  /////////////  "+ col2+"   ////////////////  "+col3);
		
		return col2;
	}
}
