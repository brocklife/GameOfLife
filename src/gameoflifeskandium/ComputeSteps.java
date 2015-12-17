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
class ComputeSteps implements Execute<IntervalBoard, Board> {

    public ComputeSteps() {
    }

    public Board execute(IntervalBoard param) throws Exception {
        param.board.makeStep(param.start, param.step);
        return param.board;
    }


}
