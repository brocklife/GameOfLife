/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflifeskandium;

import cl.niclabs.skandium.muscles.Merge;
import gameoflife.Board;

/**
 *
 * @author stefano
 */
class MergeResults implements Merge<Board, Board>{ 

    public MergeResults(){
    }

    @Override
    public Board merge(Board[] param) throws Exception {
        param[0].swapBoards();
        return param[0];
    }
    
}
