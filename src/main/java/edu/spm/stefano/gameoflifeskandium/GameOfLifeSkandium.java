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
        boolean glider = false, graphics = false;

        if (args.length == 5) {
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
                graphics = Boolean.parseBoolean(args[3]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[3] + " must be a bool.");
                System.exit(1);
            }
            try {
                glider = Boolean.parseBoolean(args[4]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[4] + " must be a bool.");
                System.exit(1);
            }
        } else {
            System.err.println("You can specify up to three arguments: height, width of the board and number of steps to be performed.");
            System.exit(1);
        }

        Board board = new Board(m, n);

        if (glider) {
            board.initializeGlider();
        } else {
            board.initializeBoard();
        }

        if (graphics) {
            JFrame frame = new JFrame("Game of Life - ST");
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
        Skeleton<Board, Board> gof = new Map<>(
                new SplitBoard(NTHREADS),
                new ComputeSteps(board),
                new MergeResults(board));

        Skeleton<Board, Board> forLoop = new For(gof, steps);

        board.initializeBoard();
        Stream<Board, Board> stream = skandium.newStream(forLoop);
        long init = System.currentTimeMillis();
        Future<Board> future = stream.input(board);

        Board res = future.get();
        long ended = System.currentTimeMillis();

        System.out.println(ended - init);
    }
}
