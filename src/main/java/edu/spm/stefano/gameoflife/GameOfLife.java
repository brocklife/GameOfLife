package edu.spm.stefano.gameoflife;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Insets;
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

        int m = 500;
        int n = 500;
        int steps = 1000;
        Board board = new Board(m, n);
        boolean glider = false, graphics = false;

        if (args.length == 6) {
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

        if (glider) {
            board.initializeGlider();
        } else {
            board.initializeBoard();
        }

        if (graphics) {
            JFrame frame = new JFrame("Game of Life - Sequential");
            Graphics g = frame.getGraphics();
            frame.pack();
            Insets insets = frame.getInsets();
            frame.getContentPane().add(new GraphicBoard(board), BorderLayout.CENTER);
            frame.paint(g);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(insets.left + insets.right + n, insets.top + insets.bottom + m);
            frame.setVisible(true);
        }

        final long startTime = System.currentTimeMillis();
        for (int k = 0; k < steps; k++) {
            board.makeSteps(1, 0, m);
        }
        final long endTime = System.currentTimeMillis();

        System.out.println("Executed in " + (endTime - startTime) + " ms!");
    }
}
