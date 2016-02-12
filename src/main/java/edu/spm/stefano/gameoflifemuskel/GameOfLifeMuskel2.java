/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spm.stefano.gameoflifemuskel;

import edu.spm.stefano.gameoflife.Board;
import edu.spm.stefano.gameoflife.GraphicBoard;
import it.reactive.muskel.MuskelExecutor;
import it.reactive.muskel.MuskelProcessor;
import it.reactive.muskel.context.MuskelContext;
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
public class GameOfLifeMuskel2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MuskelProcessor.from(1,2,3,4,5,6).
                withContext(MuskelContext.builder().local().build()).
                executeOn(MuskelExecutor.local()).
                map(i->Thread.currentThread().getName() + "Hello " + i).toList().
                subscribe(s->System.out.println(s));
    }
    
}
