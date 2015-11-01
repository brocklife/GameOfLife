/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

/**
 *
 * @author stefa
 */
public class GoLThread extends Thread {

    Board board;
    int start, n;

    public GoLThread(Board b, int startRow, int nRows) {
        board = b;
        start = startRow;
        n = nRows;
    }

    @Override
    public void run() {
        board.makeStep(start, n);
    }

}
