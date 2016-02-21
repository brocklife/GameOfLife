package edu.spm.stefano.gameoflifemultithreaded;

import edu.spm.stefano.gameoflife.GraphicBoard;
import edu.spm.stefano.gameoflife.Board;
import edu.spm.stefano.gameoflife.Interval;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
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
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.BrokenBarrierException
     */
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        int NTHREADS = Runtime.getRuntime().availableProcessors();
        int m = 1000;
        int n = 1000;
        int steps = 1000;
        boolean graphics = false;
        boolean glider = false;
        int seed = 0;
        boolean seedBool = false, optimised = false;

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


        final Board board = new Board(m, n);
        Interval[] bounds = board.splitBoard(NTHREADS);
        
        if (glider) {
            board.initializeGlider();
        } else {
            if(seedBool)
                board.initializeBoard(seed);
            else
                board.initializeBoard();
        }

        if (graphics) {
            JFrame frame = new JFrame("Game of Life - Multithreaded");
            Graphics g = frame.getGraphics();
            frame.pack();
            Insets insets = frame.getInsets();
            frame.getContentPane().add(new GraphicBoard(board), BorderLayout.CENTER);
            frame.paint(g);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(insets.left + insets.right + n, insets.top + insets.bottom + m);
            frame.setVisible(true);
        }

        final CyclicBarrier barrier = new CyclicBarrier(NTHREADS, board::swapBoards); 
        ExecutorService threadPool = Executors.newFixedThreadPool(NTHREADS);   
        final long startTime = System.currentTimeMillis();
        for (int j = 0; j < NTHREADS; j++){
            threadPool.execute(new Consumer(board, bounds[j].a, bounds[j].b, steps, barrier));
        }
        threadPool.shutdown();
        threadPool.awaitTermination(10, TimeUnit.MINUTES);
        
        final long endTime = System.currentTimeMillis();
        
        System.out.println(endTime-startTime); 
        System.exit(0);
    }
}
