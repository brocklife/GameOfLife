package GameOfLifeMultiThreaded;

import java.awt.BorderLayout;
import java.awt.Graphics;
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
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException{
        int NTHREADS = Runtime.getRuntime().availableProcessors();
        int m = 1000;
        int n = 1000;
        int steps = 1000;
        int step = m / (NTHREADS);
        

        final Board board = new Board(m, n);
        board.initializeBoard();
        
        final CyclicBarrier barrier = new CyclicBarrier(NTHREADS, new Runnable() {
                                   public void run() {
                                     board.swapBoards();
                                   }
                                 });
        
        JFrame frame = new JFrame("Game of Life");
        Graphics g = frame.getGraphics();
        frame.getContentPane().add(new GraphicBoard(board), BorderLayout.CENTER);
        frame.paint(g);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(n, m);
        frame.setVisible(true);

        ExecutorService threadPool = Executors.newFixedThreadPool(NTHREADS);
        
        for (int j = 0; j < NTHREADS; j++) {
                if (j < NTHREADS - 1) {
                    threadPool.execute(new Consumer(board, j * step, step, steps, barrier, NTHREADS));
                } else {
                    threadPool.execute(new Consumer(board, j * step, m - (step * j), steps, barrier, NTHREADS));
                }
            }
    }
}
