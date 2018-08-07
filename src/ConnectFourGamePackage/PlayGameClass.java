package ConnectFourGamePackage;

import java.util.Scanner;



public class PlayGameClass {

	int index;
	
	public void startGame() {
		
		GameBoardClass gbc = new GameBoardClass();
		gbc.createNewGameBoard();
		
		gbc.printFinalGameBoard();
		
		int i=0;
		
		while(i<21) {
			index = userTurn();
			gbc.setDice(gbc.getEmptyIndexOfAColumn(index), index, gbc.getUserDice());
			gbc.printFinalGameBoard();
			
			
			index = computerTurn(gbc);
			gbc.setDice(gbc.getEmptyIndexOfAColumn(index), index, gbc.getPcDice());
			gbc.printFinalGameBoard();
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
	
	
	private int computerTurn(GameBoardClass gbc) {
		// TODO Auto-generated method stub
		System.out.println("computer turn : ");
		MiniMaxClass mmc = new MiniMaxClass();
		
		int col = mmc.getNextMove(gbc);
		
		return col;
	}
}
