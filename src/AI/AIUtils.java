package AI;

import ticTacToe.AbsTicTacToe;
import ticTacToe.AbsMove;
import ticTacToe.Move;
import java.util.ArrayList;

/**
 * This class contains functions useful for the AI functions and algorithms
 *
 * @author Davide Rigoni, Giovanni Mazzocchin, Alex Beccaro
 */
public class AIUtils {

	/**
	 * This method find all the possible moves
	 * @param board Board of the game
	 * @return An ArrayList of move
	 */
	public static ArrayList<AbsMove> computeActions(char[][] board){
		int n = board.length;
		ArrayList<AbsMove> resActions = new ArrayList<AbsMove>();
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				if (board[i][j] == AbsTicTacToe.BLANKVALUE){
					resActions.add(new Move(i, j));
				}
			}
		}
		return resActions;
	}

}
