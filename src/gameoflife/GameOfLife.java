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
        
        int m = 10;
        int n = 4;
       
        Board board = new Board (m,n);
        board.initializeBoard();
        board.printBoard();
        
        byte[][] tmp = board.getRowsForParallelExecution(0, 3);
        
        for (byte[] tmp1 : tmp) {
            for (int j = 0; j<4; j++) {
                System.out.print(tmp1[j] + "\t");
            }
            System.out.print("\n");
        }
//        JFrame frame = new JFrame("Game of Life");
//        Graphics g = frame.getGraphics();
//        frame.getContentPane().add(new GraphicBoard(board), BorderLayout.CENTER);
//        frame.paint(g);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(m,n);
//        frame.setVisible(true);
//
//        for(int i = 0; i < 10; i++){
//            board.printBoard();
//            if (i == 0)
//                frame.paint(frame.getGraphics());
//            frame.repaint(2000, 0, 0, frame.getWidth(), frame.getHeight());
//            board.makeSteps(1);
//            board.printBoard();
//        }
    }
}
