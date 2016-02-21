package edu.spm.stefano.gameoflifeskandium;

import cl.niclabs.skandium.Skandium;
import cl.niclabs.skandium.Stream;
import cl.niclabs.skandium.skeletons.For;
import cl.niclabs.skandium.skeletons.Map;
import cl.niclabs.skandium.skeletons.Skeleton;
import edu.spm.stefano.gameoflife.Board;
import edu.spm.stefano.gameoflife.GraphicBoard;
import java.awt.BorderLayout;
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
        int steps = 1000;
        int NTHREADS = Runtime.getRuntime().availableProcessors();
        boolean glider = false, graphics = false, optimised = false;
        int seed = 0;
        boolean seedBool = false;

        if (args.length == 9) {
            try {
                m = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
            try {
                n = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[1] + " must be an integer.");
                System.exit(1);
            }
            try {
                steps = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[2] + " must be an integer.");
                System.exit(1);
            }
            try {
                optimised = Boolean.parseBoolean(args[3]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[3] + " must be a bool.");
                System.exit(1);
            }
            try {
                graphics = Boolean.parseBoolean(args[4]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[4] + " must be a bool.");
                System.exit(1);
            }            
            try {
                glider = Boolean.parseBoolean(args[5]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[5] + " must be a bool.");
                System.exit(1);
            }
            try {
                NTHREADS = Integer.parseInt(args[6]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[6] + " must be an integer.");
                System.exit(1);
            }
            try {
                seedBool = Boolean.parseBoolean(args[7]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[7] + " must be a bool.");
                System.exit(1);
            }
            try {
                seed = Integer.parseInt(args[8]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[8] + " must be an integer.");
                System.exit(1);
            }
        } else {
            System.err.println("Ooops. Wrong parameters as arguments.");
            System.exit(1);
        }

        Board board = new Board(m, n);

        if (glider) {
            board.initializeGlider();
        } else {
            if(seedBool)
                board.initializeBoard(seed);
            else
                board.initializeBoard();
        }

        if (graphics) {
            JFrame frame = new JFrame("Game of Life - Skandium");
            Graphics g = frame.getGraphics();
            frame.pack();
            Insets insets = frame.getInsets();
            frame.getContentPane().add(new GraphicBoard(board), BorderLayout.CENTER);
            frame.paint(g);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(insets.left + insets.right + n, insets.top + insets.bottom + m);
            frame.setVisible(true);
        }

        Skandium skandium = new Skandium(NTHREADS);
        Skeleton<Board, Board> forLoop = 
                new For(
                    new Map<>(
                    new SplitBoard(NTHREADS),
                    new ComputeSteps(board),
                    new MergeResults(board)), 
                steps); 
        Stream<Board, Board> stream = skandium.newStream(forLoop);
        Future<Board> future = stream.input(board);
        final long startTime = System.currentTimeMillis();
        future.get();
        final long endTime = System.currentTimeMillis();
        
        System.out.println(endTime - startTime);
    }
}
