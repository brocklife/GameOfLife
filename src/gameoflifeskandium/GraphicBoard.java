/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflifeskandium;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
/**
 *
 * @author stefa
 */
class GraphicBoard extends JPanel {

    private final Board board;
    private BufferedImage buffered;

    
    GraphicBoard(Board b) {
        this.setDoubleBuffered(true);
        board = b;
    }
    
    private void updateScreen(Graphics g) throws InterruptedException{
        buffered = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                int colour = board.getElement(i, j);
                int col;
                if (colour == 0)
                    col = 0;
                else
                    col = 65280;
                buffered.setRGB(j,i, col);
            }
        }
        if(imageUpdate(buffered, ImageObserver.FRAMEBITS,0, 0, buffered.getWidth(), buffered.getHeight()))
            g.drawImage(buffered, 0, 0, Color.BLACK, null); 
    }
    
    @Override
    public void paintComponent(Graphics g){   
        super.paintComponent(g);  
        try { 
            updateScreen(g);
        } catch (InterruptedException ex) {
            Logger.getLogger(GraphicBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}

