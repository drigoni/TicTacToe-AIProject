/**
  Stub main 2
 */


import config.AbsConfig;
import config.Config;
import ticTacToe.AbsTicTacToe;
import ticTacToe.TicTacToe;
import ticTacToe.TicTacToe2AI;


public class StubMain2 {
    public static void main(String[] args) {
        AbsConfig config0 = new Config(0,5,0,0,0,2,0,1,5);
        TicTacToe2AI ticaTacToe0 = new TicTacToe2AI(config0);
        ticaTacToe0.setIsPrintField(true);
        ticaTacToe0.start();
    }
}
