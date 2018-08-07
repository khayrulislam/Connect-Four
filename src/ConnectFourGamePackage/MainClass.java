package ConnectFourGamePackage;

import java.util.ArrayList;

public class MainClass {

	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub

		//GameBoardClass gbc = new GameBoardClass();
		
		//gbc.createNewGameBoard();

		
		//MiniMaxClass mmc = new MiniMaxClass();
		
		
		
		//System.out.println(mmc.getNextMove(gbc));
		
		//EvalutionClass ec = new EvalutionClass();
		//ec.getTheValueOfEvalutionFunction(gbc);
		
		PlayGameClass pgc = new PlayGameClass();
		
		pgc.startGame();
		
	}

}
