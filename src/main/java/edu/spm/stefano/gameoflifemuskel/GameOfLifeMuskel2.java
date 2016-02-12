/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spm.stefano.gameoflifemuskel;

import edu.spm.stefano.gameoflife.Board;
import edu.spm.stefano.gameoflife.GraphicBoard;
import it.reactive.muskel.MuskelExecutor;
import static it.reactive.muskel.MuskelExecutor.local;
import it.reactive.muskel.MuskelProcessor;
import it.reactive.muskel.context.MuskelContext;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.swing.JFrame;

/**
 *
 * @author stefano
 */
public class GameOfLifeMuskel2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int m = 500;
        int n = 500;
        int times = 1000;
        Board board = new Board(m, n);
        
        MuskelProcessor.from(board)
                .flatMap(board -> board.makeSteps(n, times, times), local())
                .subscribe(s -> System.out.println(s));
        
        long init = System.currentTimeMillis();
        
        JFrame frame = new JFrame("Game of Life - Skandium");
        Graphics g = frame.getGraphics();
        frame.pack();
        Insets insets = frame.getInsets();
        frame.getContentPane().add(new GraphicBoard(board), BorderLayout.CENTER);
        frame.paint(g);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(insets.left + insets.right + n, insets.top + insets.bottom + m);
        frame.setVisible(true);

        long ended = System.currentTimeMillis();
        
    }
    
}
