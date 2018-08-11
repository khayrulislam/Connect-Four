package ConnectFourGamePackage;

import java.io.IOException;
import java.util.Scanner;



public class PlayGameClass {

	int index;
	int i=0;
	public void startGame()  {
		
		GameBoardClass gbc = new GameBoardClass();
		gbc.createNewGameBoard();
		
		gbc.printFinalGameBoard();
		
		
		while(i<21) {
			

			index = computerTurn(gbc);
			gbc.setDice(gbc.getEmptyIndexOfAColumn(index), index, gbc.getPcDice());
			gbc.printFinalGameBoard();
			
			if(gbc.winCheck()) {
				System.out.println("computer win ");
				break;
			}
			
			index = userTurn();
			gbc.setDice(gbc.getEmptyIndexOfAColumn(index), index, gbc.getUserDice());
			gbc.printFinalGameBoard();
			
			if(gbc.winCheck()) {
				System.out.println("uer win ");
				break;
			}
			
			
			i++;
		}
		

		
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
		
		//int col2 = mmc.getNextMove(gbc,3);
		int col2;
		if(i>8) col2 = mmc.getNextMove(gbc,5);
		else col2 = mmc.getNextMove(gbc,2);
		
		//int col3 = mmc.getNextMove(gbc,1);
		
		//System.out.println(col1+ "  /////////////  "+ col2+"   ////////////////  "+col3);
		
		return col2;
	}
}
