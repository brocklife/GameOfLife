/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflifeskandium;

import gameoflife.Board;

/**
 *
 * @author stefano
 */
class IntervalBoard {
    int start;
    int step;
    Board board;
    
    public IntervalBoard(int start, int step, Board board){
        this.start = start;
        this.board = board;
        this.step = step;
    }
}
