/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spm.stefano.gameoflifemuskel;

import edu.spm.stefano.gameoflife.Board;
import edu.spm.stefano.gameoflife.GraphicBoard;
import static it.reactive.muskel.MuskelExecutor.local;
import it.reactive.muskel.MuskelProcessor;
import it.reactive.muskel.context.MuskelContext;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Insets;
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
        int seed = 0;
        boolean seedBool = false;
        boolean optimised = false;

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

        int step = m / (NTHREADS);
        int extra = m % NTHREADS;
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
        
        int start = 0; int chunk = 0;
        for (int j = 0; j < NTHREADS; j++) {
            start = start + chunk;
            chunk = step + (extra-- > 0 ? 1:0);            
            bounds[j] = new Couple(start, chunk);
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
        context.close();
        System.out.println(endTime - startTime);
    }
}