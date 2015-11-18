/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameOfLifeMultiThreaded;

import gameoflife.*;
import java.util.Random;

/**
 *
 * @author stefa
 */
public class Board {

    public final static byte ALIVE = 1;
    public final static byte DEAD = 0;

    byte[][] boardFrom;
    byte[][] boardTo;
    private final int M, N;

    public Board(int m, int n) {
        M = m;
        N = n;
        boardFrom = new byte[m][n];
        boardTo = new byte[m][n];
    }

    public int getHeight() {
        return M;
    }

    public int getWidth() {
        return N;
    }

    public byte getElement(int i, int j) {
        return boardFrom[i][j];
    }

    public void setElement(byte value, int i, int j) {
        boardFrom[i][j] = value;
    }
    
    private synchronized void setElementTo(byte value, int i, int j){
        boardTo[i][j] = value;
    }

    public void initializeBoard() {
        Random r = new Random();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                byte number = (byte) (Math.abs(r.nextInt()) % 2);
                boardFrom[i][j] = number;
            }
        }
    }
    
    public void printBoard(int fromRow, int nRows){
        for (int i = fromRow; i < fromRow+nRows; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(boardFrom[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public void printBoard() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(boardFrom[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    private int modulo(int i, int m) {
        return (i % m + m) % m;
    }

    public int countAliveNeighbours(int i, int j) {
        int result = 0;

        if (boardFrom[modulo(i - 1, M)][modulo(j - 1, N)] == ALIVE) {
            result++;
        }
        if (boardFrom[modulo(i - 1, M)][modulo(j, N)] == ALIVE) {
            result++;
        }
        if (boardFrom[modulo(i - 1, M)][modulo(j + 1, N)] == ALIVE) {
            result++;
        }
        if (boardFrom[modulo(i, M)][modulo(j - 1, N)] == ALIVE) {
            result++;
        }
        if (boardFrom[modulo(i, M)][modulo(j + 1, N)] == ALIVE) {
            result++;
        }
        if (boardFrom[modulo(i + 1, M)][modulo(j - 1, N)] == ALIVE) {
            result++;
        }
        if (boardFrom[modulo(i + 1, M)][modulo(j, N)] == ALIVE) {
            result++;
        }
        if (boardFrom[modulo(i + 1, M)][modulo(j + 1, N)] == ALIVE) {
            result++;
        }
        return result;
    }

    public void swapBoards() {
        byte[][] tmp = boardFrom;
        boardFrom = boardTo;
        boardTo = tmp;
    }

    public void makeSteps(int n, int startRow, int nRows) {
        for (int i = 0; i < n; i++) {
            makeStep(0, nRows);
            swapBoards();
        }
    }

    public void makeStep(int startRow, int nRows) {
        for (int i = startRow; i < startRow+nRows; i++) {
            for (int j = 0; j < N; j++) {
                int aliveNeighbours = countAliveNeighbours(i, j);
                if (boardFrom[i][j] == ALIVE) {
                    if (aliveNeighbours < 2 || aliveNeighbours > 3) {
                        boardTo[i][j] = DEAD;
                    } else {
                        boardTo[i][j] = ALIVE;
                    }
                } else {
                    if (aliveNeighbours == 3) {
                        boardTo[i][j] = ALIVE;
                    } else {
                        boardTo[i][j] = DEAD;
                    }
                }
            }
        }

    }

}
