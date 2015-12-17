/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflifeskandium;

import cl.niclabs.skandium.muscles.Execute;
import gameoflife.Board;

/**
 *
 * @author stefano
 */
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
