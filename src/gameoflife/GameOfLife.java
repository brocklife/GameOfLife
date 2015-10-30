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
        
        int m = 640;
        int n = 480;
       
        Board board = new Board (m,n);
        board.initializeBoard();
        
        JFrame frame = new JFrame("Game of Life");
        Graphics g = frame.getGraphics();
        frame.getContentPane().add(new GraphicBoard(board), BorderLayout.CENTER);
        frame.paint(g);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(m,n);
        frame.setVisible(true);

        for(int i = 0; i < 10; i++){
            board.printBoard();
            frame.repaint(2000, 0, 0, frame.getWidth(), frame.getHeight());
            board.makeSteps(1);
            board.printBoard();
        }
    }
    
}
