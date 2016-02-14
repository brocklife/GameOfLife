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
import it.reactive.muskel.functions.SerializableFunction;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author stefano
 */
public class GameOfLifeMuskel2 {

    static Board makeStep(Board b, int m) {
        b.makeStep(0, m);
        return b;
    }

    static Board swapBoards(Board b) {
        b.swapBoards();
        return b;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int m = 1000;
        int n = 1000;
        int steps = 1000;
        int NTHREADS = Runtime.getRuntime().availableProcessors();
        boolean glider = false;
        boolean graphics = false;

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
            try {
                NTHREADS = Integer.parseInt(args[5]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[5] + " must be a bool.");
                System.exit(1);
            }
        } else {
            System.err.println("You can specify up to three arguments: height, width of the board and number of steps to be performed.");
            System.exit(1);
        }

        Integer step = m / (NTHREADS);
        Board board = new Board(m, n);

        if (glider) {
            board.initializeGlider();
        } else {
            board.initializeBoard();
        }

        if (graphics) {
            JFrame frame = new JFrame("Game of Life - Muskel2");
            Graphics g = frame.getGraphics();
            frame.pack();
            Insets insets = frame.getInsets();
            frame.getContentPane().add(new GraphicBoard(board), BorderLayout.CENTER);
            frame.paint(g);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(insets.left + insets.right + n, insets.top + insets.bottom + m);
            frame.setVisible(true);
        }
        
        MuskelContext context = MuskelContext.builder().local().defaultPoolSize(NTHREADS).build();

        Couple[] bounds = new Couple[NTHREADS];

        for (int j = 0; j < NTHREADS; j++) {
            if (j < NTHREADS - 1) {
                bounds[j] = new Couple(j * step, step);
            } else {
                bounds[j] = new Couple(j * step, m - j * step);
            }
        }
        
        final long startTime = System.currentTimeMillis();

        for (int k = 0; k < steps; k++) {
            MuskelProcessor.from(bounds)
                    .withContext(context)
                    .map((Couple b) -> {
                        board.makeStep(b.a, b.b);
                        return b;
                    }, local())
                    .toBlocking()
                    .first();
            board.swapBoards();
        }

        final long endTime = System.currentTimeMillis();
        System.out.println("Muskel2 - Executed in " + (endTime - startTime) + " ms!");

    }

}
