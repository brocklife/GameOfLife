/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stefa
 */
public class GoLThread extends Thread {

    Board board;
    int start, n, steps;

    public GoLThread(Board b, int startRow, int nRows, int iterations) {
        board = b;
        start = startRow;
        n = nRows;
        steps = iterations;

    }

    @Override
    public void run() {
        for(int i = 0; i < steps; i++)
            board.makeStep(start, n);
    }
}

