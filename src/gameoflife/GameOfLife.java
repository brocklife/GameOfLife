/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 *
 * @author stefa
 */
public class GameOfLife {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int m = 480;
        int n = 640;
       
        Board board = new Board (m,n);
        board.initializeBoard();
        board.printBoard();
        
        JFrame frame = new JFrame("Game of Life");
        Graphics g = frame.getGraphics();
        frame.getContentPane().add(new GraphicBoard(board), BorderLayout.CENTER);
        frame.paint(g);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(n,m);
        frame.setVisible(true);

        for(int i = 0; i < 10000; i++){
            //board.printBoard();
            if (i == 0)
                frame.paint(frame.getGraphics());
            frame.repaint(0, 0, frame.getWidth(), frame.getHeight());
            board.makeStep(0, m);
            board.swapBoards();
        }
    }
}
