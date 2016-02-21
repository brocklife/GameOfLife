/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spm.stefano.gameoflifemultithreaded;

import edu.spm.stefano.gameoflife.Board;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stefa
 */
public class Consumer implements Runnable {

    Board board;
    int start, n, steps;
    private final CyclicBarrier barrier;

    public Consumer(Board b, int startRow, int nRows, int iterations, CyclicBarrier barrier) {
        board = b;
        start = startRow;
        n = nRows;
        steps = iterations;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        for (int i = 0; i < steps; i++) {
            board.makeStep(start, n);
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
