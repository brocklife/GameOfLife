/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;

/**
 *
 * @author stefa
 */
public class GameOfLifeMultiThreaded {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException{
        int NTHREADS = Runtime.getRuntime().availableProcessors();
        int counter = 0;
        int m = 500;
        int n = 500;
        int steps = 100;
        int step = m / (NTHREADS);

        Board board = new Board(m, n);
        board.initializeBoard();
        
        JFrame frame = new JFrame("Game of Life");
        Graphics g = frame.getGraphics();
        frame.getContentPane().add(new GraphicBoard(board), BorderLayout.CENTER);
        frame.paint(g);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(n, m);
        frame.setVisible(true);
        ArrayList<Thread> list = new ArrayList<>();
        
        final long startTime = System.currentTimeMillis();
        for (int i = 0; i < steps; i++) {
            ExecutorService threadPool = Executors.newFixedThreadPool(NTHREADS);
            for (int j = 0; j < NTHREADS; j++) {
                if (j < NTHREADS - 1) {
                    //list.add(new GoLThread(board, j * step, step, steps));
                    threadPool.execute(new GoLThread(board, j * step, step, steps));
                } else {
                    //list.add(new GoLThread(board, j * step, m - (step * j), steps));
                    threadPool.execute(new GoLThread(board, j * step, m - (step * j), steps));
                }
            }
            
            threadPool.shutdown();
            while(!threadPool.isTerminated()){}
            board.swapBoards();
            frame.update(g);
    }
        
        final long endTime = System.currentTimeMillis();
        System.out.println("Executed in " + (endTime-startTime) + " ms!");

    }
}
