package AI.algorithms;

import AI.AIUtils;
import AI.EF.IEvalFunction;
import AI.MatrixOperations;
import ticTacToe.*;
import ticTacToe.AbsMove;

import java.util.ArrayList;


/**
 * This class implements the {@link IMinimax IMinimax} algorithm
 * with alpha beta pruning
 *
 * @author Davide Rigoni, Giovanni Mazzocchin, Alex Beccaro
 */
public class MinimaxABPRot extends AbsMinimaxABP{
	//Create the object used to save the fields
	MatrixOperations mop = new MatrixOperations();

 	public MinimaxABPRot(int depth, IEvalFunction f){
		super(depth,f);
	}

	@Override
	protected double maxValue(AbsTicTacToeAI state, double alpha, double beta, int depthP, int currentAI) {
		//Statistics: Count the new node
		this.res.addNode();
		int level = this.getDepth() - depthP;
		mop.add(level,state.getField());

		//Check the end of the game or the max depth
		if (state.checkEnd() || depthP == 0){
			double fvalue = this.getF().eval(state, currentAI);

			//Statistics: Set best score and nearest level
			res.setBestScore(fvalue);
			res.setNearestLevel(level);
			return fvalue;
		}


		double v = Double.NEGATIVE_INFINITY;
		ArrayList<AbsMove> actions = AIUtils.computeActions(state.getField());

		for (int i = 0; i < actions.size(); i++){
			AbsTicTacToeAI newState = (AbsTicTacToeAI)state.deepClone();
			newState.move(actions.get(i));
			char[][] currFieldConf = newState.getField();
			boolean matchFound = mop.checkExistence(level + 1, currFieldConf);

			if (matchFound == false){
				double min = minValue(newState,alpha, beta, depthP - 1, currentAI);
				if (min > v)
					v = min;

				if (v >= beta)
					return v;
				if (v > alpha)
					alpha = v;
			}
		}

		return v;
	}

	@Override
	protected double minValue(AbsTicTacToeAI state, double alpha, double beta, int depthP, int currentAI) {
		//Statistics: Count the new node
		this.res.addNode();
		int level = this.getDepth() - depthP;
		mop.add(level, state.getField());

		//Check the end of the game or the max depth
		if (state.checkEnd() || depthP == 0){
			double fvalue = this.getF().eval(state, currentAI);

			//Statistics: Set best score and nearest level
			res.setBestScore(fvalue);
			res.setNearestLevel(level);
			return fvalue;
		}


		double v = Double.POSITIVE_INFINITY;
		ArrayList< AbsMove> actions = AIUtils.computeActions(state.getField());

		for (int i = 0; i < actions.size(); i++){
			AbsTicTacToeAI newState = (AbsTicTacToeAI)state.deepClone();
			newState.move(actions.get(i));

			char[][] currFieldConf = newState.getField();
			boolean matchFound = mop.checkExistence(level + 1,currFieldConf);

			if (matchFound == false){
				double max = maxValue(newState, alpha, beta, depthP - 1, currentAI);
				if (max < v){
					v = max;
				}	

				if (v <= alpha)
					return v;
				if (v < beta)
					beta = v;
			}
		}

		return v;
	}

	@Override
	protected double callComputeMove(AbsTicTacToeAI state, int depthP, int currentAI){
		char[][] currFieldConf = state.getField();   //get the field from the new state
		int level = this.getDepth() - depthP;
		boolean matchFound = mop.checkExistence(level + 1, currFieldConf);
		if (!matchFound) {
			return minValue(state, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, depthP - 1, currentAI);
		} else{
			return Double.NEGATIVE_INFINITY;
		}
	}
}
