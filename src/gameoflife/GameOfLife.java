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
    public static void main(String[] args){
        
        int m = 1000;
        int n = 1000;
        int steps = 1000;
        Board board = new Board (m,n);
        board.initializeBoard();
        
        JFrame frame = new JFrame("Game of Life");
        Graphics g = frame.getGraphics();
        frame.getContentPane().add(new GraphicBoard(board), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(n,m);
        frame.setVisible(true);
        
        final long startTime = System.currentTimeMillis();
        for(int k = 0; k < steps; k++){
            board.makeSteps(1, 0, m);
        }
        final long endTime = System.currentTimeMillis();
        
        System.out.println("Executed in " + (endTime-startTime) + " ms!");
    }
}
