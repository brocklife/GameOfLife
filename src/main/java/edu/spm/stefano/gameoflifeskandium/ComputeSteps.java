package edu.spm.stefano.gameoflifeskandium;

import cl.niclabs.skandium.muscles.Execute;
import edu.spm.stefano.gameoflife.Board;

class ComputeSteps implements Execute<Interval, Board> {
    Board board;
    public ComputeSteps(Board board){
        this.board = board;
    }
    @Override
    public Board execute(Interval param) throws Exception {
        board.makeStep(param.start, param.step);
        return board;
    }
}
