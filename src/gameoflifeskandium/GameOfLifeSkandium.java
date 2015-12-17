/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflifeskandium;

import cl.niclabs.skandium.Skandium;
import cl.niclabs.skandium.Stream;
import cl.niclabs.skandium.skeletons.For;
import cl.niclabs.skandium.skeletons.Map;
import cl.niclabs.skandium.skeletons.Skeleton;
import gameoflife.Board;
import gameoflife.GraphicBoard;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.swing.JFrame;


/**
 *
 * @author stefano
 */
public class GameOfLifeSkandium {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int m = 500;
        int n = 500;
        int times = 1000;
        int THREADS = Runtime.getRuntime().availableProcessors();
        Skandium skandium = new Skandium(THREADS);
        Skeleton<Board, Board> gof = new Map<>(
                                            new SplitBoard(THREADS),
                                            new ComputeSteps(),
                                            new MergeResults());
        
        Skeleton<Board, Board> forLoop = new For(gof, times);
        
        Board b = new Board(m, n);
        b.initializeBoard();
        
        Stream<Board,Board> stream = skandium.newStream(forLoop);
        long init = System.currentTimeMillis();
        Future<Board> future = stream.input(b);
        
        JFrame frame = new JFrame("Game of Life");
        Graphics g = frame.getGraphics();
        frame.getContentPane().add(new GraphicBoard(b), BorderLayout.CENTER);
        frame.paint(g);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(n, m);
        frame.setVisible(true);
  
        Board result;
        result = future.get();
        long ended = System.currentTimeMillis();
        
        
        System.out.println(ended-init);

    }


    
}
