/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.util.Random;

/**
 *
 * @author stefa
 */
public class Board {

    public final static byte ALIVE = 1;
    public final static byte DEAD = 0;

    private final byte[][] boardA;
    private final byte[][] boardB;
    boolean A;
    private final int M, N;

    public Board(int m, int n) {
        M = m;
        N = n;
        A = true;
        boardA = new byte[m+2][n+2];
        boardB = new byte[m+2][n+2];
    }
    
    public int getHeight(){
        return M;
    }
    
    public int getWidth(){
        return N;
    }

    public byte getElement(int i, int j) {
        return boardA[i+1][j+1];
    }

    public void setElement(byte value, int i, int j) {
        boardA[i+1][j+1] = value;
    }

    public void initializeBoard() {
        Random r = new Random();
        for (int i = 1; i < M+1; i++) {
            for (int j = 1; j < N+1; j++) {
                byte number = (byte) (Math.abs(r.nextInt()) % 2);
                boardA[i][j] = number;
            }
        }
    }

    public void printBoard() {
        if (A) {
            for (int i = 1; i < M+1; i++) {
                for (int j = 1; j < N+1; j++) {
                    System.out.print(boardA[i][j] + "\t");
                }
                System.out.print("\n");
            }
            System.out.print("\n");
        } else {
            for (int i = 1; i < M+1; i++) {
                for (int j = 1; j < N+1; j++) {
                    System.out.print(boardB[i][j] + "\t");
                }
                System.out.print("\n");
            }
            System.out.print("\n");
        }

    }

    private int countAliveNeighbours(int i, int j) {
        int result = 0;
        byte[][] board;
        
        if (A) board = boardA;
        else board = boardB;
        
            if (board[(i - 1)%M][(j - 1)%N] == ALIVE) {
                result++;
            }
            if (board[(i - 1)%M][j%N] == ALIVE) {
                result++;
            }
            if (board[(i - 1)%M][(j + 1)%N] == ALIVE) {
                result++;
            }
            if (board[i%M][(j - 1)%N] == ALIVE) {
                result++;
            }
            if (board[i%M][(j + 1)%N] == ALIVE) {
                result++;
            }
            if (board[(i + 1)%M][(j - 1)%N] == ALIVE) {
                result++;
            }
            if (board[(i + 1)%M][j%N] == ALIVE) {
                result++;
            }
            if (board[(i + 1)%M][(j + 1)%N] == ALIVE) {
                result++;
            }
        return result;
    }

    public void makeSteps(int n) {
        for (int i = 0; i < n; i++) {
            makeStep();
        }
    }

    private void makeStep() {
        if (A) {
            makeStepA();
        } else {
            makeStepB();
        }
    }

    private void makeStepA() {
        for (int i = 1; i < M+1; i++) {
            for (int j = 1; j < N+1; j++) {
                int aliveNeighbours = countAliveNeighbours(i, j);
                if (boardA[i][j] == ALIVE) {
                    if (aliveNeighbours < 2 || aliveNeighbours > 3) {
                        boardB[i][j] = DEAD;
                    } else {
                        boardB[i][j] = ALIVE;
                    }
                } else {
                    if (aliveNeighbours == 3) {
                        boardB[i][j] = ALIVE;
                    } else {
                        boardB[i][j] = DEAD;
                    }
                }
            }
        }

        A = !A;
    }

    private void makeStepB() {
        for (int i = 1; i < M+1; i++) {
            for (int j = 1; j < N+1; j++) {
                int aliveNeighbours = countAliveNeighbours(i,j);
                if (boardB[i][j] == ALIVE) {
                    if (aliveNeighbours < 2 || aliveNeighbours > 3) {
                        boardA[i][j] = DEAD;
                    } else {
                        boardA[i][j] = ALIVE;
                    }
                } else {
                    if (aliveNeighbours == 3) {
                        boardA[i][j] = ALIVE;
                    } else {
                        boardA[i][j] = DEAD;
                    }
                }
            }
        }
        A = !A;
    }
}
