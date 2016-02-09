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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.swing.JFrame;


/**
 *
 * @author stefano
 */
public class GameOfLifeSkandium {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int m = 1000;
        int n = 1000;
        int times = 1000;
        int THREADS = Runtime.getRuntime().availableProcessors();
        Board board = new Board(m, n);
        Skandium skandium = new Skandium(THREADS);
        Skeleton<Board, Board> gof = new Map<>(
                                            new SplitBoard(THREADS),
                                            new ComputeSteps(board),
                                            new MergeResults(board));
        
        Skeleton<Board, Board> forLoop = new For(gof, times);
        
        board.initializeBoard();
        Stream<Board,Board> stream = skandium.newStream(forLoop);
        long init = System.currentTimeMillis();
        Future<Board> future = stream.input(board);
        
        JFrame frame = new JFrame("Game of Life - Skandium");
        Graphics g = frame.getGraphics();
        frame.pack();
        Insets insets = frame.getInsets();
        frame.getContentPane().add(new GraphicBoard(board), BorderLayout.CENTER);
        frame.paint(g);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(insets.left + insets.right + n, insets.top + insets.bottom + m);
        frame.setVisible(true);

        Board res = future.get();
        long ended = System.currentTimeMillis();

        System.out.println(ended-init);
    }
}
