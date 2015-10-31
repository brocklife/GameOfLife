/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

/**
 *
 * @author stefa
 */
public class GameOfLifeMultiThreaded {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        int NTHREAD = 4;
        int m = 400;
        int n = 400;
        int steps = 10000;
        int step = m/(NTHREAD-1);
        
        ExecutorService ex = Executors.newFixedThreadPool(NTHREAD);

        Board board = new Board(m, n);
        board.initializeBoard();
        board.printBoard();

        JFrame frame = new JFrame("Game of Life");
        Graphics g = frame.getGraphics();
        frame.getContentPane().add(new GraphicBoard(board), BorderLayout.CENTER);
        frame.paint(g);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(m, n);
        frame.setVisible(true);

        for (int i = 0; i < steps; i++) {
            for (int j = 0; j < NTHREAD; j++){
                ex.execute(new GoLThread(board, j*step, step));
            }
            ex.awaitTermination(10, TimeUnit.SECONDS);
            frame.repaint(2000, 0, 0, frame.getWidth(), frame.getHeight());
            board.swapBoards();
        }

    }
}
