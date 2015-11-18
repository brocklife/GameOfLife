/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameOfLifeMultiThreaded;

import java.awt.Graphics;
import javax.swing.JFrame;

/**
 *
 * @author stefa
 */
public class SynchronizedCounter {
    
    private int count;
    
    public SynchronizedCounter(){
        count = 0;
    }
    
    public synchronized int getValue(){
        return count;
    }
    
    public synchronized void increment(){
        System.out.println("Incrementing semaphore: " + count + " " + Thread.currentThread());
        count++;
    }
    
    public synchronized void reset(Board board){
        System.out.println("Resetting semaphore: " + count + " " + Thread.currentThread());
        count = 0;
        board.swapBoards();
        this.notifyAll();
    }
}
