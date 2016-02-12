package edu.spm.stefano.gameoflifeskandium;

import cl.niclabs.skandium.muscles.Merge;
import edu.spm.stefano.gameoflife.Board;

class MergeResults implements Merge<Board, Board>{ 
    Board board;
    public MergeResults(Board board){
        this.board = board;
    }
    @Override
    public Board merge(Board[] param) throws Exception {
        board.swapBoards();
        return board;
    }  

}
